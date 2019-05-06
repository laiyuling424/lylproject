package com.lyl.mvptest.movie;

import com.lyl.mvptest.beans.HotMovieinfo;

import java.util.List;
/**
 * create 2018/8/22
 * author lyl
 */
public interface BaseView<T> {
    void setPresenter(MoviePresenter presenter);
    void startLoading();
    void loading();
    void finishLoading();
    void show(List<HotMovieinfo.SubjectsBean> list);
    void showNo(String error);
    void showMore(List<HotMovieinfo.SubjectsBean> list);
}
