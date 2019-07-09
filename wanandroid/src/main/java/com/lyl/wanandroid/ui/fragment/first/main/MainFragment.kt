package com.lyl.wanandroid.ui.fragment.first.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.lyl.wanandroid.R
import com.lyl.wanandroid.ui.base.BaseFragment
import com.lyl.wanandroid.WanAdnroidApplication.Companion.context


/**
 * User: lyl
 * Date: 2019-06-11 15:45
 */
class MainFragment:BaseFragment(){



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(com.lyl.wanandroid.R.layout.main_fragment_layout, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    internal class Adapter : PagerAdapter() {
        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view==`object`
        }

        override fun getCount(): Int {
           return Int.MAX_VALUE
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            var view= LayoutInflater.from(context).inflate(R.layout.banner_viewpager,null);
            var img= view.findViewById<ImageView>(R.id.img_banner)
            Glide.with(context).load("url").into(img)
            container.addView(view)
            return view
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)
        }
    }
}

