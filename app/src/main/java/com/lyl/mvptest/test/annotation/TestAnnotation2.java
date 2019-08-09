package com.lyl.mvptest.test.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * User: lyl
 * Date: 2019/4/30 4:09 PM
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface TestAnnotation2 {
    public int a();

    public String b();
}
