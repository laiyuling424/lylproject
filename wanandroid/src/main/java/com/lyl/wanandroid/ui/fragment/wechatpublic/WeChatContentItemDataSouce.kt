package com.lyl.wanandroid.ui.fragment.wechatpublic

import androidx.paging.ItemKeyedDataSource
import com.lyl.wanandroid.http.ApiServer
import com.lyl.wanandroid.ui.base.ExecuteOnceObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * User: lyl
 * Date: 2019-07-08 18:46
 */
class WeChatContentItemDataSouce(val id : Int): ItemKeyedDataSource<Int,WeChatContentBean>(){


    private val apiGenerate by lazy {
        ApiServer.getApiServer()
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<WeChatContentBean>) {
        apiGenerate.getWeChatPublicHistoryData(id,1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(ExecuteOnceObserver(onExecuteOnceNext = {
//                    Log.d("lyll","next")
//                    Log.d("lyll","data="+ it.data!!.datas!![0].link)
                    callback.onResult(it.data!!.datas!!)
                },onExecuteOnceError = {
//                    Log.d("lyll","error")
                },onExecuteOnceComplete = {
//                    Log.d("lyll","complete")
                }))
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<WeChatContentBean>) {
        apiGenerate.getWeChatPublicHistoryData(id,1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(ExecuteOnceObserver(onExecuteOnceNext = {
//                    Log.d("lyll","next")

                    callback.onResult(it.data!!.datas!!)
                },onExecuteOnceError = {
//                    Log.d("lyll","error")
                },onExecuteOnceComplete = {
//                    Log.d("lyll","complete")
                }))
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<WeChatContentBean>) {

    }

    override fun getKey(item: WeChatContentBean): Int {
        return item.id!!
    }
}