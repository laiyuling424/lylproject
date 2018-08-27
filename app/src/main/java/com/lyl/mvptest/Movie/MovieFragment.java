package com.lyl.mvptest.Movie;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.ViewModelStore;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.lyl.mvptest.adapter.EndlessRecyclerOnScrollListener;
import com.lyl.mvptest.adapter.LoadMoreWrapper;
import com.lyl.mvptest.adapter.MoiveAdapter;
import com.lyl.mvptest.adapter.MovieAdapterSelectCallback;
import com.lyl.mvptest.beans.HotMovieinfo;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * create 2018/8/21
 * author lyl
 */
public class MovieFragment extends Fragment implements BaseView,MovieAdapterSelectCallback{
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private Button button;
    private MoviePresenter moviePresenter;
    private TextView textView;
    private List<HotMovieinfo.SubjectsBean> mList=new ArrayList<>();
    private MoiveAdapter moiveAdapter;
    private LoadMoreWrapper loadMoreWrapper;
    private SwipeRefreshLayout swipeRefreshLayout;
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

        swipeRefreshLayout=(SwipeRefreshLayout)getView().findViewById(R.id.swipeRefreshLayout);

        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity().getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        moiveAdapter=new MoiveAdapter(getActivity().getApplicationContext(),mList,this);
        loadMoreWrapper=new LoadMoreWrapper(moiveAdapter);
        recyclerView.setAdapter(loadMoreWrapper);



        init();
    }

    private void init() {
        new MoviePresenter(this);
        moviePresenter.start();
        swipeRefreshLayout.setRefreshing(false);
        // 设置颜色属性的时候一定要注意是引用了资源文件还是直接设置16进制的颜色，因为都是int值容易搞混
        // 设置下拉进度的背景颜色，默认就是白色的
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
        // 设置下拉进度的主题颜色
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
        // 下拉时触发SwipeRefreshLayout的下拉动画，动画完毕之后就会回调这个方法
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                moviePresenter.getMovieData();
            }
        });

        // 设置加载更多监听
        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING);

                if (mList.size() < 39) {
                    moviePresenter.loadMore(20);
/*                    OkhttpUtil.GetOkhttp("https://api.douban.com/v2/movie/in_theaters?start=20", new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.d("lyll","fragment e--"+e.toString());
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String responseData=response.body().string();
                            Gson gson=new Gson();
                            HotMovieinfo hotMovieinfo=gson.fromJson(responseData,HotMovieinfo.class);
                            List <HotMovieinfo.SubjectsBean> addlist=hotMovieinfo.getSubjects();
                            recyclerView.post(new Runnable() {
                                @Override
                                public void run() {
                                    mList.addAll(addlist);
                                    //moiveAdapter.notifyDataSetChanged();
                                    loadMoreWrapper.notifyDataSetChanged();
                                }
                            });
                        }
                    });*/
                } else {
                    // 显示加载到底的提示
                    loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING_END);
                }
            }
        });
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
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        List<HotMovieinfo.SubjectsBean> relist=list;
        if(mList.size()==0){
            recyclerView.post(new Runnable() {
                @Override
                public void run() {
                    mList.addAll(list);
                    recyclerView.setVisibility(View.VISIBLE);
                    //moiveAdapter.notifyDataSetChanged();
                    loadMoreWrapper.notifyDataSetChanged();
                }
            });
        }else {
            if(relist.get(0).getId().equals(mList.get(0).getId())){
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity().getApplicationContext(),"木有新数据",Toast.LENGTH_SHORT).show();
                    }
                });
            }else {
                recyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        mList.addAll(list);
                        recyclerView.setVisibility(View.VISIBLE);
                        //moiveAdapter.notifyDataSetChanged();
                        loadMoreWrapper.notifyDataSetChanged();
                    }
                });
            }
        }

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

    @Override
    public void onItemSelected(List<HotMovieinfo.SubjectsBean> list, int postion) {
        Intent intent=new Intent(getActivity().getApplicationContext(),MovieDetailActivity.class);
        intent.putExtra("MovieDetail",(Serializable) list);
        intent.putExtra("position",postion);
        startActivity(intent);
    }

    @Override
    public void showMore(List list) {
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                mList.addAll(list);
                loadMoreWrapper.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        moviePresenter.cancelOkhttp();
    }
}
