package com.lyl.wanandroid.ui.fragment.wechatpublic

import androidx.paging.DataSource
import com.lyl.wanandroid.util.MyLog

/**
 * User: lyl
 * Date: 2019-07-08 18:46
 */

class WeChatContentItemDataSouceFactory(var id:Int) : DataSource.Factory<Int, WeChatContentBean>() {
    override fun create(): DataSource<Int, WeChatContentBean> {
        MyLog.Logd("ididididid")
        return WeChatContentItemDataSouce(id)
    }
}