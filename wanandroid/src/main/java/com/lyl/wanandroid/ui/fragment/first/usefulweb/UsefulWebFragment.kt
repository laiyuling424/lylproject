package com.lyl.wanandroid.ui.fragment.first.usefulweb

import android.content.Intent
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
import kotlinx.android.synthetic.main.useful_fragment_layout.*

/**
 * User: lyl
 * Date: 2019-07-11 14:43
 */
class UsefulWebFragment : BaseFragment() , OnItemClickListener<String> {

    var listUsefulWeb:List<UsefulWebBean>?=ArrayList()

    override fun itemClick(t: String, position: Int) {
        val intent = Intent()

        intent.putExtra(Constants.CONTENT_URL, this!!.listUsefulWeb!![position].link)

        MyLog.Logd("url===>"+this!!.listUsefulWeb!![position].link)

        startActivity(WebViewDetailActivity::class.java, intent)
    }

    override val layoutId: Int
        get() = R.layout.useful_fragment_layout

    override fun loadData() {
        getBanner()
    }

    override fun initView() {
        recycle_list_view.let { it!!.onItemClickListener = this }
    }



    private fun getBanner() {
        ApiServer.getApiServer()
                .getUsefulWebList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(
                        ExecuteOnceObserver(onExecuteOnceNext = {
                            var list: java.util.ArrayList<String>? = ArrayList()
                            for (i in 0 until it.data!!.size) {
                                list!!.add(it.data!![i].name!!)
                            }
                            recycle_list_view.setListText(list)
                            listUsefulWeb=it.data
                        }, onExecuteOnceError = {
                            MyLog.Logd("onExecuteOnceError==>" + it.message)
                        }, onExecuteOnceComplete = {
//                            MyLog.Logd("onExecuteOnceComplete")
                        })
                )

    }
}