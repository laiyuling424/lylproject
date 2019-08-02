package com.lyl.wanandroid.ui.activity.collectweb

import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

/**
 * User: lyl
 * Date: 2019-08-02 15:48
 */
class ViewModelCollectWeb:ViewModel(){
    var collectWebList=LivePagedListBuilder(CollectWebItemDataSouceFactory(),
            PagedList.Config.Builder()
                    .setPageSize(10)
                    .setEnablePlaceholders(false)
                    .build()
    ).build()
}
