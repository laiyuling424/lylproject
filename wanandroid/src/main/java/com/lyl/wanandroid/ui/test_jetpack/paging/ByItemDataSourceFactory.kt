package com.lyl.wanandroid.ui.test_jetpack.paging

import androidx.paging.DataSource

/**
 * User: lyl
 * Date: 2019-06-13 11:37
 */

class ByItemDataSourceFactory : DataSource.Factory<Long, GithubAccount>() {
    override fun create(): DataSource<Long, GithubAccount> = ByItemDataSource()
}