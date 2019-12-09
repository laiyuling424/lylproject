package com.lyl.wanandroid.ui.bean

/**
 * Create By: lyl
 * Date: 2019-07-31 17:55
 */
/**
{
"data": {
"admin": false,
"chapterTops": [],
"collectIds": [
8573,
8096,
8025
],
"email": "",
"icon": "",
"id": 24177,
"nickname": "lyl大白菜",
"password": "",
"token": "",
"type": 0,
"username": "lyl大白菜"
},
"errorCode": 0,
"errorMsg": ""
}
 */
data class LoginBean(
        var admin: Boolean? = null,
        var chapterTops: ArrayList<Any>? = null,
        var collectIds: ArrayList<Int>? = null,
        var email: String? = null,
        var icon: String? = null,
        var id: Int? = null,
        var nickname: String? = null,
        var password: String? = null,
        var token: String? = null,
        var type: Int? = null,
        var username: String? = null
)