package com.lyl.wanandroid.ui.fragment.wechatpublic

/**
 * User: lyl
 * Date: 2019-07-08 17:44
 */
data class WeChatContentListBean(
        var curPage: Int = 0,
        var offset: Int = 0,
        var isOver: Boolean = false,
        var pageCount: Int = 0,
        var size: Int = 0,
        var total: Int = 0,
        var datas: List<WeChatContentBean>? = null
)

data class WeChatContentBean(
        var apkLink: String? = null,
        var author: String? = null,
        var chapterId: Int = 0,
        var chapterName: String? = null,
        var isCollect: Boolean = false,
        var courseId: Int = 0,
        var desc: String? = null,
        var envelopePic: String? = null,
        var isFresh: Boolean = false,
        var id: Int = 0,
        var link: String? = null,
        var niceDate: String? = null,
        var origin: String? = null,
        var projectLink: String? = null,
        var publishTime: Long = 0,
        var superChapterId: Int = 0,
        var superChapterName: String? = null,
        var title: String? = null,
        var type: Int = 0,
        var userId: Int = 0,
        var visible: Int = 0,
        var zan: Int = 0,
        var tags: List<*>? = null
)