package com.lyl.wanandroid.ui.fragment.wechatpublic

import androidx.paging.DataSource

/**
 * Create By: lyl
 * Date: 2019-07-08 18:46
 */

class WeChatContentItemDataSouceFactory(var id: Int) : DataSource.Factory<Int, WeChatContentBean>() {
    override fun create(): DataSource<Int, WeChatContentBean> {
        return WeChatContentItemDataSouce(id)
    }
}