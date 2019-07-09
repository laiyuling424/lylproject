package com.lyl.wanandroid.ui.fragment.first.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lyl.wanandroid.R
import com.lyl.wanandroid.ui.base.BaseFragment
import kotlinx.android.synthetic.main.main_fragment_layout.*

/**
 * User: lyl
 * Date: 2019-06-11 15:45
 */
class MainFragment:BaseFragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.main_fragment_layout, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}

