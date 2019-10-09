package com.lyl.utils.function;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import androidx.annotation.RequiresApi;
import androidx.core.content.FileProvider;

import java.io.File;

/**
 * Create By: lyl
 * Date: 2019-4-09 10:24
 */
public class InstallUtil {
    public static int UNKNOWN_CODE = 2018;
    private Activity mAct;
    private String mPath;

    public InstallUtil(Activity mAct, String mPath) {
        this.mAct = mAct;
        this.mPath = mPath;
    }

    public void install() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) startInstallO();
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) startInstallN();
        else startInstall();
    }

    //6.0
    private void startInstall() {
        Intent install = new Intent(Intent.ACTION_VIEW);
        install.setDataAndType(Uri.parse("file://" + mPath), "application/vnd.android.package-archive");
        install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mAct.startActivity(install);
    }

    //7.0
    private void startInstallN() {
//        //参数1 上下文, 参数2 在AndroidManifest中的android:authorities值, 参数3  共享的文件
//        Uri apkUri = FileProvider.getUriForFile(mAct, Constants.AUTHORITY, new File(mPath));
//        Intent install = new Intent(Intent.ACTION_VIEW);
//        //由于没有在Activity环境下启动Activity,设置下面的标签
//        install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        //添加这一句表示对目标应用临时授权该Uri所代表的文件
//        install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//        install.setDataAndType(apkUri, "application/vnd.android.package-archive");
//        mAct.startActivity(install);


/*
在主model的androidManifest里面配置

        <provider
        android:name="androidx.core.content.FileProvider"
        <--包名-->
        android:authorities="com.lyl.myapplication.fileprovider"
        android:exported="false"
        android:grantUriPermissions="true">
            <meta-data
        android:name="android.support.FILE_PROVIDER_PATHS"
        <--在res/xml里面新建provider_paths 现在这个文件在本model里面-->
        android:resource="@xml/provider_paths"
        tools:replace="android:resource" />
        </provider>

*/


        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            intent.setDataAndType(Uri.fromFile(new File(mPath)), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        } else {
            // 声明需要的临时的权限
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            // 第二个参数，即第一步中配置的authorities
            Uri contentUri = FileProvider.getUriForFile(mAct, "com.lyl.myapplication.fileprovider", new File(mPath));
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        }
        mAct.startActivity(intent);
    }

    //8.0
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void startInstallO() {
        boolean isGranted = mAct.getPackageManager().canRequestPackageInstalls();
        if (isGranted) startInstallN();//安装应用的逻辑(写自己的就可以)
        else new AlertDialog.Builder(mAct)
                .setCancelable(false)
                .setTitle("安装应用需要打开未知来源权限，请去设置中开启权限")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface d, int w) {
                        Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
                        mAct.startActivityForResult(intent, UNKNOWN_CODE);
                    }
                })
                .show();
    }
}
