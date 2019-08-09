package com.lyl.wanandroid.ui.fragment.wechatpublic

import androidx.paging.ItemKeyedDataSource
import com.lyl.wanandroid.http.ApiServer
import com.lyl.wanandroid.ui.base.ExecuteOnceObserver
import com.lyl.wanandroid.util.MyLog
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
        MyLog.Logd("loadInitial   idd====>"+ WeChatPublicFragment.idd!!.value)
        MyLog.Logd("loadInitial   id====>"+ id)
        apiGenerate.getWeChatPublicHistoryData(WeChatPublicFragment.idd!!.value!!,1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(ExecuteOnceObserver(onExecuteOnceNext = {
//                    Log.d("lyll","next")
//                    Log.d("lyll","data="+ it.data!!.datas!![0].link)
                    MyLog.Logd("loadInitial====>"+ it.data!!.datas!![0].author)
                    callback.onResult(it.data!!.datas!!)
                },onExecuteOnceError = {
//                    Log.d("lyll","error")
                },onExecuteOnceComplete = {
//                    Log.d("lyll","complete")
                }))
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<WeChatContentBean>) {
        MyLog.Logd("loadAfter   idd====>"+ WeChatPublicFragment.idd!!.value)
        MyLog.Logd("loadAfter   id====>"+ id)
        apiGenerate.getWeChatPublicHistoryData(WeChatPublicFragment.idd!!.value!!,1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(ExecuteOnceObserver(onExecuteOnceNext = {
                    MyLog.Logd("loadAfter====>"+ it.data!!.datas!![0].author)
                    callback.onResult(it.data!!.datas!!)
                },onExecuteOnceError = {
//                    Log.d("lyll","error")
                },onExecuteOnceComplete = {
//                    Log.d("lyll","complete")
                }))
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<WeChatContentBean>) {
        MyLog.Logd("loadBefore   idd====>"+ WeChatPublicFragment.idd!!.value)
        MyLog.Logd("loadBefore   id====>"+ id)
        apiGenerate.getWeChatPublicHistoryData(WeChatPublicFragment.idd!!.value!!,1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(ExecuteOnceObserver(onExecuteOnceNext = {
                    MyLog.Logd("loadBefore====>"+ it.data!!.datas!![0].author)
                    callback.onResult(it.data!!.datas!!)
                },onExecuteOnceError = {
                    //                    Log.d("lyll","error")
                },onExecuteOnceComplete = {
                    //                    Log.d("lyll","complete")
                }))
    }

    override fun getKey(item: WeChatContentBean): Int {
        return item.id!!
    }
}