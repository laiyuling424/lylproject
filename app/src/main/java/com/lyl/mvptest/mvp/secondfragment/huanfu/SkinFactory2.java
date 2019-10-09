package com.lyl.mvptest.mvp.secondfragment.huanfu;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import java.lang.reflect.Method;

/**
 * Create By: lyl
 * Date: 2019-07-27 11:32
 */
public class SkinFactory2 implements LayoutInflater.Factory2 {

    private static String[] prxfixList={
            "android.widget",
            "android.view",
            "android.webkit"
    };

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {

//        var assetManager = AssetManager::class.objectInstance
//        val addAssetPath = assetManager!!.javaClass.getMethod("addAssetPath", String.javaClass)

        try {
            AssetManager assetManager = AssetManager.class.newInstance();

            Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View view;
//        view.setBackground(SkinManager.Companion.getSkinManager().getDrawable(000));
        try {
            Class<?> aClass = context.getClassLoader().loadClass(name);
            aClass.getConstructor(new Class[]{Context.class,AttributeSet.class});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
