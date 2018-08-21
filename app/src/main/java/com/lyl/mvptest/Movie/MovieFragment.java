package com.lyl.mvptest.Movie;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.lyl.mvptest.R;
import com.lyl.mvptest.Utils.OkhttpUtil;
import com.lyl.mvptest.adapter.MoiveAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MovieFragment extends Fragment {
    private List<HotMovieinfo.SubjectsBean> list;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.movie_layout,container,false);
        //return inflater.inflate(R.layout.moive_item_layout,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadMoive();
        recyclerView=(RecyclerView) getView().findViewById(R.id.movieRecycleView);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity().getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

    }

    public void loadMoive(){
        OkhttpUtil.GetOkhttp("https://api.douban.com/v2/movie/in_theaters", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("lyll","e--"+e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //Log.d("lyll","response--"+response.toString());
                String responseData=response.body().string();
                Gson gson=new Gson();
                HotMovieinfo hotMovieinfo=gson.fromJson(responseData,HotMovieinfo.class);
                list=hotMovieinfo.getSubjects();
                recyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        MoiveAdapter moiveAdapter=new MoiveAdapter(getActivity().getApplicationContext(),list);
                        recyclerView.setAdapter(moiveAdapter);
                    }
                });
                //String image=hotMovieinfo.getSubjects().get(0).getImages().getLarge();
                //Log.d("lyll","data01--"+hotMovieinfo.toString());
                //Log.d("lyll","data02--"+hotMovieinfo.getSubjects().toString());
                for (HotMovieinfo.SubjectsBean list:hotMovieinfo.getSubjects()){
                    Log.d("lyll","name--"+list.getOriginal_title());
                }
            }
        });
    }
}
