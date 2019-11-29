package com.lyl.wanandroid.ui.fragment.first.tixi

/**
 * Create By: lyl
 * Date: 2019-07-13 09:07
 */
/**
{
"data": [
{
"children": [
{
"children": [],
"courseId": 13,
"id": 60,
"name": "Android Studio相关",
"order": 1000,
"parentChapterId": 150,
"userControlSetTop": false,
"visible": 1
}
],
"courseId": 13,
"id": 150,
"name": "开发环境",
"order": 1,
"parentChapterId": 0,
"userControlSetTop": false,
"visible": 1
}
],
"errorCode": 0,
"errorMsg": ""
}
 */

data class TixiBean(
        var children: List<TixiChildBean>? = null,
        var courseId: Int? = null,
        var id: Int? = null,
        var name: String? = null,
        var order: Int? = null,
        var parentChapterId: Int? = null,
        var userControlSetTop: Boolean? = null,
        var visible: Int? = null
)

data class TixiChildBean(
        var children: List<Any>? = null,
        var courseId: Int? = null,
        var id: Int? = null,
        var name: String? = null,
        var order: Int? = null,
        var parentChapterId: Int? = null,
        var userControlSetTop: Boolean? = null,
        var visible: Int? = null
)