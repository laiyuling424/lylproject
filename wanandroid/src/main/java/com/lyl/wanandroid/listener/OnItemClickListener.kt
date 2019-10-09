package com.lyl.wanandroid.listener

/**
 * Create By: lyl
 * Date: 2019-07-08 17:08
 */
interface OnItemClickListener<T> {
    /**
     *单击事件
     */
    fun itemClick(t: T, position: Int)
}

interface OnItemClickListenerTwo<T> {
    /**
     *单击事件
     */
    fun itemClickTwo(t: T, position: Int)
}