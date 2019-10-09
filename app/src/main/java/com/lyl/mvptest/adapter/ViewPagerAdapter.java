package com.lyl.mvptest.adapter;



import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * Create By: lyl
 * Date: 2019/5/5 5:22 PM
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    private FragmentManager fragmetnmanager;
    private List<Fragment> listfragment;

    //定义构造带两个参数
    public ViewPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.fragmetnmanager = fm;
        this.listfragment = list;
    }

    @Override
    public Fragment getItem(int arg0) {
        // TODO Auto-generated method stub
        return listfragment.get(arg0);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return listfragment.size();
    }


}