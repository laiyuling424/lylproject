package com.lyl.mvptest.Movie;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lyl.mvptest.R;
import com.lyl.mvptest.adapter.ActorAdapter;
import com.lyl.mvptest.adapter.MoiveAdapter;
import com.lyl.mvptest.beans.HotMovieinfo;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailActivity extends AppCompatActivity {
    private TextView nameCN,nameUS,genres,year,directors,rating,collect_count;
    private Button like,see;
    private ImageView imageView;
    private RecyclerView recyclerView;
    private SlidingUpPanelLayout slidingUpPanelLayout;
    private Toolbar toolbar;
    private List<HotMovieinfo.SubjectsBean> mlist;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        this.setSupportActionBar((Toolbar) findViewById(R.id.main_toolbar));
        init();
        setMovieData();
    }
    public void init(){
        nameCN=(TextView)findViewById(R.id.nameCN);
        nameUS=(TextView)findViewById(R.id.nameUS);
        genres=(TextView)findViewById(R.id.genres);
        year=(TextView)findViewById(R.id.year);
        directors=(TextView)findViewById(R.id.directors);
        rating=(TextView)findViewById(R.id.rating);
        collect_count=(TextView)findViewById(R.id.collect_count);
        imageView=(ImageView)findViewById(R.id.image);
        toolbar=(Toolbar) findViewById(R.id.main_toolbar);
        like=(Button)findViewById(R.id.like);
        see=(Button)findViewById(R.id.see);
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("lyll", "like onclick");
            }
        });
        see.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("lyll", "see onclick");
            }
        });

        slidingUpPanelLayout=(SlidingUpPanelLayout)findViewById(R.id.sliding_layout);
        slidingUpPanelLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                Log.i("lyll", "onPanelSlide, offset " + slideOffset);
            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
                Log.i("lyll", "onPanelStateChanged, newState" + newState);
            }
        });
        slidingUpPanelLayout.setFadeOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
            }
        });

        mlist=(List<HotMovieinfo.SubjectsBean>) getIntent().getSerializableExtra("MovieDetail");
        position=getIntent().getIntExtra("position",-1);


        recyclerView=(RecyclerView)findViewById(R.id.casts);
        LinearLayoutManager layoutManager=new LinearLayoutManager(MovieDetailActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        ActorAdapter actorAdapter=new ActorAdapter(MovieDetailActivity.this,mlist,position);
        recyclerView.setAdapter(actorAdapter);

        this.toolbar.setNavigationIcon(R.drawable.ic_toolbar_back);
        this.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View paramAnonymousView) {
                finish();
            }
        });
    }

    public void setMovieData(){
        nameCN.setText(mlist.get(position).getOriginal_title());
        nameUS.setText(mlist.get(position).getSubtype());
        year.setText(mlist.get(position).getYear());
        rating.setText(String.valueOf(mlist.get(position).getRating().getAverage()));
        collect_count.setText(String.valueOf(mlist.get(position).getCollect_count()));

        List<String> list=new ArrayList<>();
        for(int i=0;i<mlist.get(position).getDirectors().size();i++){
            list.add(mlist.get(position).getDirectors().get(i).getName());
        }
        directors.setText(list.toString());
        list.clear();
        for(int i=0;i<mlist.get(position).getGenres().size();i++){
            list.add(mlist.get(position).getGenres().get(i));
        }
        genres.setText(list.toString());
        list.clear();

        Glide.with(MovieDetailActivity.this).load(mlist.get(position).getImages().getLarge()).into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (slidingUpPanelLayout != null) {
                    if (slidingUpPanelLayout.getPanelState() != SlidingUpPanelLayout.PanelState.HIDDEN) {
                        slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
                    } else {
                        slidingUpPanelLayout.animate().translationY(0).setDuration(200);
                        slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                    }
                }
            }
        });
    }

}