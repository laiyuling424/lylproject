package com.lyl.wanandroid.ui.activity.collectweb

import androidx.paging.DataSource
import com.lyl.wanandroid.ui.bean.CollectWebBean

/**
 * Create By: lyl
 * Date: 2019-08-02 15:48
 */
class CollectWebItemDataSouceFactory : DataSource.Factory<Int, CollectWebBean>() {
    override fun create(): DataSource<Int, CollectWebBean> {
        return CollectWebItemDataSouce()
    }
}