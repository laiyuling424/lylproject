package com.lyl.httpapp

import java.io.InputStream

/**
 * User: lyl
 * Date: 2019-07-18 10:05
 */
interface CallBackListener{
    fun success(inputStream: InputStream)
    fun failure(e:String)
}