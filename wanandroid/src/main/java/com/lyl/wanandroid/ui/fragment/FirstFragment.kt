package com.lyl.wanandroid.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.lyl.wanandroid.R
import com.lyl.wanandroid.ui.base.BaseFragment
import com.lyl.wanandroid.ui.fragment.first.home.HomeFragment
import com.lyl.wanandroid.ui.fragment.wechatpublic.WeChatPublicFragment
import java.util.ArrayList

/**
 * User: lyl
 * Date: 2019-07-08 14:11
 */
class FirstFragment: BaseFragment(){


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
         val view = inflater.inflate(R.layout.first_fragment_layout, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        var adapter=Adapter(this@FirstFragment.childFragmentManager)
//
//        adapter.addFragment(WeChatPublicFragment(),"home")
//
//        var viewPager:ViewPager=view!!.findViewById(R.id.viewpager)
//        var tablayout:TabLayout=view!!.findViewById(R.id.tablayout)
//
//        viewPager.adapter=adapter
//        viewPager.currentItem=0
//
//        tablayout.tabMode = TabLayout.MODE_SCROLLABLE//设置TabLayout的模式为滚动模式
//        //与viepager进行绑定,TabLayout的标签页通过PagerAdapter的getPagerTitle方法获取
//        tablayout.setupWithViewPager(viewPager)
    }

    internal class Adapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        private val mFragments = ArrayList<Fragment>()
        private val mFragmentTitles = ArrayList<String>()

        fun addFragment(fragment: Fragment, title: String) {
            mFragments.add(fragment)
            mFragmentTitles.add(title)
        }

        override fun getItem(position: Int): Fragment {
            return mFragments[position]
        }

        override fun getCount(): Int {
            return mFragments.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mFragmentTitles[position]
        }
    }

}