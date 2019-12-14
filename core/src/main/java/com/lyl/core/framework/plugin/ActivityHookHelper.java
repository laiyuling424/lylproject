package com.lyl.core.framework.plugin;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;


/**
 * Create By: lyl
 * Date: 2019-12-13 15:32
 */
public class ActivityHookHelper {

    private Context context;
    private String proxyActivity = "com.lyl.core.framework.plugin.TestActivity";

    public ActivityHookHelper(Context context) {
        this.context = context;
    }

    public ActivityHookHelper(Context context, String proxyActivity) {
        this.context = context;
        this.proxyActivity = proxyActivity;
    }

    public String getProxyActivity() {
        return proxyActivity;
    }

    public void setProxyActivity(String activityAllName) {
        this.proxyActivity = activityAllName;
    }

    public void hookStartActivity() throws Exception {
        //找到hook点 有ams和Instrumentation 优先接口：IActivityManager

        //ActivityManager.getService().startActivity()
        //1. 先拿到ActivityManager.getService()对象
        Class<?> activityManagerClass = Class.forName("android.app.ActivityManager");
        Field iActivityManagerSingleton = activityManagerClass.getDeclaredField("IActivityManagerSingleton");
        iActivityManagerSingleton.setAccessible(true);
        //private static final Singleton<IActivityManager> IActivityManagerSingleton 静态变量设置为null
        Object iActivityManagerSingletonObj = iActivityManagerSingleton.get(null);

        Class<?> singletonClass = Class.forName("android.util.Singleton");
        Field mInstance = singletonClass.getDeclaredField("mInstance");
        mInstance.setAccessible(true);
        final Object amsObj = mInstance.get(iActivityManagerSingletonObj);

        //2.在startActivity方法中替换已经在androidmanifest注册activity的intent
        Class<?> iActivityManagerClass = Class.forName("android.app.IActivityManager");
        Object proxy = Proxy.newProxyInstance(context.getClass().getClassLoader(), new Class[]{iActivityManagerClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {

//                Log.e("TAG", "methodName----------------->" + method.getName());
                int index = -1;

                if (method.getName().equals("startActivity")) {
                    for (int i = 0; i < objects.length; i++) {
                        if (objects[i] instanceof Intent) {
                            index = i;
                            break;
                        }
                    }

                    Intent targetIntent = (Intent) objects[index];
                    Intent proxyIntent = new Intent();

                    // 判断宿主没有声明的Activity才走替换流程
                    boolean isExistActivity = isExistActivity(targetIntent.getComponent().getClassName());
                    if (isExistActivity) {
                        return method.invoke(amsObj, objects);
                    }

                    proxyIntent.setComponent(new ComponentName(context, proxyActivity));

                    // 把原来的Intent绑在代理Intent上面
                    proxyIntent.putExtra("targetIntent", targetIntent);

                    objects[index] = proxyIntent;
                }

                return method.invoke(amsObj, objects);
            }
        });

        mInstance.set(iActivityManagerSingletonObj, proxy);
    }


    public void hookLaunchActivity() throws Exception {

        //9.0 及以上 不行
        if (Build.VERSION.SDK_INT > 27) {
//            hookLaunchActivityP();

        } else {
            hookLaunchActivityO();
        }

    }

    //9.0 不行
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void hookLaunchActivityP() throws Exception {
        //在activitythread中将原来的intent替换回来
        Class<?> clientTransactionHandlerClass = Class.forName("android.app.ClientTransactionHandler");
        //handleLaunchActivity(ActivityThread.ActivityClientRecord r,PendingTransactionActions pendingActions, Intent customIntent)
        Class<?> activityClientRecordClass = Class.forName("android.app.ActivityThread$ActivityClientRecord");
        Class<?> pendingTransactionActionsClass = Class.forName("android.app.servertransaction.PendingTransactionActions");
        Method handleLaunchActivity = clientTransactionHandlerClass.getDeclaredMethod("handleLaunchActivity", activityClientRecordClass.getClass(),
                pendingTransactionActionsClass.getClass(), Intent.class);
        Parameter[] parameters = handleLaunchActivity.getParameters();
        if (parameters[2].getType().isAssignableFrom(Intent.class)) {
            Object proxyparameter = parameters[2];
            Intent proxyIntent = (Intent) proxyparameter;
            if (proxyIntent.getParcelableExtra("targetIntent") != null) {
                Intent originIntent = proxyIntent.getParcelableExtra("targetIntent");
                handleLaunchActivity.invoke(clientTransactionHandlerClass, parameters[0], parameters[1], originIntent);
            }
        }

    }

    //8.1及以下
    public void hookLaunchActivityO() throws Exception {
        //在activitythread中将原来的intent替换回来
        Class<?> activitythreadClass = Class.forName("android.app.ActivityThread");
        Field sCurrentActivityThread = activitythreadClass.getDeclaredField("sCurrentActivityThread");
        sCurrentActivityThread.setAccessible(true);
        Object sCurrentActivityThreadObject = sCurrentActivityThread.get(null);

        Field mH = activitythreadClass.getDeclaredField("mH");
        mH.setAccessible(true);
        Handler mhObj = (Handler) mH.get(sCurrentActivityThreadObject);
        Field mCallBack = mhObj.getClass().getDeclaredField("mCallback");
        mCallBack.setAccessible(true);

        mCallBack.set(mH, new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message message) {
                Log.d("lyll", "------>" + message.what);
                if (message.what == 100) {
                    handleLaunchActivity(message);
                }
                return false;
            }
        });

    }

    public void handleLaunchActivity(Message msg) {
        try {
            Object obj = msg.obj;
            Field intentField = obj.getClass().getDeclaredField("intent");
            intentField.setAccessible(true);
            Intent proxyIntent = (Intent) intentField.get(obj);
            // 代理意图
            Intent originIntent = proxyIntent.getParcelableExtra("targetIntent");
            Log.d("lyll", "------>" + originIntent.getComponent().getPackageName());
            Log.d("lyll", "------>" + originIntent.getComponent().getClassName());
            if (originIntent != null) {
                // 替换意图
                intentField.set(obj, originIntent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isExistActivity(String activity) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
            ActivityInfo[] activities = packageInfo.activities;
            for (int i = 0; i < activities.length; i++) {
                if (activity.equalsIgnoreCase(activities[i].name)) {
                    return true;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}
