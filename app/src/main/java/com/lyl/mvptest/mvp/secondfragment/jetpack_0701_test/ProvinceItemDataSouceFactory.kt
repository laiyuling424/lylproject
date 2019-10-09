package com.lyl.mvptest.mvp.secondfragment.jetpack_0701_test

import androidx.paging.DataSource

/**
 * Create By: lyl
 * Date: 2019-07-01 16:05
 */
class ProvinceItemDataSouceFactory:DataSource.Factory<Long,ProvinceBean>(){
    override fun create(): DataSource<Long, ProvinceBean> {
        return ProvinceItemDataSouce()
    }
}