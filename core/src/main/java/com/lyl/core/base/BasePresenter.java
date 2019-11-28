package com.lyl.core.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Create By: lyl
 * Date: 2019-11-28 09:49
 */
public abstract class BasePresenter<V extends BaseView> {

    protected Reference<V> viewRef;

    public void attachView(V view) {
        //持有的是Activity的软引用，
        viewRef = new WeakReference<V>(view);
    }


    public void detachView() {
        if (viewRef != null) {
            viewRef.clear();
            viewRef = null;
        }
    }
}
