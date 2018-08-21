package com.lyl.mvptest;
import android.support.design.widget.NavigationView;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;
import com.lyl.mvptest.Movie.HotMovieinfo;
import com.lyl.mvptest.Utils.OkhttpUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends FragmentActivity {
    private FragmentManager mSupportFragmentManager;
    mainFragment ainFragment=new mainFragment();
    DrawerLayout mDrawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerLayout=(DrawerLayout)findViewById(R.id.drag_layout);
        NavigationView navigationView=(NavigationView)findViewById(R.id.nav);
    }
}
