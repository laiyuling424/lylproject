package com.lyl.mvptest.Movie;

import com.lyl.mvptest.beans.HotMovieinfo;

import java.util.List;

public interface BaseView<T> {
    void setPresenter(MoviePresenter presenter);
    void startLoading();
    void loading();
    void finishLoading();
    void show(List<HotMovieinfo.SubjectsBean> list);
    void showNo(String error);
}
