package com.lyl.wanandroid.ui.fragment.first.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.lyl.wanandroid.Constants
import com.lyl.wanandroid.R
import com.lyl.wanandroid.http.ApiServer
import com.lyl.wanandroid.listener.OnItemClickListener
import com.lyl.wanandroid.ui.activity.WebViewDetailActivity
import com.lyl.wanandroid.ui.base.BaseFragment
import com.lyl.wanandroid.ui.base.ExecuteOnceObserver
import com.lyl.wanandroid.util.MyLog
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.main_fragment_layout.*


/**
 * Create By: lyl
 * Date: 2019-06-11 15:45
 */
class MainFragment : BaseFragment(), OnItemClickListener<MainArticleBean> {
    override val layoutId: Int
        get() = R.layout.main_fragment_layout

    override fun loadData() {

    }

    override fun initView() {

    }

    override fun itemClick(t: MainArticleBean, position: Int) {
        val intent = Intent()

        intent.putExtra(Constants.CONTENT_Id, t.id)
        intent.putExtra(Constants.CONTENT_URL, t.link)
        intent.putExtra(Constants.CONTENT_AUTHOR, t.author)
        intent.putExtra(Constants.CONTENT_CHAPTER, t.chapterName)
        intent.putExtra(Constants.CONTENT_CHAPTER_ID, t.chapterId)
        intent.putExtra(Constants.CONTENT_TITLE, t.title)

        startActivity(WebViewDetailActivity::class.java, intent)
    }

    var viewModelMainArticle: ViewModelMainArticle? = null

//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return inflater.inflate(R.layout.main_fragment_layout, container, false)
//    }

    @SuppressLint("WrongConstant")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val linearLayoutManager = LinearLayoutManager(this@MainFragment.activity)
        linearLayoutManager.orientation = LinearLayout.VERTICAL
        recycle_view.layoutManager = linearLayoutManager

        var mMainArticleAdapter = MainArticleAdapter()
        mMainArticleAdapter.let { it!!.itemClickListener = this }
        recycle_view.adapter = mMainArticleAdapter

        viewModelMainArticle = getviewModelMainArticle(0)
        viewModelMainArticle!!.articleLists.observe(this, Observer(mMainArticleAdapter!!::submitList))

        getBanner()

    }

    override fun onResume() {
        super.onResume()
    }


    private fun getBanner() {
        ApiServer.getApiServer().getMainBanner()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(ExecuteOnceObserver(onExecuteOnceNext = {
                    var bannerList: ArrayList<String>? = ArrayList()
                    for (i in 0 until it.data!!.size) {

                        bannerList!!.add(it.data!![i].imagePath!!)
                    }
                    slideshowView.setUrlList(bannerList)
                }, onExecuteOnceError = {
                    MyLog.Logd("error=" + it.message)
                }, onExecuteOnceComplete = {

                }))

    }

    private fun getviewModelMainArticle(id: Int): ViewModelMainArticle {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return ViewModelMainArticle(id) as T
            }
        })[ViewModelMainArticle::class.java]
    }
}

