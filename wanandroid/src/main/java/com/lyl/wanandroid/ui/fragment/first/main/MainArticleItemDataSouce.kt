package com.lyl.wanandroid.ui.fragment.first.main

import androidx.paging.ItemKeyedDataSource
import com.lyl.wanandroid.http.ApiServer
import com.lyl.wanandroid.ui.base.ExecuteOnceObserver
import com.lyl.wanandroid.util.MyLog
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * User: lyl
 * Date: 2019-07-10 15:06
 */
class MainArticleItemDataSouce(var page: Int) : ItemKeyedDataSource<Int, MainArticleBean>() {

    private val apiGenerate by lazy {
        ApiServer.getApiServer()
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<MainArticleBean>) {
        apiGenerate.getMainArticleList(page)
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
        apiGenerate.getMainArticleList(page)
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