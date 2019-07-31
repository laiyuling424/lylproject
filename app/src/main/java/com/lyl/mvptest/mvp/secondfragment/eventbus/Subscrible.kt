package com.lyl.mvptest.mvp.secondfragment.eventbus

/**
 * User: lyl
 * Date: 2019-07-17 09:35
 */

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Subscrible(val threadMode: ThreadMode =ThreadMode.Main)