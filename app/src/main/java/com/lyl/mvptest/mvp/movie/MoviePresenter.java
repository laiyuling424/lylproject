package com.lyl.mvptest.mvp.movie;

import android.util.Log;

import com.google.gson.Gson;
import com.lyl.mvptest.beans.HotMovieinfo;
import com.lyl.mvptest.utils.OkhttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * create 2018/8/22
 * author lyl
 */
public class MoviePresenter implements BasePresenter {
    List<HotMovieinfo.SubjectsBean> list;
    int hotMovieinfoCount = 0;
    private BaseView view;
    private Call call;

    public MoviePresenter(BaseView View) {
        view = View;
        view.setPresenter(this);
        list = new ArrayList<>();
    }

    public int getHotMovieinfoCount() {
        return hotMovieinfoCount;
    }

    public int getHotMovieinfoLenght() {
        return list.size();
    }

    @Override
    public void cancelOkhttp() {

        for (Call call : OkhttpUtil.okHttpClient.dispatcher().runningCalls()) {
            call.cancel();
        }

    }

    @Override
    public void start() {
        view.loading();
        getMovieData();
    }

    public void getMovieData() {
        OkhttpUtil.GetOkhttp("https://api.douban.com/v2/movie/in_theaters", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                view.showNo(e.toString());
                Log.d("lyll", "e--" + e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                Gson gson = new Gson();
                HotMovieinfo hotMovieinfo = gson.fromJson(responseData, HotMovieinfo.class);
                hotMovieinfoCount = hotMovieinfo.getTotal();
                list = hotMovieinfo.getSubjects();
                if (list == null || list.size() == 0) {
                    view.showNo("数据获取失败");
                } else {
                    view.show(list);
                    view.finishLoading();
                }
            }
        });
    }

    @Override
    public void loadMore(int start) {
        OkhttpUtil.GetOkhttp("https://api.douban.com/v2/movie/in_theaters?start=" + start, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("lyll", "fragment e--" + e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                Gson gson = new Gson();
                HotMovieinfo hotMovieinfo = gson.fromJson(responseData, HotMovieinfo.class);
//                hotMovieinfoCount=hotMovieinfoCount+hotMovieinfo.getCount();
                list.addAll(hotMovieinfo.getSubjects());
                view.showMore(list);
            }
        });
    }
}
