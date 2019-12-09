package com.lyl.wanandroid.ui.activity.search

import androidx.paging.DataSource


/**
 * Create By: lyl
 * Date: 2019-07-11 15:42
 */
class SearchDataSouceFactory : DataSource.Factory<Int, SearchResponseBean>() {
    override fun create(): DataSource<Int, SearchResponseBean> {
        return SearchDataSouce()
    }
}