package com.lyl.wanandroid.ui.activity.collectarticle

import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

/**
 * User: lyl
 * Date: 2019-08-02 14:26
 */
class ViewModelCollectArticle:ViewModel(){
    var collectArticleList=LivePagedListBuilder(CollectArticleItemDataSouceFactory(),
            PagedList.Config.Builder()
                    .setPageSize(10)
                    .setEnablePlaceholders(false)
                    .build()
    ).build()
}