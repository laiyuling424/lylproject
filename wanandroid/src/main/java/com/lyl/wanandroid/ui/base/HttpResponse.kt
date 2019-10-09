package com.lyl.wanandroid.ui.base


/**
 * Create By: lyl
 * Date: 2019-07-08 15:55
 */
data class HttpResponse<T>(
        var data: T? = null,
        var errorCode: Int = 0,
        var errorMsg: String? = null
)