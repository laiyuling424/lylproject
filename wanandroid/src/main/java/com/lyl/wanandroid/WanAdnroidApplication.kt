package com.lyl.wanandroid

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.os.Debug
import android.os.Handler
import com.lyl.appmonitor.window.MonitorWindowService
import com.lyl.wanandroid.util.MyLog
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Create By: lyl
 * Date: 2019-06-11 14:54
 */

open class WanAdnroidApplication : Application(), Application.ActivityLifecycleCallbacks {

    companion object {
        private var handler: Handler? = null
        private var context: Application? = null
        fun getContext(): Context {
            return context!!
        }

        fun getHandler(): Handler {
            return handler!!
        }
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        registerActivityLifecycleCallbacks(this)
//        startTraceMinitor()


    }

    private fun startTraceMinitor() {
        val dateFormat: DateFormat = SimpleDateFormat("dd_MM_yyyy_hh_mm_ss", Locale.getDefault())
        val logDate: String = dateFormat.format(Date())
        Debug.startMethodTracing("aa.trace")
        MyLog.Loge("startTraceMinitor")
    }

    private fun endTraceMinitor() {
        Debug.stopMethodTracing()
        MyLog.Loge("endTraceMinitor")
    }

    var index = 0
    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        index++
        MyLog.Loge("index is $index")
//        if (index == 5) {
//            endTraceMinitor()
//        }

        if (index == 1) {
            MonitorWindowService.startService(activity)
        }

//        if (index == 2){
//            val intent = Intent("UP_DATA")
//            intent.putExtra("code",1)
//            LocalBroadcastManager.getInstance(this)
//                    .sendBroadcast(intent)
//        }
    }

    override fun onActivityStarted(activity: Activity?) {

    }

    override fun onActivityResumed(activity: Activity?) {
        if (index == 2) {
            fork()
        }
    }

    private fun fork() {
        try {
            val fork = Runtime.getRuntime().exec("fork")
            val inputStream = fork.inputStream
            val errorInputStream = fork.errorStream
            var len = 0
            val bs = ByteArray(256)
            while (-1 != (inputStream.read(bs).also { len = it })) {
                MyLog.Loge("${String(bs, 0, len)}")
            }
            inputStream.close()
            errorInputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onActivityPaused(activity: Activity?) {

    }

    override fun onActivityStopped(activity: Activity?) {

    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {

    }

    override fun onActivityDestroyed(activity: Activity?) {

    }
}