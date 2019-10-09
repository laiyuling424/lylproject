package com.lyl.wanandroid.http

import com.lyl.wanandroid.ui.bean.LoginBean
import com.lyl.wanandroid.ui.activity.search.SearchResponseListBean
import com.lyl.wanandroid.ui.activity.search.SearchWordBean
import com.lyl.wanandroid.ui.base.HttpResponse
import com.lyl.wanandroid.ui.bean.CollectArticleListBean
import com.lyl.wanandroid.ui.bean.CollectWebBean
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
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Create By: lyl
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

    //register
    @POST("user/register")
    fun register(@Query("username") username: String,@Query("password") password:String,@Query("repassword") repassword:String): Observable<ResponseBody>

    //logout
    @GET("user/logout/json")
    fun logout(): Observable<ResponseBody>

    //收藏文章列表
    @GET("lg/collect/list/{page}/json")
    fun collectArticleList(@Path("page") page: Int):  Observable<HttpResponse<CollectArticleListBean>>

    //收藏站内文章
    @POST("lg/collect/{id}/json")
    fun collectInnerArticle(@Path("id") id: Int):  Observable<ResponseBody>

    //收藏站外文章  title，author，link
    @POST("lg/collect/add/json")
    fun collectOutArticle(@Query("title") title: String,@Query("author") author: String,@Query("link") link: String):  Observable<ResponseBody>

    //收藏页面取消收藏
    //TODO 没有做
    @POST("lg/collect/{id}/json")
    fun uncollectCollectionList(@Path("id") page: Int):  Observable<ResponseBody>
    //文章列表取消收藏
    @POST("lg/uncollect_originId/{id}/json")
    fun uncollectArtilceList(@Path("id") page: Int):  Observable<ResponseBody>

    //收藏网站列表
    @GET("lg/collect/usertools/json")
    fun collectWebList():  Observable<HttpResponse<List<CollectWebBean>>>

    //收藏网址
    @POST("lg/collect/addtool/json")
    fun collectWeb(@Query("name") name: String,@Query("link") link: String):  Observable<ResponseBody>

    //编辑收藏网站
    @GET("lg/collect/updatetool/json")
    fun editCollectWeb(@Query("id") id: String,@Query("name") name: String,@Query("link") link: String):  Observable<ResponseBody>

    //删除收藏网站
    @GET("lg/collect/deletetool/json")
    fun delectCollectWeb(@Query("id") id: Int):  Observable<ResponseBody>
}