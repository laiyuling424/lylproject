package com.lyl.mvptest.mvp.secondfragment.jetpack_0701_test

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Create By: lyl
 * Date: 2019-07-01 15:39
 */

object ApiGenerate {
    private val retrofit = Retrofit.Builder()
            .baseUrl("http://guolin.tech/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    fun getApiService(): ApiService = retrofit.create(ApiService::class.java)
}