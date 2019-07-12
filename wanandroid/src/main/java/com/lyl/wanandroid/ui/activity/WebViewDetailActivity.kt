package com.lyl.wanandroid.ui.activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import com.lyl.wanandroid.Constants
import com.lyl.wanandroid.R
import com.lyl.wanandroid.ui.base.BaseActivity
import com.lyl.wanandroid.util.MyLog
import kotlinx.android.synthetic.main.activity_we_chat_detail.*

class WebViewDetailActivity : BaseActivity() {
    override val layoutId: Int
        get() =R.layout.activity_we_chat_detail

    override fun loadData() {

    }

    override fun initView() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_we_chat_detail)

        title_name.text = intent.getStringExtra(Constants.CONTENT_TITLE)
        var url: String = intent.getStringExtra(Constants.CONTENT_URL)
        init(url)
    }

    fun img_back(view: View) {
        finish()
    }

    fun init(url: String) {
        webview.loadUrl(url)
        val webSettings = webview.settings
        //打开js支持
        webSettings.javaScriptEnabled = true
        webview.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                // TODO 自动生成的方法存根
                if (newProgress == 100) {
                    //加载完网页进度条消失
                    progress.visibility = View.GONE

                } else {
                    //开始加载网页时显示进度条
                    progress.visibility = View.VISIBLE
                    //设置进度值
                    progress.progress = newProgress
                }
            }
        }
        webview.setOnKeyListener(View.OnKeyListener { view, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN) {
                //按返回键操作并且能回退网页
                if (keyCode == KeyEvent.KEYCODE_BACK && webview.canGoBack()) {
                    //后退
                    webview.goBack()
                    return@OnKeyListener true
                }
            }
            false
        })

        webview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                MyLog.Logd("onSuccess: url$url")
                return super.shouldOverrideUrlLoading(view, url)

            }

            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                // Android版本变量
                val version = Build.VERSION.SDK_INT
                // 因为该方法在 Android 4.4 版本才可使用，所以使用时需进行版本判断
                if (version < 18) {
                    view.loadUrl(url)
                } else {
                    view.evaluateJavascript(url) { value ->
                        //此处为 js 返回的结果
                        MyLog.Logd("onSuccess: value$value")
                    }
                }
            }
        }
    }
}
