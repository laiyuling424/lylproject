package com.lyl.httpapp

import android.os.Looper
import android.util.Log
import com.alibaba.fastjson.JSON
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.logging.Handler

/**
 * User: lyl
 * Date: 2019-07-18 11:03
 */
class JsonCallbackListener<T> : CallBackListener {

    private var responseClass: Class<T>? = null

    private var mHandler = android.os.Handler(Looper.getMainLooper())

    private var jsonDataListener: IJsonDataListener<T>? = null

    constructor(responseClass: Class<T>, listener: IJsonDataListener<T>) {
        this.responseClass = responseClass
        this.jsonDataListener = listener
    }

    override fun success(inputStream: InputStream) {
        var response: String = getContent(inputStream)

        var clazz: T = JSON.parseObject(response, responseClass)

        mHandler.post(object : Runnable {
            override fun run() {
                jsonDataListener!!.onSuccess(clazz)
            }
        })
    }

    override fun failure(e: String) {

    }

    private fun getContent(inputStream: InputStream): String {

//        var reader: BufferedReader = BufferedReader(InputStreamReader(inputStream))
//        var line: String? = null
//        var sb = StringBuffer()
//        while ((line = reader.readLine()) != null) {
//            sb.append(reader.readLine() + "\n")
//        }
//        inputStream.close()

        var line: String? = null
        var reader: BufferedReader = BufferedReader(InputStreamReader(inputStream))
        var sb = StringBuffer()
        do {
            line = reader.readLine()
            if (line == null) break
            sb.append(line + "\n")
        } while (true)
        return sb.toString()
    }
}