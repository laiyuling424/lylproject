package com.lyl.mvptest.mvp.jetpack_0701_test

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * User: lyl
 * Date: 2019-07-01 15:41
 */

interface ApiService {
    @GET("api/china")
    fun getProvince():Observable<List<ProvinceBean>>
}