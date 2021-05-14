package com.lyl.mvvm.test

import huaan.com.mvvmdemo.http.RetrofitClient
import kotlinx.coroutines.delay

class ArticleRepository : BaseRepository() {

    suspend fun getDatas(): ResponseData<List<Data>> = request {
        RetrofitClient.reqApi.getDatas()
    }
}