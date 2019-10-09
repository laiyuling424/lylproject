package com.lyl.wanandroid.ui.bean

/**
 * Create By: lyl
 * Date: 2019-08-02 11:06
 */
/**
{
    "data": {
    "curPage": 1,
    "datas": [
    {
        "author": "xiaoyang",
        "chapterId": 0,
        "chapterName": "",
        "courseId": 13,
        "desc": "",
        "envelopePic": "",
        "id": 64522,
        "link": "https://www.wanandroid.com/blog/show/2604",
        "niceDate": "2019-06-03",
        "origin": "",
        "originId": 8573,
        "publishTime": 1559524110000,
        "title": "lycknight的知识体系分享",
        "userId": 24177,
        "visible": 0,
        "zan": 0
    }
    ],
    "offset": 0,
    "over": true,
    "pageCount": 1,
    "size": 20,
    "total": 3
},
    "errorCode": 0,
    "errorMsg": ""
}
        */

data class CollectArticleBean(
        var author:String?=null,
        var chapterId:Int?=null,
        var chapterName:String?=null,
        var courseId:Int?=null,
        var desc:String?=null,
        var envelopePic:String?=null,
        var id:Int?=null,
        var link:String?=null,
        var niceDate:String?=null,
        var origin:String?=null,
        var originId:Int?=null,
        var publishTime:Long?=null,
        var title:String?=null,
        var userId:Int?=null,
        var visible:Int?=null,
        var zan:Int?=null
)

data class CollectArticleListBean(
        var curPage:Int?=null,
        var datas:List<CollectArticleBean>?=null,
        var offset:Int?=null,
        var over:Boolean?=null,
        var pageCount:Int?=null,
        var size:Int?=null,
        var total:Int?=null
)