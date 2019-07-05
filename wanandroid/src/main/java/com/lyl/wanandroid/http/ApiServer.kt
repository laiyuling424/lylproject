package com.lyl.wanandroid.http

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * User: lyl
 * Date: 2019-07-05 17:50
 */
object ApiServer{

    val baseUrl="https://www.wanandroid.com/"

    private val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    fun getApiServer(): Api = retrofit.create(Api::class.java)
}