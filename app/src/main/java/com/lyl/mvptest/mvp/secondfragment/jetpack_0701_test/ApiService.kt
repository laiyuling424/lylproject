package com.lyl.mvptest.mvp.secondfragment.jetpack_0701_test

import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Create By: lyl
 * Date: 2019-07-01 15:41
 */

interface ApiService {
    @GET("api/china")
    fun getProvince(): Observable<List<ProvinceBean>>
}