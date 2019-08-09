package com.lyl.wanandroid.ui.activity.search

import androidx.paging.DataSource
import com.lyl.wanandroid.ui.fragment.first.usefulweb.UsefulWebBean


/**
 * User: lyl
 * Date: 2019-07-11 15:42
 */
class SearchDataSouceFactory: DataSource.Factory<Int, SearchResponseBean>(){
    override fun create(): DataSource<Int, SearchResponseBean> {
        return SearchDataSouce()
    }
}