package com.lyl.mvptest.adapter;

import com.lyl.mvptest.beans.HotMovieinfo;

import java.util.List;

/**
 * create 2018/8/23
 * author lyl
 */
public interface MovieAdapterSelectCallback {
    void onItemSelected(List<HotMovieinfo.SubjectsBean> list, int postion);
}
