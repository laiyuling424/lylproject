package com.lyl.wanandroid.ui.fragment.first.tixi

import androidx.paging.DataSource

/**
 * Create By: lyl
 * Date: 2019-07-13 09:07
 */
class TixiItemDataSouceFactory() : DataSource.Factory<Int, TixiBean>() {
    override fun create(): DataSource<Int, TixiBean> {
        return TixiItemDataSouce()
    }
}