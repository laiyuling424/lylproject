package com.lyl.mvptest.adapter;

import android.view.View;

import com.lyl.mvptest.beans.HotMovieinfo;

import java.util.List;
import java.util.UUID;

public interface MovieAdapterSelectCallback {
    void onItemSelected(List<HotMovieinfo.SubjectsBean> list,int postion);
}