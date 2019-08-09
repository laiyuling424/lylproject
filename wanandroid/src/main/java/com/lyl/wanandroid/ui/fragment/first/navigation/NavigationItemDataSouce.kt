package com.lyl.wanandroid.ui.fragment.first.tixi

import androidx.paging.DataSource
import androidx.paging.ItemKeyedDataSource
import com.lyl.wanandroid.http.ApiServer
import com.lyl.wanandroid.ui.base.ExecuteOnceObserver
import com.lyl.wanandroid.util.MyLog
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * User: lyl
 * Date: 2019-07-13 09:06
 */
class NavigationItemDataSouce() : ItemKeyedDataSource<Int, NavigationListBean>() {

    private val apiGenerate by lazy {
        ApiServer.getApiServer()
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<NavigationListBean>) {
        apiGenerate.getNavigationList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(ExecuteOnceObserver(
                        onExecuteOnceNext = {
                            callback.onResult(it.data!!)
                            MyLog.Logd("Navigation onExecuteOnceNext")
                        },
                        onExecuteOnceError = {
                            MyLog.Logd("Navigation onExecuteOnceError===>"+it.message)
                        },
                        onExecuteOnceComplete = {
                            MyLog.Logd("Navigation onExecuteOnceComplete")
                        }
                ))
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<NavigationListBean>) {
        apiGenerate.getNavigationList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(ExecuteOnceObserver(
                        onExecuteOnceNext = {
                            callback.onResult(it.data!!)
                        },
                        onExecuteOnceError = {
                            MyLog.Logd("Navigation onExecuteOnceError")
                        },
                        onExecuteOnceComplete = {
                            MyLog.Logd("Navigation onExecuteOnceComplete")
                        }
                ))
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<NavigationListBean>) {

    }

    override fun getKey(item: NavigationListBean): Int {
        return item.cid!!
    }
}