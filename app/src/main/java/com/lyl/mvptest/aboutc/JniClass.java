package com.lyl.mvptest.aboutc;

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
     *
     * Description: diff 和包
     * @param oldApk 原有包地址
     * @param patch 差分包地址
     * @param output 输出路径
     * @return : void
     */
    public static native void bsPatch(String oldApk, String patch, String output);

}
