package com.lyl.mvptest.mvp.secondfragment.jetpack_0701_test

import androidx.paging.ItemKeyedDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Create By: lyl
 * Date: 2019-07-01 15:51
 */
class ProvinceItemDataSouce : ItemKeyedDataSource<Long, ProvinceBean>() {

    private val apiGenerate by lazy {
        ApiGenerate.getApiService()
    }

    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<ProvinceBean>) {
        apiGenerate.getProvince()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(ExecuteOnceObserver(onExecuteOnceNext = {
                    callback.onResult(it)
                }, onExecuteOnceError = {
                }, onExecuteOnceComplete = {
                }))
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<ProvinceBean>) {
        apiGenerate.getProvince()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(ExecuteOnceObserver(onExecuteOnceNext = {
                    callback.onResult(it)
                }, onExecuteOnceError = {
                }, onExecuteOnceComplete = {
                }))
    }

//    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<ProvinceBean>) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }

    override fun loadBefore(params: LoadParams<Long>, callback:
    LoadCallback<ProvinceBean>) {
        //由于这里不需要向上加载因此省略此处
    }

    override fun getKey(item: ProvinceBean): Long {
        return item.id!!.toLong()
    }

}