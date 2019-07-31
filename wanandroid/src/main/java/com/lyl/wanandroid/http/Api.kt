package com.lyl.wanandroid.http

import com.lyl.wanandroid.ui.activity.login.LoginBean
import com.lyl.wanandroid.ui.activity.search.SearchResponseListBean
import com.lyl.wanandroid.ui.activity.search.SearchWordBean
import com.lyl.wanandroid.ui.base.HttpResponse
import com.lyl.wanandroid.ui.fragment.first.main.MainArticleBodyBean
import com.lyl.wanandroid.ui.fragment.first.main.MianBannerBean
import com.lyl.wanandroid.ui.fragment.first.project.KindBean
import com.lyl.wanandroid.ui.fragment.first.project.KindContentListBean
import com.lyl.wanandroid.ui.fragment.first.tixi.NavigationListBean
import com.lyl.wanandroid.ui.fragment.first.tixi.TixiBean
import com.lyl.wanandroid.ui.fragment.first.usefulweb.UsefulWebBean
import com.lyl.wanandroid.ui.fragment.wechatpublic.WeChatContentListBean
import com.lyl.wanandroid.ui.fragment.wechatpublic.WeChatPublicListBeanResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * User: lyl
 * Date: 2019-07-05 17:50
 */
interface Api {

    //公众号列表
    @GET("wxarticle/chapters/json")
    fun getWeChatPublicList(): Observable<WeChatPublicListBeanResponse>

    //某个公众号历史数据
    /**
     * @param id 公众号列表id
     * @param page 页码
     */
    @GET("wxarticle/list/{id}/{page}/json")
    fun getWeChatPublicHistoryData(@Path("id") id: Int, @Path("page") page: Int): Observable<HttpResponse<WeChatContentListBean>>

    //banner
    @GET("banner/json")
    fun getMainBanner(): Observable<HttpResponse<List<MianBannerBean>>>

    //首页文章列表
    @GET("article/list/{page}/json")
    fun getMainArticleList(@Path("page") page: Int): Observable<HttpResponse<MainArticleBodyBean>>


    //常用网站
    @GET("friend/json")
    fun getUsefulWebList(): Observable<HttpResponse<List<UsefulWebBean>>>

    //搜索热词
    @GET("/hotkey/json")
    fun getSearchWordsList(): Observable<HttpResponse<List<SearchWordBean>>>

    //https://www.wanandroid.com/article/query/0/json?k=aa
    //搜索
    @POST("article/query/{page}/json")
    fun getSearchResponseList(@Path("page") page: Int, @Query("k") k: String): Observable<HttpResponse<SearchResponseListBean>>

    //体系
    @GET("tree/json")
    fun getTixiList(): Observable<HttpResponse<List<TixiBean>>>

    //体系文章列表
    @GET("article/list/{page}/json")
    fun getTixiArticleList(@Path("page") page: Int,@Query("cid") cid:Int): Observable<HttpResponse<MainArticleBodyBean>>

    //导航
    @GET("navi/json")
    fun getNavigationList(): Observable<HttpResponse<List<NavigationListBean>>>

    //项目列表
    @GET("project/tree/json")
    fun getkindList(): Observable<HttpResponse<List<KindBean>>>

    //项目列表详情列表
    @GET("project/list/{page}/json")
    fun getkindContentList(@Path("page") page: Int,@Query("cid") cid:Int): Observable<HttpResponse<KindContentListBean>>

    //login
    @POST("user/login")
    fun login(@Query("username") username: String,@Query("password") password:String): Observable<HttpResponse<LoginBean>>
}