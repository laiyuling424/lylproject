package com.lyl.wanandroid.ui.test_jetpack.paging

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * User: lyl
 * Date: 2019-06-13 11:07
 */

object ApiGenerate {
    private val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    fun getGitHubService(): GitHubService = retrofit.create(GitHubService::class.java)
}