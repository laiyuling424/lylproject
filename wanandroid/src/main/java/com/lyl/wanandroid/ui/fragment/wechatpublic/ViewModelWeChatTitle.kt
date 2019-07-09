package com.lyl.wanandroid.ui.fragment.wechatpublic

import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

/**
 * User: lyl
 * Date: 2019-07-08 15:00
 */
class ViewModelWeChatTitle:ViewModel(){

    val titleLists = LivePagedListBuilder(WeChatTitleItemDataSouceFactory(),
            PagedList.Config.Builder()
                    .setPageSize(10)
                    .setEnablePlaceholders(false).build()).build()
}