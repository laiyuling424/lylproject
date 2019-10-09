package com.lyl.wanandroid.ui.activity.search

import android.annotation.SuppressLint
import android.view.View
import androidx.paging.ItemKeyedDataSource
import com.lyl.wanandroid.http.ApiServer
import com.lyl.wanandroid.ui.base.ExecuteOnceObserver
import com.lyl.wanandroid.ui.fragment.first.usefulweb.UsefulWebBean
import com.lyl.wanandroid.util.MyLog
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Create By: lyl
 * Date: 2019-07-11 15:41
 */
class SearchDataSouce:ItemKeyedDataSource<Int, SearchResponseBean>(){

    private val apiGenerate by lazy {
        ApiServer.getApiServer()
    }

    @SuppressLint("CheckResult")
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<SearchResponseBean>) {
        apiGenerate.getSearchResponseList(0,"aa")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe (
                    ExecuteOnceObserver(onExecuteOnceNext = {
                        callback.onResult(it.data!!.datas!!)
                        MyLog.Logd("it.data!!.datas!!===>"+it.data!!.datas!![0].author)
                    },onExecuteOnceError = {
                        MyLog.Logd("search onExecuteOnceError==>" + it.message)
                    },onExecuteOnceComplete = {
                        MyLog.Logd("search onExecuteOnceComplete")
                    })
                )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<SearchResponseBean>) {
        apiGenerate.getSearchResponseList(0,"aa")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe (
                        ExecuteOnceObserver(onExecuteOnceNext = {
                            callback.onResult(it.data!!.datas!!)
                        },onExecuteOnceError = {
                            MyLog.Logd("search onExecuteOnceError==>" + it.message)
                        },onExecuteOnceComplete = {
                            MyLog.Logd("search onExecuteOnceComplete")
                        })
                )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<SearchResponseBean>) {

    }

    override fun getKey(item: SearchResponseBean): Int {
        return item.id!!
    }
}