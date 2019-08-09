package com.lyl.wanandroid.ui.bean

/**
 * User: lyl
 * Date: 2019-08-02 11:33
 */
/**
{
    "data": [
    {
        "desc": "",
        "icon": "",
        "id": 1852,
        "link": "www.baidu.com",
        "name": "baidu",
        "order": 0,
        "userId": 24177,
        "visible": 1
    }
    ],
    "errorCode": 0,
    "errorMsg": ""
}
        */

data class CollectWebBean(
        var desc:String?=null,
        var icon:String?=null,
        var id:Int?=null,
        var link:String?=null,
        var name:String?=null,
        var order:Int?=null,
        var userId:Int?=null,
        var visible:Int?=null
)