package com.lyl.mvptest.test.annotation.test;

import android.util.Log;

import com.lyl.mvptest.test.annotation.TestAnnotation2;
import com.lyl.mvptest.test.annotation.TestAnnotation3;
import com.lyl.mvptest.test.annotation.TestAnnotation4;
import com.lyl.mvptest.test.annotation.TestAnnotation5;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * User: lyl
 * Date: 2019/4/30 4:06 PM
 */
@TestAnnotation2(a = 1, b = "cc")
@com.lyl.mvptest.test.annotation.TestAnnotation
public class TestAnnotation {

    //abcdefghijklmnopqrstuvwxyz

    @TestAnnotation2(a = 1, b = "cc")
    public void aa() {
        Log.d("lyll", "aa method");
    }

    @TestAnnotation3()
    public void bb() {

    }

    @TestAnnotation3(a = 3333, b = "cccccc")
    public void cc() {

    }

    @TestAnnotation4(a = "aa")
    public void dd() {

    }

    @TestAnnotation5()
    public void ee() {

    }

    User user;

    public void ff() {
        user = new User();
        user.speak();
        user.sleep();
    }

    public void gg() {
        boolean hasAnnotation = TestAnnotation.class.isAnnotationPresent(TestAnnotation2.class);
        Log.d("lyll", "gg method hasAnnotation="+hasAnnotation);
        if (hasAnnotation) {
            TestAnnotation2 testAnnotation = TestAnnotation.class.getAnnotation(TestAnnotation2.class);

            Log.d("lyll", "id:" + testAnnotation.a());
            Log.d("lyll", "msg:" + testAnnotation.b());
        }

        try {
            Method methodcc=TestAnnotation.class.getMethod("cc");
            if (methodcc!=null){
                Annotation[] testAnnotation2=methodcc.getAnnotations();
                for( int i = 0;i < testAnnotation2.length;i++) {
                    Log.d("lyll", "sdfsd");
                    Log.d("lyll", "method cc annotation::" + testAnnotation2[i].annotationType().getSimpleName());
                }

                TestAnnotation3 testAnnotation3 = methodcc.getAnnotation(TestAnnotation3.class);
                Log.d("lyll", "method cc annotation:: id:" + testAnnotation3.a());
                Log.d("lyll", "method cc annotation:: msg:" + testAnnotation3.b());
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

    public void hh() {

    }

    public void ii() {

    }

    public void jj() {

    }

    public void kk() {

    }

    public void ll() {

    }

    public void mm() {

    }

    public void nn() {

    }

    public void oo() {

    }

}
