package com.lyl.mvptest.test.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * User: lyl
 * Date: 2019/4/30 4:14 PM
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface TestAnnotation4 {
    public String a();
}
