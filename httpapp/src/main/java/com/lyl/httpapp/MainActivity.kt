package com.lyl.httpapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getdata()

    }

    private fun getdata() {
        NetUtil.sendJsonRequest("https://www.wanandroid.com/banner/json", null, WeChatPublicListBeanResponse::class.java,
                object : IJsonDataListener<WeChatPublicListBeanResponse> {
                    override fun onSuccess(t: WeChatPublicListBeanResponse?) {
                        Log.d("lyll", "response====>$t")
                    }
                    override fun onFailure() {

                    }
                })
    }
}
