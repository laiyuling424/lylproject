package com.lyl.wanandroid.util

import android.provider.Settings
import com.lyl.wanandroid.http.ApiServer
import com.lyl.wanandroid.ui.base.ExecuteOnceObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Create By: lyl
 * Date: 2019-08-12 18:23
 */
class CollectionUtil {

    companion object {

        private val apiGenerate by lazy {
            ApiServer.getApiServer()
        }

        fun collectionWanandroidArticle(id: Int) :Int{
            var response=-1
            apiGenerate.collectInnerArticle(id)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(ExecuteOnceObserver(onExecuteOnceNext = {
                        showToast("文章收藏成功")
                        response=1
                    }, onExecuteOnceError = {
                        MyLog.Logd("collectionWanandroidArticle onExecuteOnceError==>" + it.message)
                        response=0
                    }, onExecuteOnceComplete = {
                        MyLog.Logd("collectionWanandroidArticle onExecuteOnceComplete")
                    }))
            return response
        }

        fun collectionOtherArticle(title: String, author: String, link: String) {
            apiGenerate.collectOutArticle(title,author,link)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(ExecuteOnceObserver(onExecuteOnceNext = {
                        showToast("文章收藏成功")
                    }, onExecuteOnceError = {
                        MyLog.Logd("collectionOtherArticle onExecuteOnceError==>" + it.message)
                    }, onExecuteOnceComplete = {
                        MyLog.Logd("collectionOtherArticle onExecuteOnceComplete")
                    }))
        }

        fun collectionWebPage(name: String, link: String) {
            apiGenerate.collectWeb(name, link)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(ExecuteOnceObserver(onExecuteOnceNext = {
                        showToast("网站收藏成功")
                    }, onExecuteOnceError = {
                        MyLog.Logd("collectionWebPage onExecuteOnceError==>" + it.message)
                    }, onExecuteOnceComplete = {
                        MyLog.Logd("collectionWebPage onExecuteOnceComplete")
                    }))
        }

        fun uncollectArtilceList(id: Int) :Int{
            var response=-1
            apiGenerate.uncollectArtilceList(id)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(ExecuteOnceObserver(onExecuteOnceNext = {
                        showToast("文章已取消收藏")
                        response=1
                    }, onExecuteOnceError = {
                        MyLog.Logd("uncollectArtilceList onExecuteOnceError==>" + it.message)
                        response=0
                    }, onExecuteOnceComplete = {
                        MyLog.Logd("uncollectArtilceList onExecuteOnceComplete")
                    }))
            return response
        }

        fun uncollectCollectionList(id: Int) {
            apiGenerate.uncollectCollectionList(id)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(ExecuteOnceObserver(onExecuteOnceNext = {
                        showToast("文章已取消收藏")
                    }, onExecuteOnceError = {
                        MyLog.Logd("uncollectCollectionList onExecuteOnceError==>" + it.message)
                    }, onExecuteOnceComplete = {
                        MyLog.Logd("uncollectCollectionList onExecuteOnceComplete")
                    }))
        }

        fun unCollectionWebPage(id:Int) {
            apiGenerate.delectCollectWeb(id)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(ExecuteOnceObserver(onExecuteOnceNext = {
                        showToast("网站已取消收藏")
                    }, onExecuteOnceError = {
                        MyLog.Logd("unCollectionWebPage onExecuteOnceError==>" + it.message)
                    }, onExecuteOnceComplete = {
                        MyLog.Logd("unCollectionWebPage onExecuteOnceComplete")
                    }))
        }
    }


}