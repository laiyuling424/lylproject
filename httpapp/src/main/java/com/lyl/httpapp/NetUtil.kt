package com.lyl.httpapp

/**
 * Create By: lyl
 * Date: 2019-07-18 11:20
 */
class NetUtil {

    companion object {
        public fun <T, M> sendJsonRequest(url: String, requestData: T, response: Class<M>, listener: IJsonDataListener<M>) {
            var httpRequest = JsonHttpRequest()
            var callBackListener = JsonCallbackListener(response, listener)
            var httpTask = HttpTask(url, requestData, httpRequest, callBackListener)
            ThreadPoolManager.getInstance().addTask(httpTask)
        }
    }
}