package com.lyl.wanandroid.ui.test_jetpack.paging

import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

/**
 * User: lyl
 * Date: 2019-06-13 11:48
 */

class ByItemViewModel : ViewModel() {
    val accounts = LivePagedListBuilder(ByItemDataSourceFactory(),
            PagedList.Config.Builder()
                    .setPageSize(30)
                    .setEnablePlaceholders(false).build()).build()
}