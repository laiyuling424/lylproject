package com.lyl.wanandroid.ui.activity.search

import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

/**
 * Create By: lyl
 * Date: 2019-07-11 15:41
 */
class ViewModelSearch:ViewModel(){

    var searchList=LivePagedListBuilder(
            SearchDataSouceFactory(),
            PagedList.Config.Builder()
                    .setPageSize(10)
                    .setEnablePlaceholders(false).build()
    ).build()
}