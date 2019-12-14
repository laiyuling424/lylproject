//package com.lyl.core.framework.plugin.data;
//
//import android.content.Context;
//
///**
// * Email 240336124@qq.com
// * Created by Darren on 2017/4/30.
// * Version 1.0
// * Description:
// */
//public class PluginManager {
//    public static final void install(Context context, String apkPath) {
//        // 解决类加载的问题
//        try {
//            FixDexManager fixDexManager =
//                    new FixDexManager(context);
//            // 把apk的class加载到ApplicationClassLoader
//            fixDexManager.fixDex(apkPath);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
