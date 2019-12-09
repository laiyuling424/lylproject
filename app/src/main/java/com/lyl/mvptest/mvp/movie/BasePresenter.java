package com.lyl.mvptest.mvp.movie;

/**
 * create 2018/8/22
 * author lyl
 */
public interface BasePresenter<T> {
    void start();

    void loadMore(int start);

    void cancelOkhttp();
}
