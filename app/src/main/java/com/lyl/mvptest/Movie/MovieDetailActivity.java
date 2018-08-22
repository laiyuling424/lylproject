package com.lyl.mvptest.Movie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.lyl.mvptest.R;
import com.lyl.mvptest.beans.HotMovieinfo;

import java.util.List;

public class MovieDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Intent intent=getIntent();
        Bundle i=intent.getExtras();
        int a=getIntent().getIntExtra("position",-1);
        Log.d("lyll","position--"+a);
        Log.d("lyll","data--"+intent.getExtras().toString());
        List <HotMovieinfo.SubjectsBean> list=(List<HotMovieinfo.SubjectsBean>) getIntent().getSerializableExtra("MovieDetail");
        Log.d("lyll","data--"+list.toString());
    }
}
