package com.lyl.mvptest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.lyl.mvptest.adapter.MainFragmentAdapter;
import com.lyl.mvptest.mvp.book.BookFragment;
import com.lyl.mvptest.mvp.movie.MovieFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * create 2018/8/21
 * author lyl
 */
public class MainFragment extends Fragment {
    TabLayout mTabLayout;
    ViewPager viewPager;
    MainFragmentAdapter adapter;
    List<Fragment> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mainfragment_layout, container, false);
        mTabLayout = (TabLayout) view.findViewById(R.id.tablayout);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        list = new ArrayList<>();


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Fragment movieFragment = new MovieFragment();
        Fragment bookFragment = new BookFragment();
        list.add(movieFragment);
        list.add(bookFragment);
        adapter = new MainFragmentAdapter(this.getChildFragmentManager(), list);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);

        mTabLayout.setTabMode((TabLayout.MODE_SCROLLABLE));//设置TabLayout的模式为滚动模式
        //与viepager进行绑定,TabLayout的标签页通过PagerAdapter的getPagerTitle方法获取
        mTabLayout.setupWithViewPager(viewPager);
    }
}
