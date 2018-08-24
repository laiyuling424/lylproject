package com.lyl.mvptest.Movie;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.lyl.mvptest.Utils.OkhttpUtil;
import com.lyl.mvptest.beans.HotMovieinfo;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
/**
 * create 2018/8/22
 * author lyl
 */
public class MoviePresenter implements BasePresenter{
    private BaseView view;

    public MoviePresenter(BaseView View) {
        view =View;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        view.loading();
        getMovieData();
    }
    public void getMovieData(){
        OkhttpUtil.GetOkhttp("https://api.douban.com/v2/movie/in_theaters", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                view.showNo(e.toString());
                Log.d("lyll","e--"+e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("lyll","success");
                String responseData=response.body().string();
                Gson gson=new Gson();
                HotMovieinfo hotMovieinfo=gson.fromJson(responseData,HotMovieinfo.class);
                List <HotMovieinfo.SubjectsBean> list=hotMovieinfo.getSubjects();
                view.show(list);
                view.finishLoading();
            }
        });
    }

}
