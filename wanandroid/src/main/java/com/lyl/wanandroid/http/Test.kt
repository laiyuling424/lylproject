package com.lyl.wanandroid.http

import com.lyl.wanandroid.util.MyLog
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

/**
 * Create By: lyl
 * Date: 2019-08-01 10:18
 */
class Test {

    public fun proxy() {
        var api:Api=Proxy.newProxyInstance(Api::class.java.classLoader, arrayOf(Api::class.java), object : InvocationHandler {
            override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any? {
                MyLog.Logd("method==$method")
                return null
            }
        })as Api
        api.getWeChatPublicList()
    }
}