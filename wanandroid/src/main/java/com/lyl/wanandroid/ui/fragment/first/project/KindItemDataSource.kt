package com.lyl.wanandroid.ui.fragment.first.project

import androidx.paging.DataSource
import androidx.paging.ItemKeyedDataSource
import com.lyl.wanandroid.http.Api
import com.lyl.wanandroid.http.ApiServer
import com.lyl.wanandroid.ui.base.ExecuteOnceObserver
import com.lyl.wanandroid.ui.test_jetpack.paging.ApiGenerate
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * User: lyl
 * Date: 2019-07-19 17:13
 */
class KindItemDataSource : ItemKeyedDataSource<Int, KindBean>() {

    private val ApiGenerate by lazy {
        ApiServer.getApiServer()
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<KindBean>) {
        ApiGenerate.getkindList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(ExecuteOnceObserver(
                        onExecuteOnceNext = {
                            callback.onResult(it.data!!)
                        },
                        onExecuteOnceError = {

                        },
                        onExecuteOnceComplete = {

                        }
                ))
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<KindBean>) {
        ApiGenerate.getkindList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(ExecuteOnceObserver(
                        onExecuteOnceNext = {
                            callback.onResult(it.data!!)
                        },
                        onExecuteOnceError = {

                        },
                        onExecuteOnceComplete = {

                        }
                ))
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<KindBean>) {

    }

    override fun getKey(item: KindBean): Int {
        return item.id!!
    }
}