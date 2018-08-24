package com.lyl.mvptest.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lyl.mvptest.Book.Bookfragment;
import com.lyl.mvptest.Movie.MovieFragment;
/**
 * create 2018/8/21
 * author lyl
 */
public class MainFragmentAdapter extends FragmentPagerAdapter {
    private  String[] TABLAYOUT_ID={"movie","book"};
    public MainFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment=null;
        switch (position){
            case  0:
                fragment=new MovieFragment();
                break;
            case  1:
                fragment=new Bookfragment();
                break;

        }
        return fragment;
    }

    @Override
    public int getCount() {
        return TABLAYOUT_ID.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TABLAYOUT_ID[position];
    }
}
