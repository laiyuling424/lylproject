package com.lyl.mvptest.test.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * User: lyl
 * Date: 2019/4/30 4:12 PM
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface TestAnnotation3 {
    public int a() default -1;

    public String b() default "bb";
}
