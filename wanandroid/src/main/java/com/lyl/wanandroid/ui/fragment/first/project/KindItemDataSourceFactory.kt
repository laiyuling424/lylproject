package com.lyl.wanandroid.ui.fragment.first.project

import androidx.paging.DataSource

/**
 * Create By: lyl
 * Date: 2019-07-19 17:13
 */
class KindItemDataSourceFactory : DataSource.Factory<Int, KindBean>() {
    override fun create(): DataSource<Int, KindBean> {
        return KindItemDataSource()
    }
}