package com.lyl.hotfix;

import android.content.Context;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Field;

import dalvik.system.DexClassLoader;

/**
 * Create By: lyl
 * Date: 2019-12-09 09:21
 */
public class Tinker {

    static File[] dexFile;

    public Tinker(String dexDir) {
        File dexFolder = new File(dexDir);
        if (!dexFolder.isDirectory()) {
            throw new RuntimeException("tinker init param dexDir is not Directory");
        }
        File[] dexFile = dexFolder.listFiles();
        for (File file : dexFile) {
            if (file.isDirectory()) {
                throw new RuntimeException("tinker init param dexDir Directory have Directory");
            }
        }
    }


    public static void hotfix(Context context) throws Exception {

        ClassLoader classLoader = context.getClassLoader();

        File file = new File(context.getDir("odex", Context.MODE_PRIVATE).getAbsolutePath() + File.separator + "fixdex");

        if (!file.exists()) {
            file.mkdir();
        }

        Object appElement = getElement(classLoader);

        Object hotfixElement;

        for (File dexfile : dexFile) {
            DexClassLoader dexClassLoader = new DexClassLoader(dexfile.getAbsolutePath(), file.getAbsolutePath(), null, classLoader.getParent());

            hotfixElement = getElement(dexClassLoader);

            appElement = margerElement(hotfixElement, appElement);
        }

        injectDexElements(classLoader, appElement);
    }

    private static void injectDexElements(ClassLoader classLoader, Object dexElement) throws Exception {
        Class<?> dexClass = Class.forName("dalvik.system.BaseDexClassLoader");
        Field pathList = dexClass.getDeclaredField("pathList");
        pathList.setAccessible(true);
        pathList.get(classLoader);

        Class<?> pathListClass = dexClass.getClass();
        Field dexElements = pathListClass.getDeclaredField("dexElements");
        dexElements.setAccessible(true);
        dexElements.set(dexClass, dexElement);
    }

    private static Object margerElement(Object arrayLhs, Object arrayRhs) {
        Class<?> localClass = arrayLhs.getClass().getComponentType();
        int i = Array.getLength(arrayLhs);
        int j = i + Array.getLength(arrayRhs);
        Object result = Array.newInstance(localClass, j);
        for (int k = 0; k < j; ++k) {
            if (k < i) {
                Array.set(result, k, Array.get(arrayLhs, k));
            } else {
                Array.set(result, k, Array.get(arrayRhs, k - i));
            }
        }
        return result;
    }

    private static Object getElement(ClassLoader classLoader) throws Exception {
        Class<?> dexClass = Class.forName("dalvik.system.BaseDexClassLoader");
        Field pathList = dexClass.getDeclaredField("pathList");
        pathList.setAccessible(true);
        pathList.get(classLoader);

        Class<?> pathListClass = dexClass.getClass();
        Field dexElements = pathListClass.getDeclaredField("dexElements");
        dexElements.setAccessible(true);
        Object o = dexElements.get(pathList);

        return o;
    }

}
