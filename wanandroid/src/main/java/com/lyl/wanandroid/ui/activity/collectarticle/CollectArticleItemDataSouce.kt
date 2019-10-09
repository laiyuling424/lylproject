package com.lyl.wanandroid.ui.activity.collectarticle

import androidx.paging.ItemKeyedDataSource
import com.lyl.wanandroid.http.ApiServer
import com.lyl.wanandroid.ui.base.ExecuteOnceObserver
import com.lyl.wanandroid.ui.bean.CollectArticleBean
import com.lyl.wanandroid.util.LiveDataBus
import com.lyl.wanandroid.util.MyLog
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Create By: lyl
 * Date: 2019-08-02 14:27
 */
class CollectArticleItemDataSouce : ItemKeyedDataSource<Int, CollectArticleBean>() {

    private val apiGenerate by lazy {
        ApiServer.getApiServer()
    }

    private var page: Int = 0

    private var pageCount: Int = 0

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<CollectArticleBean>) {
        apiGenerate.collectArticleList(page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(ExecuteOnceObserver(onExecuteOnceNext = {
                    page = it.data?.curPage!!
                    pageCount = it.data?.pageCount!!
                    LiveDataBus.getInstance().with("userArticleNum", Int::class.java).postValue(it.data!!.total)
                    callback.onResult(it.data!!.datas!!)
                }, onExecuteOnceError = {
                    MyLog.Logd("onExecuteOnceError==>" + it.message)
                }, onExecuteOnceComplete = {
                    MyLog.Logd("onExecuteOnceComplete")
                }))
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<CollectArticleBean>) {
        if (page >= pageCount) return
        apiGenerate.collectArticleList(page)
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

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<CollectArticleBean>) {

    }

    override fun getKey(item: CollectArticleBean): Int {
        return item.id!!
    }
}