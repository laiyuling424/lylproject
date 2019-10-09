package com.lyl.wanandroid.ui.fragment.first.project

/**
 * Create By: lyl
 * Date: 2019-07-19 17:56
 */
/**
{
    "data": {
        "curPage": 1,
        "datas": [
        {
            "apkLink": "",
            "author": "TzuChiangLi",
            "chapterId": 294,
            "chapterName": "完整项目",
            "collect": false,
            "courseId": 13,
            "desc": "当时毕业公司安排我学习Android的开发以快速开发项目，所以在公司一直MVC的模式开发，在看了现在的主流及趋势后，发现MVP是进步路上的必修课，所以就参考了很多大神的项目学习MVP的写法和思路。\r\n",
            "envelopePic": "https://wanandroid.com/blogimgs/bf9ed860-3ab1-4bea-9c9a-6de3c75e861b.png",
            "fresh": false,
            "id": 8658,
            "link": "http://www.wanandroid.com/blog/show/2617",
            "niceDate": "2019-07-01",
            "origin": "",
            "prefix": "",
            "projectLink": "https://github.com/TzuChiangLi/WanAndroid",
            "publishTime": 1561983121000,
            "superChapterId": 294,
            "superChapterName": "开源项目主Tab",
            "tags": [
            {
                "name": "项目",
                "url": "/project/list/1?cid=294"
            }
            ],
            "title": "WanAndroid 个人第一个练手项目分享",
            "type": 0,
            "userId": -1,
            "visible": 0,
            "zan": 0
        }
        ],
        "offset": 0,
        "over": false,
        "pageCount": 10,
        "size": 15,
        "total": 147
    },
    "errorCode": 0,
    "errorMsg": ""
}
 */

data class KindContentListBean(
        var curPage:Int?=null,
        var datas:List<KindContentBean>?=null,
        var offset:Int?=null,
        var over:Boolean?=null,
        var pageCount:Int?=null,
        var size:Int?=null,
        var total:Int?=null
)

data class KindContentBean(
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
        var superChapterId:String?=null,
        var superChapterName:String?=null,
        var tags:List<KindContentTagsBean>?=null,
        var title:String?=null,
        var type:Int?=null,
        var userId:Int?=null,
        var visible:Int?=null,
        var zan:Int?=null
)

data class KindContentTagsBean(
        var name:String?=null,
        var url:String?=null
)