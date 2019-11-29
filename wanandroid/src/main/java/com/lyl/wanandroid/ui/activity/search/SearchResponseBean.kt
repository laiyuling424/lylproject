package com.lyl.wanandroid.ui.activity.search

/**
 * Create By: lyl
 * Date: 2019-07-12 15:06
 */
/**
{
"data": {
"curPage": 1,
"datas": [
{
"apkLink": "",
"author": "code小生",
"chapterId": 414,
"chapterName": "code小生",
"collect": false,
"courseId": 13,
"desc": "",
"envelopePic": "",
"fresh": false,
"id": 8585,
"link": "https://mp.weixin.qq.com/s/hZzk2fy0m4edexXiFt3FIA",
"niceDate": "2019-06-06",
"origin": "",
"prefix": "",
"projectLink": "",
"publishTime": 1559750400000,
"superChapterId": 408,
"superChapterName": "公众号",
"tags": [
{
"name": "公众号",
"url": "/wxarticle/list/414/1"
}
],
"title": "Android-打包<em class='highlight'>AA</em>R步骤以及注意事项",
"type": 0,
"userId": -1,
"visible": 1,
"zan": 0
}
],
"offset": 0,
"over": true,
"pageCount": 1,
"size": 20,
"total": 12
},
"errorCode": 0,
"errorMsg": ""
}
 */
data class SearchResponseListBean(
        var curPage: Int? = null,
        var datas: List<SearchResponseBean>? = null,
        var offset: Int? = null,
        var over: Boolean? = null,
        var pageCount: Int? = null,
        var size: Int? = null,
        var total: Int? = null
)

/**
{
"apkLink": "",
"author": "code小生",
"chapterId": 414,
"chapterName": "code小生",
"collect": false,
"courseId": 13,
"desc": "",
"envelopePic": "",
"fresh": false,
"id": 8585,
"link": "https://mp.weixin.qq.com/s/hZzk2fy0m4edexXiFt3FIA",
"niceDate": "2019-06-06",
"origin": "",
"prefix": "",
"projectLink": "",
"publishTime": 1559750400000,
"superChapterId": 408,
"superChapterName": "公众号",
"tags": [
{
"name": "公众号",
"url": "/wxarticle/list/414/1"
}
],
"title": "Android-打包<em class='highlight'>AA</em>R步骤以及注意事项",
"type": 0,
"userId": -1,
"visible": 1,
"zan": 0
}
 */
data class SearchResponseBean(
        var apklink: String? = null,
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
        var publishTime: String? = null,
        var superChapterId: Int? = null,
        var superChapterName: String? = null,
        var tags: List<SearchResponseWcChatBean>? = null,
        var title: String? = null,
        var type: Int? = null,
        var userId: Int? = null,
        var visible: Int? = null,
        var zan: Int? = null
)

/**
{
"name": "公众号",
"url": "/wxarticle/list/414/1"
}
 */
data class SearchResponseWcChatBean(
        var name: String? = null,
        var url: String? = null
)


