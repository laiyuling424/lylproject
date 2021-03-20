package com.lyl.core.mvpbase;

/**
 * Create By: lyl
 * Date: 2019-11-28 09:53
 */
public interface BaseView {

    void showError(String error);

    void showLoading();

    void stopLoading();

    //无网络
    void showNetworkError();

    //网络是否有用
    boolean isNetworkAvailable();

    void loadSuccess();

    void showDataError();

}

