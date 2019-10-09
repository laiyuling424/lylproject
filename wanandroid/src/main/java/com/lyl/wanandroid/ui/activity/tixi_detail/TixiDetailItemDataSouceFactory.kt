package com.lyl.wanandroid.ui.activity.tixi_detail

import androidx.paging.DataSource
import com.lyl.wanandroid.ui.fragment.first.main.MainArticleBean

/**
 * Create By: lyl
 * Date: 2019-07-13 15:08
 */
class TixiDetailItemDataSouceFactory(var cid:Int):DataSource.Factory<Int,MainArticleBean>(){
    override fun create(): DataSource<Int, MainArticleBean> {
        return TixiDetailItemDataSouce(cid)
    }
}