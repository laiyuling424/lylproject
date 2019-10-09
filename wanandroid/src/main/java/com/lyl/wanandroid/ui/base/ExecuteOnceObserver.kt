package com.lyl.wanandroid.ui.base

import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * Create By: lyl
 * Date: 2019-06-20 17:45
 */

class ExecuteOnceObserver<T>(val onExecuteOnceNext: (T) -> Unit = {},
                             val onExecuteOnceComplete: () -> Unit = {},
                             val onExecuteOnceError: (Throwable) -> Unit = {}) : Observer<T> {
    private var mDisposable: Disposable? = null

    override fun onComplete() {
        onExecuteOnceComplete()
    }

    override fun onSubscribe(d: Disposable) {
        mDisposable = d
    }

    override fun onNext(t: T) {
//        try {
            onExecuteOnceNext(t)
            this.onComplete()
//        } catch (e: Throwable) {
//            this.onError(e)
//        } finally {
//            if (mDisposable != null && !mDisposable!!.isDisposed) {
//                mDisposable!!.dispose()
//            }
//        }
    }
    override fun onError(e: Throwable) {
        onExecuteOnceError(e)
    }
}