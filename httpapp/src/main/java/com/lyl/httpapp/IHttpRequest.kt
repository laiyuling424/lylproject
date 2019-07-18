package com.lyl.httpapp

/**
 * User: lyl
 * Date: 2019-07-18 09:58
 */
interface IHttpRequest{
    fun setUrl(url:String)
    fun setData(data: ByteArray)
    fun setListener(callBackListener: CallBackListener)
    fun execute()
}