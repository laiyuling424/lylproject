package com.lyl.mvptest.Utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/**
 * create 2018/8/24
 * author lyl
 */
public class CatchHandler implements Thread.UncaughtExceptionHandler {

    private Map<String, String> infos = new HashMap<String, String>();
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");


    private CatchHandler() {
    }

    public static CatchHandler getInstance() {

        return mCatchHandler;
    }

    private static CatchHandler mCatchHandler = new CatchHandler();

    private Context mContext;


    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        //处理未捕获异常
        handleException(ex);

    }

    private void handleException(Throwable ex) {
        if (ex == null) {
            return;
        }
        //收集设备参数信息
        collectDeviceInfo(mContext);
        //保存日志文件
        saveCrashInfo2File(ex);

        System.exit(1);
    }

    private void saveCrashInfo2File(Throwable ex) {

        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : infos.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key + "=" + value + "\n");
        }

        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        sb.append(result);
        try {
            long timestamp = System.currentTimeMillis();
            String time = formatter.format(new Date());
            String fileName = time + ".log";
            String rpname = "report.log";

            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                String path = Environment.getExternalStorageDirectory()+"/Android/data/"+mContext.getPackageName()+"/catch/";
                File dir = new File(path);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File report = new File(path+rpname);
                if(report.exists()){
                    report.delete();
                }
                FileOutputStream fos = new FileOutputStream(path+fileName);
                fos.write(sb.toString().getBytes());
                fos.close();
                FileOutputStream rpfos = new FileOutputStream(report);
                rpfos.write(sb.toString().getBytes());
                rpfos.close();
            }
        } catch (Exception e) {
            Log.e("ERROR", "an error occured while writing file...", e);
        }
    }

    public void collectDeviceInfo(Context ctx) {
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName == null ? "null" : pi.versionName;
                String versionCode = pi.versionCode + "";
                infos.put("versionName", versionName);
                infos.put("versionCode", versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("ERROR", "an error occured when collect package info", e);
        }
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                infos.put(field.getName(), field.get(null).toString());
                Log.d("ERROR", field.getName() + " : " + field.get(null));
            } catch (Exception e) {
                Log.e("ERROR", "an error occured when collect crash info", e);
            }
        }
    }

    public void init(Context context) {
        mContext = context;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }


    private void handleException(final Thread thread, final Throwable ex) {
        new AlertDialog.Builder(mContext).setMessage("ex").show();
    }
}
