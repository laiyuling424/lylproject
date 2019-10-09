package com.lyl.wanandroid.ui.fragment.first.main

import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.lyl.wanandroid.ui.fragment.wechatpublic.WeChatContentItemDataSouceFactory

/**
 * Create By: lyl
 * Date: 2019-07-10 15:04
 */
class ViewModelMainArticle(page:Int):ViewModel(){
    var articleLists = LivePagedListBuilder(MainArticleItemDataSouceFactory(page),
            PagedList.Config.Builder()
                    .setPageSize(10)
                    .setEnablePlaceholders(false).build()).build()
}