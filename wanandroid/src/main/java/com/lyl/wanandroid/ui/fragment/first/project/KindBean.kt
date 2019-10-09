package com.lyl.wanandroid.ui.fragment.first.project

/**
 * Create By: lyl
 * Date: 2019-07-19 17:15
 */
/**
{
    "data": [
    {
        "children": [],
        "courseId": 13,
        "id": 294,
        "name": "完整项目",
        "order": 145000,
        "parentChapterId": 293,
        "userControlSetTop": false,
        "visible": 0
    }
    ],
    "errorCode": 0,
    "errorMsg": ""
}
 */
data class KindBean(
        var children:List<Any>?=null,
        var courseId:Int?=null,
        var id:Int?=null,
        var name:String?=null,
        var order:Int?=null,
        var parentChapterId:Int?=null,
        var userControlSetTop:Boolean?=null,
        var visible:Int?=null
)