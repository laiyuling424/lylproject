package com.lyl.wanandroid.ui.fragment

import butterknife.ButterKnife
import butterknife.OnClick
import com.lyl.wanandroid.R
import com.lyl.wanandroid.ui.activity.login.LoginActivity
import com.lyl.wanandroid.ui.base.BaseFragment
import com.lyl.wanandroid.util.MyLog
import kotlinx.android.synthetic.main.no_landing.*
import kotlinx.android.synthetic.main.third_fragment_layout.*

/**
 * User: lyl
 * Date: 2019-07-08 14:12
 */
class Thirdfragment: BaseFragment(){
    override val layoutId: Int
        get() = R.layout.third_fragment_layout

    override fun loadData() {

    }

    override fun initView() {
        ButterKnife.bind(this, mView!!)
    }

    @OnClick(R.id.login_img)
    fun login(){
        MyLog.Logd("zzzzzzzz")
        startActivity(LoginActivity::class.java,null)
    }
}