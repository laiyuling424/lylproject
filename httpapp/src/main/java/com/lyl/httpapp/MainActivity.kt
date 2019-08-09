package com.lyl.httpapp

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.lyll.httpapp.R


class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getdata()
        geterrordata()
    }

    private fun getdata() {
        NetUtil.sendJsonRequest("https://www.wanandroid.com/wxarticle/chapters/json", null, WeChatPublicListBeanResponse::class.java,
                object : IJsonDataListener<WeChatPublicListBeanResponse> {
                    override fun onSuccess(t: WeChatPublicListBeanResponse?) {
                        Log.d("lyll", "response====>$t")
                    }
                    override fun onFailure() {

                    }
                })
    }

    private fun geterrordata() {
        NetUtil.sendJsonRequest("https://xxxxxxxxx", null, WeChatPublicListBeanResponse::class.java,
                object : IJsonDataListener<WeChatPublicListBeanResponse> {
                    override fun onSuccess(t: WeChatPublicListBeanResponse?) {
                        Log.d("lyll", "response====>$t")
                    }
                    override fun onFailure() {

                    }
                })
    }
}
