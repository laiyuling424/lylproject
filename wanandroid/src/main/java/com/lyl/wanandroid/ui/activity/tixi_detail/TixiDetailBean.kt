package com.lyl.wanandroid.ui.activity.tixi_detail

/**
 * Create By: lyl
 * Date: 2019-07-13 15:10
 */
/**
{
"data": {
"curPage": 1,
"datas": [
{
"apkLink": "",
"author": "南尘",
"chapterId": 60,
"chapterName": "Android Studio相关",
"collect": false,
"courseId": 13,
"desc": "",
"envelopePic": "",
"fresh": false,
"id": 8686,
"link": "https://juejin.im/post/5d26e5206fb9a07ec63b3f02",
"niceDate": "1天前",
"origin": "",
"prefix": "",
"projectLink": "",
"publishTime": 1562860146000,
"superChapterId": 60,
"superChapterName": "开发环境",
"tags": [],
"title": "南尘亲人重病，需要帮助，有能力可以伸出援助之手",
"type": 1,
"userId": -1,
"visible": 1,
"zan": 0
}
],
"offset": 0,
"over": false,
"pageCount": 3,
"size": 20,
"total": 42
},
"errorCode": 0,
"errorMsg": ""
}
 */

data class TixiDetailListBean(
        var curPage: Int? = null,
        var datas: List<TixiDetailBean>? = null,
        var offset: Int? = null,
        var over: Boolean? = null,
        var pageCount: Int? = null,
        var size: Int? = null,
        var total: Int? = null
)

data class TixiDetailBean(
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