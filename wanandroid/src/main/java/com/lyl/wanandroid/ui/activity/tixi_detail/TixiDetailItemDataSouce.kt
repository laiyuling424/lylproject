package com.lyl.wanandroid.ui.activity.tixi_detail

import androidx.paging.ItemKeyedDataSource
import com.lyl.wanandroid.http.ApiServer
import com.lyl.wanandroid.ui.base.ExecuteOnceObserver
import com.lyl.wanandroid.ui.fragment.first.main.MainArticleBean
import com.lyl.wanandroid.util.MyLog
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Create By: lyl
 * Date: 2019-07-13 15:08
 */

class TixiDetailItemDataSouce(var cid: Int) : ItemKeyedDataSource<Int, MainArticleBean>() {

    val apiGenerate by lazy {
        ApiServer.getApiServer()
    }

    private var page = 0

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<MainArticleBean>) {
        apiGenerate.getTixiArticleList(page, cid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(ExecuteOnceObserver(onExecuteOnceNext = {
                    page = it.data?.curPage!!
                    callback.onResult(it.data!!.datas!!)
                }, onExecuteOnceError = {
                    MyLog.Logd("onExecuteOnceError==>" + it.message)
                }, onExecuteOnceComplete = {
                    MyLog.Logd("onExecuteOnceComplete")
                }))
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<MainArticleBean>) {
        apiGenerate.getTixiArticleList(page, cid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(ExecuteOnceObserver(onExecuteOnceNext = {
                    page = it.data?.curPage!!
                    callback.onResult(it.data!!.datas!!)
                }, onExecuteOnceError = {
                }, onExecuteOnceComplete = {
                }))
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<MainArticleBean>) {

    }

    override fun getKey(item: MainArticleBean): Int {
        return item.id!!
    }
}