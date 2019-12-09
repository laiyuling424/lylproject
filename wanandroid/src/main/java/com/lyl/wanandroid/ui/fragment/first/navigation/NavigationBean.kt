package com.lyl.wanandroid.ui.fragment.first.tixi

/**
 * Create By: lyl
 * Date: 2019-07-13 09:07
 */
/**
{
"data": [
{
"articles": [
{
"apkLink": "",
"author": "小编",
"chapterId": 272,
"chapterName": "常用网站",
"collect": false,
"courseId": 13,
"desc": "",
"envelopePic": "",
"fresh": false,
"id": 1848,
"link": "https://developers.google.cn/",
"niceDate": "2018-01-07",
"origin": "",
"prefix": "",
"projectLink": "",
"publishTime": 1515322795000,
"superChapterId": 0,
"superChapterName": "",
"tags": [],
"title": "Google开发者",
"type": 0,
"userId": -1,
"visible": 0,
"zan": 0
}
],
"cid": 272,
"name": "常用网站"
}
],
"errorCode": 0,
"errorMsg": ""
}
 */

data class NavigationBean(
        var apkLink: String? = null,
        var author: String? = null,
        var chapterId: Int? = null,
        var chapterName: String? = null,
        var collect: Boolean? = null,
        var courseId: Int? = null,
        var desc: String? = null,
        var envelopePic: String? = null,
        var fresh: Boolean? = null,
        var id: Int? = null,
        var link: String? = null,
        var niceDate: String? = null,
        var origin: String? = null,
        var prefix: String? = null,
        var projectLink: String? = null,
        var publishTime: Long? = null,
        var superChapterId: Int? = null,
        var superChapterName: String? = null,
        var tags: List<Any>? = null,
        var title: String? = null,
        var type: Int? = null,
        var userId: Int? = null,
        var visible: Int? = null,
        var zan: Int? = null

)

data class NavigationListBean(
        var articles: List<NavigationBean>? = null,
        var cid: Int? = null,
        var name: String? = null
)
