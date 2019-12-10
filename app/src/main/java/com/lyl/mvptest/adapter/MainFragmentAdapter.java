package com.lyl.mvptest.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * create 2018/8/21
 * author lyl
 */
public class MainFragmentAdapter extends FragmentPagerAdapter {
    List<Fragment> list;
    private String[] TABLAYOUT_ID = {"movie", "book"};
    private FragmentManager fragmetnmanager;

    public MainFragmentAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
        fragmetnmanager = fm;
    }

    @Override
    public Fragment getItem(int arg0) {
        // TODO Auto-generated method stub
        return list.get(arg0);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TABLAYOUT_ID[position];
    }
}
