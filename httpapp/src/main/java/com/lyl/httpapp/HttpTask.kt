package com.lyl.httpapp

import android.util.Log
import com.alibaba.fastjson.JSON
import java.util.concurrent.Delayed
import java.util.concurrent.TimeUnit

/**
 * Create By: lyl
 * Date: 2019-07-18 10:09
 */
class HttpTask<T> : Runnable, Delayed {


    private var mIHttpRequest: IHttpRequest? = null

    constructor(url: String, requestData: T, httpRequest: IHttpRequest, listener: CallBackListener) {
        mIHttpRequest = httpRequest
        httpRequest.setUrl(url)
        var zz = JSON.toJSONString(requestData)
        httpRequest.setData(zz.toByteArray(Charsets.UTF_8))
        httpRequest.setListener(listener)
    }

    override fun run() {
        try {
            mIHttpRequest!!.execute()
        } catch (e: Exception) {
            Log.d("lyll", "e===${e.toString()}")
            ThreadPoolManager.getInstance().addDelayTask(this)
        }

    }

    override fun compareTo(other: Delayed?): Int {
        return 0
    }

    override fun getDelay(unit: TimeUnit?): Long {
        return unit!!.convert(this.delayTime!! - System.currentTimeMillis(), TimeUnit.MILLISECONDS)
    }


    //    data class DCgongshi(
//            private var delayTime: Long? = null,
//            private var retryCount: Int? = null
//    )
    private var delayTime: Long? = 0
    private var retryCount: Int? = 0

    public fun getDelayTime(): Long? {
        return this.delayTime!!
    }

    public fun getRetryCount(): Int? {
        return this.retryCount
    }

    public fun setDelayTime(delayTime: Long) {
        this.delayTime = delayTime + System.currentTimeMillis()
    }

    public fun setRetryCount(retryCount: Int) {
        this.retryCount = retryCount
    }
}