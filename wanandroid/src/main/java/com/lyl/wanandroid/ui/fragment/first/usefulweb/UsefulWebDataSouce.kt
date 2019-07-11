package com.lyl.wanandroid.ui.fragment.first.usefulweb

import android.annotation.SuppressLint
import androidx.paging.ItemKeyedDataSource
import com.lyl.wanandroid.http.ApiServer
import com.lyl.wanandroid.ui.base.ExecuteOnceObserver
import com.lyl.wanandroid.util.MyLog
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * User: lyl
 * Date: 2019-07-11 15:41
 */
class UsefulWebDataSouce:ItemKeyedDataSource<Int,UsefulWebBean>(){

    private val apiGenerate by lazy {
        ApiServer.getApiServer()
    }

    @SuppressLint("CheckResult")
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<UsefulWebBean>) {
        apiGenerate.getUsefulWebList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe (
                    ExecuteOnceObserver(onExecuteOnceNext = {
                        callback.onResult(it.data!!)
                    },onExecuteOnceError = {
                        MyLog.Logd("onExecuteOnceError==>" + it.message)
                    },onExecuteOnceComplete = {
                        MyLog.Logd("onExecuteOnceComplete")
                    })
                )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<UsefulWebBean>) {
        apiGenerate.getUsefulWebList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe (
                        ExecuteOnceObserver(onExecuteOnceNext = {
                            callback.onResult(it.data!!)
                        },onExecuteOnceError = {
                            MyLog.Logd("onExecuteOnceError==>" + it.message)
                        },onExecuteOnceComplete = {
                            MyLog.Logd("onExecuteOnceComplete")
                        })
                )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<UsefulWebBean>) {

    }

    override fun getKey(item: UsefulWebBean): Int {
        return item.id!!
    }
}