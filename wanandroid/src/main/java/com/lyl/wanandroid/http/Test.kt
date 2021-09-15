package com.lyl.wanandroid.http

import com.lyl.wanandroid.ui.fragment.wechatpublic.WeChatPublicListBeanResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

/**
 * Create By: lyl
 * Date: 2019-08-01 10:18
 */


public fun main() {
    var api: Api = Proxy.newProxyInstance(Api::class.java.classLoader, arrayOf(Api::class.java), object : InvocationHandler {
        override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any? {
//            MyLog.Logd("method==$method")
            println("method==$method")
            return null
        }
    }) as Api
    val weChatPublicList = api.getWeChatPublicList()

    val call = ApiServer.getApiServer().getWeChatPublicListt()
    call.enqueue(object : Callback<WeChatPublicListBeanResponse> {
        override fun onResponse(call: Call<WeChatPublicListBeanResponse>, response: Response<WeChatPublicListBeanResponse>) {
            println("onResponse")
        }

        override fun onFailure(call: Call<WeChatPublicListBeanResponse>, t: Throwable) {
            println("onFailure")
        }

    })

    Thread.sleep(4000)
    println("end")

}
