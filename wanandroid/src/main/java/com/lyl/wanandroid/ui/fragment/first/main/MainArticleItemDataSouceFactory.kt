package com.lyl.wanandroid.ui.fragment.first.main

import androidx.paging.DataSource

/**
 * Create By: lyl
 * Date: 2019-07-10 15:07
 */
class MainArticleItemDataSouceFactory(var page:Int):DataSource.Factory<Int,MainArticleBean>(){
    override fun create(): DataSource<Int, MainArticleBean> {
        return MainArticleItemDataSouce(page)
    }
}