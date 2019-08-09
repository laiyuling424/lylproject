package com.lyl.wanandroid.ui.base

import org.reactivestreams.Subscriber

/**
 * User: lyl
 * Date: 2019-07-10 10:58
 */
abstract class BaseObserver<T> : Subscriber<T> {
    override fun onError(e: Throwable?) {
        onFail(e)
        onEnd()
    }

    override fun onNext(t: T) {
        onSuccess(t)
    }

    override fun onComplete() {
        onEnd()
    }

    /**
     * 结束回调
     */
    abstract fun onEnd()

    /**
     * 请求成功回调
     */
    abstract fun onSuccess(data: T)

    /**
     * 请求失败回调
     */
    abstract fun onFail(error: Throwable?)
}