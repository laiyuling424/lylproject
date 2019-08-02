package com.lyl.wanandroid.ui.fragment

import android.view.View
import androidx.lifecycle.Observer
import butterknife.ButterKnife
import butterknife.OnClick
import com.lyl.wanandroid.R
import com.lyl.wanandroid.WanAdnroidApplication
import com.lyl.wanandroid.ui.activity.SettingActivity
import com.lyl.wanandroid.ui.activity.collectarticle.CollectArticleActivity
import com.lyl.wanandroid.ui.activity.collectweb.CollectWebActivity
import com.lyl.wanandroid.ui.activity.LoginActivity
import com.lyl.wanandroid.ui.bean.LoginBean
import com.lyl.wanandroid.ui.base.BaseFragment
import com.lyl.wanandroid.util.LiveDataBus
import com.lyl.wanandroid.util.SharedPreferencesUtil
import com.lyl.wanandroid.util.showToast
import kotlinx.android.synthetic.main.is_landing.*
import kotlinx.android.synthetic.main.third_fragment_layout.*

/**
 * User: lyl
 * Date: 2019-07-08 14:12
 */
class Thirdfragment : BaseFragment() {

    override val layoutId: Int
        get() = R.layout.third_fragment_layout

    override fun loadData() {

    }

    override fun initView() {
        ButterKnife.bind(this, mView!!)

        init()
    }

    override fun onResume() {
        super.onResume()
        init()
    }

    private fun init() {

        if (SharedPreferencesUtil.getBoolean(WanAdnroidApplication.getContext(), "islanding", false)) {
            no_landing.visibility = View.GONE
            is_landing.visibility = View.VISIBLE
        } else {
            no_landing.visibility = View.VISIBLE
            is_landing.visibility = View.GONE
        }

        LiveDataBus.getInstance().with("userdata", LoginBean::class.java).observe(
                this@Thirdfragment, Observer<LoginBean> {
            article_num.text = it.collectIds!!.size.toString()
        }
        )

        LiveDataBus.getInstance().with("userArticleNum", Int::class.java).observe(
                this@Thirdfragment, Observer<Int> {
            article_num.text = it.toString()
        }
        )

        LiveDataBus.getInstance().with("userWebNum", Int::class.java).observe(
                this@Thirdfragment, Observer<Int> {
            username.text = it.toString()
        }
        )

        LiveDataBus.getInstance().with("userName", String::class.java).observe(
                this@Thirdfragment, Observer<String> {
            web_num.text = it
        }
        )

    }

    @OnClick(R.id.login_img,R.id.headview, R.id.ll_article, R.id.ll_web, R.id.ll_project, R.id.ll_issue, R.id.l_article, R.id.l_web, R.id.l_project, R.id.l_issue)
    fun onClick(view: View) {
        when (view.id) {
            R.id.login_img -> {
                startActivity(LoginActivity::class.java, null)
            }
            R.id.headview -> {
                startActivity(SettingActivity::class.java, null)
            }
            R.id.l_article, R.id.ll_article -> {
                startActivity(CollectArticleActivity::class.java, null)
            }
            R.id.l_web, R.id.ll_web -> {
                startActivity(CollectWebActivity::class.java, null)
            }
            R.id.l_project, R.id.ll_project -> {
                //wanandroid没有
//                startActivity(MyProjectActivity::class.java, null)
                showToast("待完善")
            }
            R.id.l_issue, R.id.ll_issue -> {
                //wanandroid没有
//                startActivity(MyIssueActivity::class.java, null)
                showToast("待完善")
            }
        }

    }
}