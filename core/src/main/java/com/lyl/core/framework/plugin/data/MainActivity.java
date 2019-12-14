//package com.lyl.core.framework.plugin.data;
//
//import android.content.Intent;
//import android.content.pm.PackageInfo;
//import android.content.pm.PackageManager;
//import android.os.Bundle;
//import android.os.Environment;
//import android.os.RemoteException;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.View;
//
//import com.morgoo.droidplugin.pm.PluginManager;
//
//import java.io.File;
//
//
//public class MainActivity extends AppCompatActivity {
//    private String apkPath = Environment.getExternalStorageDirectory().getAbsolutePath()
//            + File.separator +"yaoyiyao_plugin.apk";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }
//
//    public void click(View view) {
//        /*// 启动插件，插件下载好了在内存卡里面
//        Intent intent = new Intent();
//        // 目前启动肯定报错，类找不到  类怎么办？ 热修复应该能够找到解决方案
//        // dex 现在我们加载apk
//        intent.setClassName(getPackageName(),
//                "com.hc.yaoyiyao.MainActivity");
//        // 参数
//        intent.putExtra("user_name","Darren");
//        startActivity(intent);*/
//
//        // 一定要这样
//        PackageManager pm =  getPackageManager();
//        // 有了apk路径是可以获取apk的包名
//        PackageInfo info = pm.getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES);
//        String packageName = info.packageName;
//        Intent intent = pm.getLaunchIntentForPackage(packageName);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.putExtra("user_name","Darren");
//        startActivity(intent);
//    }
//
//    public void install(View view){
//        // PluginManager.install(this,apkPath);
//        try {
//            int result = PluginManager.getInstance().installPackage(apkPath, 0);
//            Log.e("TAG","result = "+result);
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
//    }
//}
