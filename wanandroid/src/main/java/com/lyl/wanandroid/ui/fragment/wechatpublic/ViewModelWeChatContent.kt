package com.lyl.wanandroid.ui.fragment.wechatpublic

import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

/**
 * Create By: lyl
 * Date: 2019-07-08 17:42
 */
class ViewModelWeChatContent(id: Int) : ViewModel() {

    var contentLists = LivePagedListBuilder(WeChatContentItemDataSouceFactory(id),
            PagedList.Config.Builder()
                    .setPageSize(10)
                    .setEnablePlaceholders(false).build()).build()


}