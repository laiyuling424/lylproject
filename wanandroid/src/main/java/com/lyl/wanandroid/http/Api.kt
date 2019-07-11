package com.lyl.wanandroid.http

import com.lyl.wanandroid.ui.bean.HttpResponse
import com.lyl.wanandroid.ui.fragment.first.main.MainArticleBodyBean
import com.lyl.wanandroid.ui.fragment.first.main.MianBannerBean
import com.lyl.wanandroid.ui.fragment.first.usefulweb.UsefulWebBean
import com.lyl.wanandroid.ui.fragment.wechatpublic.WeChatContentListBean
import com.lyl.wanandroid.ui.fragment.wechatpublic.WeChatPublicListBeanResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * User: lyl
 * Date: 2019-07-05 17:50
 */
interface Api {

    //公众号列表
    @GET("wxarticle/chapters/json")
    fun getWeChatPublicList():Observable<WeChatPublicListBeanResponse>

    //某个公众号历史数据
    /**
     * @param id 公众号列表id
     * @param page 页码
     */
    @GET("wxarticle/list/{id}/{page}/json")
    fun getWeChatPublicHistoryData(@Path("id") id: Int,@Path("page") page: Int):Observable<HttpResponse<WeChatContentListBean>>

    //公众号列表
    @GET("banner/json")
    fun getMainBanner():Observable<HttpResponse<List<MianBannerBean>>>

    //首页文章列表
    @GET("article/list/{page}/json")
    fun getMainArticleList(@Path("page") page: Int):Observable<HttpResponse<MainArticleBodyBean>>


    //常用网站
    @GET("friend/json")
    fun getUsefulWebList():Observable<HttpResponse<List<UsefulWebBean>>>
}