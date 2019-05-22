package com.lyl.mvptest.test.annotation.test;


import com.lyl.mvptest.test.annotation.TestAnnotation3;

/**
 * User: lyl
 * Date: 2019/4/30 4:32 PM
 */

@TestAnnotation3(a = 1, b = "cc")
public class Test {

    public static void main(String[] args) {

        boolean hasAnnotation = Test.class.isAnnotationPresent(TestAnnotation3.class);

        if ( hasAnnotation ) {
            TestAnnotation3 testAnnotation = Test.class.getAnnotation(TestAnnotation3.class);

            System.out.println("id:"+testAnnotation.a());
            System.out.println("msg:"+testAnnotation.b());
        }



    }

}


