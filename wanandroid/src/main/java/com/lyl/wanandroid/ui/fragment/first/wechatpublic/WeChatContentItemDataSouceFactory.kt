package com.lyl.wanandroid.ui.fragment.first.wechatpublic

import androidx.paging.DataSource

/**
 * User: lyl
 * Date: 2019-07-08 18:46
 */

class WeChatContentItemDataSouceFactory(id:Int) : DataSource.Factory<Int, WeChatContentBean>() {
    override fun create(): DataSource<Int, WeChatContentBean> {
        return WeChatContentItemDataSouce(408)
    }
}