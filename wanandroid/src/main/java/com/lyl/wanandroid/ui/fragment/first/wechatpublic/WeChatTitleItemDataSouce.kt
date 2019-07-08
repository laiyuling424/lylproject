package com.lyl.wanandroid.ui.fragment.first.wechatpublic

import android.util.Log
import androidx.paging.ItemKeyedDataSource
import com.lyl.wanandroid.http.ApiServer
import com.lyl.wanandroid.ui.base.ExecuteOnceObserver
import com.lyl.wanandroid.ui.test_jetpack.paging.ApiGenerate
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * User: lyl
 * Date: 2019-07-08 15:15
 */
class WeChatTitleItemDataSouce:ItemKeyedDataSource<Int,WeChatPublicListBean>(){
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<WeChatPublicListBean>) {
        apiGenerate.getWeChatPublicList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(ExecuteOnceObserver(onExecuteOnceNext = {
                    Log.d("lyll","next")
                    Log.d("lyll","data="+ it.data!![0]!!.name)
                    callback.onResult(it.data!!)
                },onExecuteOnceError = {
                    Log.d("lyll","error")
                },onExecuteOnceComplete = {
                    Log.d("lyll","complete")
                }))
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<WeChatPublicListBean>) {
        apiGenerate.getWeChatPublicList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(ExecuteOnceObserver(onExecuteOnceNext = {
                    Log.d("lyll","next")
//                    Log.d("lyll","data="+it.get(0).name)
                    callback.onResult(it.data!!)
                },onExecuteOnceError = {
                    Log.d("lyll","error")
                },onExecuteOnceComplete = {
                    Log.d("lyll","complete")
                }))
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<WeChatPublicListBean>) {
        //由于这里不需要向上加载因此省略此处
    }

    override fun getKey(item: WeChatPublicListBean): Int {
        return item.id!!
    }

    private val apiGenerate by lazy {
        ApiServer.getApiServer()
    }

}

