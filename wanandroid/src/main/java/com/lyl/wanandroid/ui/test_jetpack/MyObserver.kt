package com.lyl.wanandroid.ui.test_jetpack

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * User: lyl
 * Date: 2019-06-11 17:43
 */

class MyObserver(var lifecycle: Lifecycle, var callback: CallBack) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public fun connectOnCreate() {
        p("connectOnCreate")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public fun connectOnStart() {
        p("connectOnStart")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public fun connectOnResume() {
        p("connectOnResume")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public fun connectOnStop() {
        p("connectOnStop")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public fun connectOnPause() {
        p("connectOnPause")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public fun disConnectOnDestroy() {
        p("disConnectOnDestroy")
    }

    fun p(string: String) {
        callback.update(string)
    }
}