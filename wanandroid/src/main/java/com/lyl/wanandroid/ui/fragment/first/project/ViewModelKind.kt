package com.lyl.wanandroid.ui.fragment.first.project

import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

/**
 * User: lyl
 * Date: 2019-07-19 17:13
 */
class ViewModelKind:ViewModel(){
    var kindList=LivePagedListBuilder(KindItemDataSourceFactory(),
            PagedList.Config
                    .Builder()
                    .setPageSize(10)
                    .setEnablePlaceholders(false)
                    .build())
            .build()
}