package com.lyl.wanandroid.ui.fragment.first.usefulweb

import androidx.paging.DataSource


/**
 * User: lyl
 * Date: 2019-07-11 15:42
 */
class UsefulWebDataSouceFactory: DataSource.Factory<Int,UsefulWebBean>(){
    override fun create(): DataSource<Int, UsefulWebBean> {
        return UsefulWebDataSouce()
    }
}