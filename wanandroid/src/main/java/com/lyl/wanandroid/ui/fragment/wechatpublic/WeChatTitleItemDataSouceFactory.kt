package com.lyl.wanandroid.ui.fragment.wechatpublic

import androidx.paging.DataSource


/**
 * Create By: lyl
 * Date: 2019-07-08 15:25
 */
class WeChatTitleItemDataSouceFactory : DataSource.Factory<Int, WeChatPublicListBean>() {
    override fun create(): DataSource<Int, WeChatPublicListBean> {
        return WeChatTitleItemDataSouce()
    }
}