package com.lyl.wanandroid.ui.activity.collectweb

import androidx.paging.ItemKeyedDataSource
import com.lyl.wanandroid.http.ApiServer
import com.lyl.wanandroid.ui.base.ExecuteOnceObserver
import com.lyl.wanandroid.ui.bean.CollectWebBean
import com.lyl.wanandroid.util.LiveDataBus
import com.lyl.wanandroid.util.MyLog
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * User: lyl
 * Date: 2019-08-02 15:48
 */
class CollectWebItemDataSouce : ItemKeyedDataSource<Int, CollectWebBean>() {

    private val apiGenerate by lazy {
        ApiServer.getApiServer()
    }

    var firstId = -1

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<CollectWebBean>) {
        apiGenerate.collectWebList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(ExecuteOnceObserver(
                        onExecuteOnceNext = {
                            firstId = it.data!![0].id!!
                            LiveDataBus.getInstance().with("userWebNum", Int::class.java).postValue(it.data!!.size)
                            callback.onResult(it.data!!)
                        },
                        onExecuteOnceError = {
                            MyLog.Loge("error", "CollectWeb error=${it.message}")
                        },
                        onExecuteOnceComplete = {

                        }
                ))
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<CollectWebBean>) {
        apiGenerate.collectWebList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(ExecuteOnceObserver(
                        onExecuteOnceNext = {
                            if (firstId != it.data!![0].id!!) callback.onResult(it.data!!)
                        },
                        onExecuteOnceError = {
                            MyLog.Loge("error", "CollectWeb error=${it.message}")
                        },
                        onExecuteOnceComplete = {

                        }
                ))
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<CollectWebBean>) {

    }

    override fun getKey(item: CollectWebBean): Int {
        return item.id!!
    }
}