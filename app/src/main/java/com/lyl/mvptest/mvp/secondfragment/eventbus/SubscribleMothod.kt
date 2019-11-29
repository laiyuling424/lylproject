package com.lyl.mvptest.mvp.secondfragment.eventbus

import java.lang.reflect.Method

/**
 * Create By: lyl
 * Date: 2019-07-17 10:10
 */
data class SubscribleMothod(
        var name: Method? = null,
        var type: Any? = null,
        var threadMode: ThreadMode? = null
)