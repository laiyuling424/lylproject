package com.lyl.wanandroid.ui.test_jetpack.paging

import androidx.paging.ItemKeyedDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * User: lyl
 * Date: 2019-06-13 11:08
 */

class ByItemDataSource : ItemKeyedDataSource<Long, GithubAccount>() {

    private val mGitHubService by lazy {
        ApiGenerate.getGitHubService()
    }

    override fun loadInitial(params: LoadInitialParams<Long>, callback:
    LoadInitialCallback<GithubAccount>) {
        mGitHubService.getGithubAccount(0, params.requestedLoadSize)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(ExecuteOnceObserver({
                    callback.onResult(it)
                }))
    }

    override fun loadAfter(params: LoadParams<Long>, callback:
    LoadCallback<GithubAccount>) {
        mGitHubService.getGithubAccount(params.key, params.requestedLoadSize)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(ExecuteOnceObserver(onExecuteOnceNext = {
                    callback.onResult(it)
                }))
    }

    override fun loadBefore(params: LoadParams<Long>, callback:
    LoadCallback<GithubAccount>) {
        //由于这里不需要向上加载因此省略此处
    }

    override fun getKey(item: GithubAccount): Long = item.id.toLong()
}