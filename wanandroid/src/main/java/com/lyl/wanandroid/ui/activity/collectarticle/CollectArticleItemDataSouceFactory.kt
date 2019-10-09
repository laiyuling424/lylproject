package com.lyl.wanandroid.ui.activity.collectarticle

import androidx.paging.DataSource
import com.lyl.wanandroid.ui.bean.CollectArticleBean


/**
 * Create By: lyl
 * Date: 2019-08-02 14:27
 */
class CollectArticleItemDataSouceFactory: DataSource.Factory<Int,CollectArticleBean>(){
    override fun create(): DataSource<Int, CollectArticleBean> {
        return CollectArticleItemDataSouce()
    }
}