package com.lyl.mvptest.mvp.secondfragment.jetpack_0701_test

import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

/**
 * Create By: lyl
 * Date: 2019-07-01 15:50
 */
class ProvinceViewModel: ViewModel(){
    val accounts = LivePagedListBuilder(ProvinceItemDataSouceFactory(),
            PagedList.Config.Builder()
                    .setPageSize(10)
                    .setEnablePlaceholders(false).build()).build()
}