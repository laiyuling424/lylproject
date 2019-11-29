package com.lyl.wanandroid.http

import com.lyl.wanandroid.WanAdnroidApplication
import com.lyl.wanandroid.http.cookie.CookieJarImpl
import com.lyl.wanandroid.util.MyLog
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Create By: lyl
 * Date: 2019-07-05 17:50
 */
object ApiServer {

    val baseUrl = "https://www.wanandroid.com/"

    private val retrofit = Retrofit.Builder()
            .client(getClient(0))
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    fun getApiServer(): Api = retrofit.create(Api::class.java)

    private fun getClient(time: Long): OkHttpClient {
        var time = time
        if (time < 30000L)
            time = 30000L  //毫秒
        val loggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
            MyLog.Logd("header=====$message")
        })
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
                .connectTimeout(time, TimeUnit.MILLISECONDS)
                //.sslSocketFactory(SSLSocketFactoryUtils.createSSLSocketFactory())
                .readTimeout(time, TimeUnit.MILLISECONDS)
                .writeTimeout(time, TimeUnit.MILLISECONDS)
                .addInterceptor(loggingInterceptor)
                .cookieJar(CookieJarImpl(com.lyl.wanandroid.http.cookie.PersistentCookieStore(WanAdnroidApplication.getContext())))

//                .cookieJar(CookiesManager())
//                .addInterceptor { chain ->
//                    //添加统一请求头
//                    var token = App.getInstance().getUser().getToken()
//                    var uin = App.getInstance().getUser().getUin()
//                    if (uin == null) {
//                        uin = ""
//                    }
//                    if (token == null) {
//                        token = ""
//                    }
//                    val request = chain.request()
//                            .newBuilder()
//                            .addHeader("Authorizations", uin + "#" + token)
//                            .addHeader("api-version", "5.1")
//                            .removeHeader("User-Agent")//移除旧的
//                            .addHeader("User-Agent", WebSettings.getDefaultUserAgent(context))//添加真正的头部
//                            .build()
//                    chain.proceed(request)
//                }
                .build()
    }

}