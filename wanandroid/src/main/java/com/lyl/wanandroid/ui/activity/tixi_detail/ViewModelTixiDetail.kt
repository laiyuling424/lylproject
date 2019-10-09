package com.lyl.wanandroid.ui.activity.tixi_detail

import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

/**
 * Create By: lyl
 * Date: 2019-07-13 15:08
 */
class ViewModelTixiDetail(var cid:Int):ViewModel(){
    var tixiDetailList=LivePagedListBuilder(
            TixiDetailItemDataSouceFactory(cid),
            PagedList.Config.Builder()
                    .setPageSize(10)
                    .setEnablePlaceholders(false)
                    .build()
    ).build()
}