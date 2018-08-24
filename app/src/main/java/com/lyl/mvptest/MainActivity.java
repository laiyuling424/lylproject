package com.lyl.mvptest;
import android.support.design.widget.NavigationView;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
/**
 * create 2018/8/21
 * author lyl
 */
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
