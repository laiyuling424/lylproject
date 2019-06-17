package com.lyl.wanandroid.ui.test_jetpack.paging

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * User: lyl
 * Date: 2019-06-13 11:06
 */

interface GitHubService {
    @GET("users")
    fun getGithubAccount(@Query("since") id: Long, @Query("per_page") perPage: Int):
            Observable<List<GithubAccount>>
}