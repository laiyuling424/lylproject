package com.lyl.wanandroid.ui.fragment.first.usefulweb

import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

/**
 * User: lyl
 * Date: 2019-07-11 15:41
 */
class ViewModelUsefulWeb:ViewModel(){

    var usefulWebList=LivePagedListBuilder(
            UsefulWebDataSouceFactory(),
            PagedList.Config.Builder()
                    .setPageSize(10)
                    .setEnablePlaceholders(false).build()
    ).build()
}