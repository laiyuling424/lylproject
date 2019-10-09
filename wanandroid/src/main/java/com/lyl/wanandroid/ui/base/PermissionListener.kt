package com.lyl.wanandroid.ui.base

/**
 * Create By: lyl
 * Date: 2019-06-11 13:55
 */

interface PermissionListener {

    fun onGranted()

    fun onDenied(deniedPermissions: List<String>)

}