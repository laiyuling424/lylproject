package com.lyl.mvptest.aboutc;

import android.graphics.Bitmap;

/**
 * Create By: lyl
 * Date: 2020/8/6 9:30 AM
 */
public class JniClass {

    static {
        System.loadLibrary("native-lib");
    }

    /**
     * author : laiyuling
     * date : 2020/8/6 9:33 AM
     * <p>
     * Description: diff 和包
     *
     * @param oldApk 原有包地址
     * @param patch  差分包地址
     * @param output 输出路径
     * @return : void
     */
    public native static void bsPatch(String oldApk, String patch, String output);

    public native static Bitmap getCameraFrameBitbmp(Bitmap bmp);

    public native static void loadcascade(String filepath);

}
