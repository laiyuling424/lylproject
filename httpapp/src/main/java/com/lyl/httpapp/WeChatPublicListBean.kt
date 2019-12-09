package com.lyl.httpapp

/**
 * Create By: lyl
 * Date: 2019-07-18 14:34
 */
data class WeChatPublicListBean(
//        var children:Array<Any>?=null,
        var courseId: Int? = null,
        var id: Int? = null,
        var name: String? = null,
        var parentChapterId: Int? = null,
        var userControlSetTop: Boolean? = null,
        var visible: Int? = null
)

//data class WeChatPublicListBeanList(
//        var datas: WeChatPublicListBean? = null
//)

data class WeChatPublicListBeanResponse(
        var data: List<WeChatPublicListBean>? = null,
        var errorCode: Int = 0,
        var errorMsg: String? = null
)