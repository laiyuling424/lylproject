package com.lyl.httpapp

import android.util.Log
import java.io.BufferedOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.lang.RuntimeException
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLConnection

/**
 * User: lyl
 * Date: 2019-07-18 10:18
 */
class JsonHttpRequest : IHttpRequest {

    private var url: String? = null
    private var data: ByteArray? = null
    private var listener: CallBackListener? = null
    private var urlConnection: URLConnection? = null

    override fun setUrl(url: String) {
        this.url = url
    }

    override fun setData(data: ByteArray) {
        this.data = data
    }

    override fun setListener(callBackListener: CallBackListener) {
        this.listener = callBackListener
    }

    override fun execute() {
        var url: URL = URL(this.url)
        urlConnection = url.openConnection() as HttpURLConnection
        (urlConnection as HttpURLConnection).connectTimeout = 6000
        (urlConnection as HttpURLConnection).useCaches = false
        (urlConnection as HttpURLConnection).instanceFollowRedirects = true
        (urlConnection as HttpURLConnection).readTimeout = 3000
        (urlConnection as HttpURLConnection).doInput = true
        (urlConnection as HttpURLConnection).doOutput = true
        (urlConnection as HttpURLConnection).requestMethod = "GET"
        (urlConnection as HttpURLConnection).setRequestProperty("Content-Type", "application/json;charset=UTF-8")
        (urlConnection as HttpURLConnection).connect()

        var out: OutputStream = (urlConnection as HttpURLConnection).outputStream
        var bos = BufferedOutputStream(out)
        bos.write(data)
        bos.flush()//刷新缓冲区，发送数据
        out.close()
        bos.close()

        if ((urlConnection as HttpURLConnection).responseCode == HttpURLConnection.HTTP_OK) {
            var inn: InputStream = (urlConnection as HttpURLConnection).inputStream
//                Log.d("lyll", "inn ======>"+inn.available())


//            var bytes = ByteArray(1024)
//            bytes = ByteArray(1024)
//            inn.read(bytes)
//            val str = String(bytes)
//            Log.d("lyll", "inn ======>$str")

            listener!!.success(inn)
        } else {
            throw RuntimeException("请求失败")
        }

        (urlConnection as HttpURLConnection).disconnect()
    }
}

