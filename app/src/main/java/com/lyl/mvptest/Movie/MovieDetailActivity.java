package com.lyl.mvptest.Movie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.lyl.mvptest.R;
import com.lyl.mvptest.adapter.MoiveAdapter;
import com.lyl.mvptest.beans.HotMovieinfo;

import java.util.List;

public class MovieDetailActivity extends Activity {
    private TextView nameCN,nameUS,genres,year,directors,rating,collect_count;
    private Button like,see;
    private RecyclerView recyclerView;
    private List<HotMovieinfo.SubjectsBean> mlist;
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        init();
        setMovieData();
/*        Intent intent=getIntent();
        Bundle i=intent.getExtras();
        int a=getIntent().getIntExtra("position",-1);
        Log.d("lyll","position--"+a);
        Log.d("lyll","data--"+intent.getExtras().toString());
        List <HotMovieinfo.SubjectsBean> list=(List<HotMovieinfo.SubjectsBean>) getIntent().getSerializableExtra("MovieDetail");
        Log.d("lyll","data--"+list.toString());*/
    }
    public void init(){
        nameCN=(TextView)findViewById(R.id.nameCN);
        nameUS=(TextView)findViewById(R.id.nameUS);
        genres=(TextView)findViewById(R.id.genres);
        year=(TextView)findViewById(R.id.year);
        directors=(TextView)findViewById(R.id.directors);
        rating=(TextView)findViewById(R.id.rating);
        collect_count=(TextView)findViewById(R.id.collect_count);
        like=(Button)findViewById(R.id.like);
        see=(Button)findViewById(R.id.see);

        mlist=(List<HotMovieinfo.SubjectsBean>) getIntent().getSerializableExtra("MovieDetail");
        position=getIntent().getIntExtra("position",-1);

        recyclerView=(RecyclerView)findViewById(R.id.casts);
        LinearLayoutManager layoutManager=new LinearLayoutManager(MovieDetailActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        /**
         * recyclerView沒寫
         */
    }

    public void setMovieData(){
        /**
         * 數據沒有綁定
         */
    }

}
