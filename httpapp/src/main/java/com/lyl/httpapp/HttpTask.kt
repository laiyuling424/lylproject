package com.lyl.httpapp

import com.alibaba.fastjson.JSON

/**
 * User: lyl
 * Date: 2019-07-18 10:09
 */
class HttpTask<T> : Runnable {

    private var mIHttpRequest: IHttpRequest? = null

    constructor(url: String, requestData: T, httpRequest: IHttpRequest, listener: CallBackListener) {
        mIHttpRequest = httpRequest
        httpRequest.setUrl(url)
        var zz = JSON.toJSONString(requestData)
        httpRequest.setData(zz.toByteArray(Charsets.UTF_8))
        httpRequest.setListener(listener)
    }

    override fun run() {
        mIHttpRequest!!.execute()
    }
}