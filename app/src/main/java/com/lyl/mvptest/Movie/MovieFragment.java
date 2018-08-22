package com.lyl.mvptest.Movie;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.ViewModelStore;
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
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lyl.mvptest.R;
import com.lyl.mvptest.Utils.OkhttpUtil;
import com.lyl.mvptest.adapter.MoiveAdapter;
import com.lyl.mvptest.beans.HotMovieinfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MovieFragment extends Fragment implements BaseView {
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private Button button;
    private MoviePresenter moviePresenter;
    private TextView textView;
    private List<HotMovieinfo.SubjectsBean> mList=new ArrayList<>();
    private MoiveAdapter moiveAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.movie_layout,container,false);
        //return inflater.inflate(R.layout.moive_item_layout,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView=(RecyclerView) getView().findViewById(R.id.movieRecycleView);
        progressBar=(ProgressBar)getView().findViewById(R.id.progressBar);
        button=(Button)getView().findViewById(R.id.btn01);
        textView=(TextView)getView().findViewById(R.id.error);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity().getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        moiveAdapter=new MoiveAdapter(getActivity().getApplicationContext(),mList);
        recyclerView.setAdapter(moiveAdapter);
        new MoviePresenter(this);
        moviePresenter.start();

    }



    @Override
    public Lifecycle getLifecycle() {
        return super.getLifecycle();
    }

    @Override
    public void setPresenter(MoviePresenter presenter) {
        this.moviePresenter=presenter;
    }

    @Override
    public void startLoading() {
        moviePresenter.start();
        progressBar.setVisibility(View.VISIBLE);
        button.setVisibility(View.GONE);
        textView.setVisibility(View.GONE);
    }

    @Override
    public void loading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void finishLoading() {
        progressBar.post(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
            }
        });

    }

    @NonNull
    @Override
    public ViewModelStore getViewModelStore() {
        return super.getViewModelStore();
    }

    @Override
    public void show(final List list) {
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                mList.addAll(list);
                recyclerView.setVisibility(View.VISIBLE);
                moiveAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void showNo(final String error) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
                textView.setVisibility(View.VISIBLE);
                textView.setText(error);
                Toast.makeText(getActivity().getApplicationContext(),"数据加载失败,点击再次加载",Toast.LENGTH_LONG).show();
                button.setVisibility(View.VISIBLE);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startLoading();
                    }
                });
            }
        });

    }
}
