package com.lyl.wanandroid.ui.fragment.first.main

/**
 * Create By: lyl
 * Date: 2019-07-10 14:52
 */

/**
 *
 * {
"data": {
"curPage": 1,
"datas": [
{
"apkLink": "",
"author": " qing的世界",
"chapterId": 306,
"chapterName": "多线程与并发",
"collect": false,
"courseId": 13,
"desc": "",
"envelopePic": "",
"fresh": true,
"id": 8679,
"link": "https://juejin.im/post/5d1eb4acf265da1bb003de71",
"niceDate": "17小时前",
"origin": "",
"prefix": "",
"projectLink": "",
"publishTime": 1562600598000,
"superChapterId": 168,
"superChapterName": "基础知识",
"tags": [],
"title": "Android多线程技术选型最全指南(part 1 - 误区)",
"type": 0,
"userId": -1,
"visible": 1,
"zan": 0
}
],
"offset": 0,
"over": false,
"pageCount": 336,
"size": 20,
"total": 6705
},
"errorCode": 0,
"errorMsg": ""
}*
 */
data class MainArticleBodyBean(
    var curPage:Int?=null,
    var datas:List<MainArticleBean>?=null,
    var offset:Int?=null,
    var over:Boolean?=null,
    var pageCount:Int?=null,
    var size:Int?=null,
    var total:Int?=null
)

data class MainArticleBean(
        var apkLink:String?=null,
        var author:String?=null,
        var chapterId:Int?=null,
        var chapterName:String?=null,
        var collect:Boolean?=null,
        var courseId:Int?=null,
        var desc:String?=null,
        var envelopePic:String?=null,
        var fresh:Boolean?=null,
        var id:Int?=null,
        var link:String?=null,
        var niceDate:String?=null,
        var origin:String?=null,
        var prefix:String?=null,
        var projectLink:String?=null,
        var publishTime:Long?=null,
        var superChapterId:Int?=null,
        var superChapterName:String?=null,
        var tags:List<Any>?=null,
        var title:String?=null,
        var type:Int?=null,
        var userId:Int?=null,
        var visible:Int?=null,
        var zan:Int?=null
)

