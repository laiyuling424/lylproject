package com.lyl.wanandroid.ui.fragment.first.tixi

import androidx.paging.DataSource
import androidx.paging.ItemKeyedDataSource
import com.lyl.wanandroid.http.ApiServer
import com.lyl.wanandroid.ui.base.ExecuteOnceObserver
import com.lyl.wanandroid.util.MyLog
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Create By: lyl
 * Date: 2019-07-13 09:06
 */
class TixiItemDataSouce() : ItemKeyedDataSource<Int, TixiBean>() {

    private val apiGenerate by lazy {
        ApiServer.getApiServer()
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<TixiBean>) {
        apiGenerate.getTixiList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(ExecuteOnceObserver(
                        onExecuteOnceNext = {
                            callback.onResult(it.data!!)
                        },
                        onExecuteOnceError = {
                            MyLog.Logd("Tixi onExecuteOnceError===>"+it.message)
                        },
                        onExecuteOnceComplete = {
                            MyLog.Logd("Tixi onExecuteOnceComplete")
                        }
                ))
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<TixiBean>) {
        apiGenerate.getTixiList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(ExecuteOnceObserver(
                        onExecuteOnceNext = {
                            callback.onResult(it.data!!)
                        },
                        onExecuteOnceError = {
                            MyLog.Logd("Tixi onExecuteOnceError")
                        },
                        onExecuteOnceComplete = {
                            MyLog.Logd("Tixi onExecuteOnceComplete")
                        }
                ))
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<TixiBean>) {

    }

    override fun getKey(item: TixiBean): Int {
        return item.id!!
    }
}