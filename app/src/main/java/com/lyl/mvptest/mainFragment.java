package com.lyl.mvptest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lyl.mvptest.adapter.MainFragmentAdapter;
/**
 * create 2018/8/21
 * author lyl
 */
public class mainFragment extends Fragment {
    protected TabLayout mTabLayout;
    protected Toolbar mToolbar;
    protected ViewPager viewPager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mainfragment_layout, container, false);
        mTabLayout=(TabLayout)view.findViewById(R.id.tablayout);
        mToolbar=(Toolbar)view.findViewById(R.id.toolbar);
        viewPager=(ViewPager)view.findViewById(R.id.viewpager);


        mToolbar.setNavigationIcon(R.drawable.ic_me);
        mToolbar.setContentInsetsAbsolute(0, 0);
        mToolbar.setContentInsetStartWithNavigation(0);


        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               ((MainActivity) getActivity()).mDrawerLayout.openDrawer(Gravity.START);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MainFragmentAdapter adapter=new MainFragmentAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        mTabLayout.setTabMode((TabLayout.MODE_SCROLLABLE));//设置TabLayout的模式为滚动模式
        //与viepager进行绑定,TabLayout的标签页通过PagerAdapter的getPagerTitle方法获取
        mTabLayout.setupWithViewPager(viewPager);
    }
}
