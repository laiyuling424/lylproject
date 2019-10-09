package com.lyl.wanandroid.ui.fragment.first.tixi

import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

/**
 * Create By: lyl
 * Date: 2019-07-13 09:08
 */
class ViewModelNavigation : ViewModel() {
    var navigationList = LivePagedListBuilder(
            NavigationItemDataSouceFactory(),
            PagedList.Config.Builder()
                    .setPageSize(10)
                    .setEnablePlaceholders(false)
                    .build()
    ).build()
}