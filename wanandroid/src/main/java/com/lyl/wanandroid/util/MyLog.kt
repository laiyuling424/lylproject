package com.lyl.wanandroid.util

import android.util.Log
import com.lyl.wanandroid.config.Config


/**
 * User: lyl
 * Date: 2019-05-22 11:18
 */

object MyLog {
    var TAG = "lyll"

    //
    fun Logv(TAG: String, msg: Any?) {
        if (Config.outputLog && msg != null) {
            Log.v(TAG, msg.toString())
        }
    }

    //提示
    fun Logi(TAG: String, msg: Any?) {
        if (Config.outputLog && msg != null) {
            Log.i(TAG, msg.toString())
        }
    }

    //调试
    fun Logd(TAG: String, msg: Any?) {
        if (Config.outputLog && msg != null) {
            Log.d(TAG, msg.toString())
        }
    }

    //警告
    fun Logw(TAG: String, msg: Any?) {
        if (Config.outputLog && msg != null) {
            Log.w(TAG, msg.toString())
        }
    }

    //错误
    fun Loge(TAG: String, msg: Any?) {
        if (Config.outputLog && msg != null) {
            Log.e(TAG, msg.toString())
        }
    }
}