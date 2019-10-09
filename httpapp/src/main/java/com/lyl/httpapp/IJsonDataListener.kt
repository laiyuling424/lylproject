package com.lyl.httpapp

/**
 * Create By: lyl
 * Date: 2019-07-18 11:15
 */
interface IJsonDataListener<T>{
    fun onSuccess(t:T?)
    fun onFailure()
}