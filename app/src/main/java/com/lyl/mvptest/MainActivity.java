package com.lyl.mvptest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import com.google.android.material.navigation.NavigationView;
import com.lyl.mvptest.adapter.ViewPagerAdapter;
import com.lyl.mvptest.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * create 2018/8/21
 * author lyl
 */
public class MainActivity extends AppCompatActivity {
//    DrawerLayout mDrawerLayout;
    NoScrollViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    List<Fragment> list;
     Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ButterKnife.bind(this);

        init();


    }

    private void init() {
//        mDrawerLayout=(DrawerLayout)findViewById(R.id.drag_layout);
//        NavigationView navigationView=(NavigationView)findViewById(R.id.nav);
        viewPager =(NoScrollViewPager)findViewById(R.id.viewpager);
        list=new ArrayList<>();
        Fragment fragmentOne=new MainFragment();
        Fragment fragmentTwo=new SecondFragment();
        Fragment fragmentThree=new ThirdFragment();
        list.add(fragmentOne);
        list.add(fragmentTwo);
        list.add(fragmentThree);
//        viewPagerAdapter=new ViewPagerAdapter(this.getSupportFragmentManager(),list);
        viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager(),list);

        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(0);


        mToolbar=(Toolbar)findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_me);
        mToolbar.setContentInsetsAbsolute(0, 0);
        mToolbar.setContentInsetStartWithNavigation(0);


//        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @SuppressLint("WrongConstant")
//            @Override
//            public void onClick(View v) {
//               mDrawerLayout.openDrawer(Gravity.START);
//            }
//        });
    }

    @OnClick({R.id.tv1,R.id.tv2,R.id.tv3})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv1:
                viewPager.setCurrentItem(0);
                break;
            case R.id.tv2:
                viewPager.setCurrentItem(1);
                break;
            case R.id.tv3:
                viewPager.setCurrentItem(2);
                break;
        }
    }

}
