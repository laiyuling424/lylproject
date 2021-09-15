package com.lyl.wanandroid.util

import android.util.Log
import com.lyl.wanandroid.config.Config


/**
 * Create By: lyl
 * Date: 2019-05-22 11:18
 */

object MyLog {
    var TAG = "lyll"

    fun Logv(msg: Any?) {
        Logv(TAG, msg)
    }

    fun Logv(TAG: String, msg: Any?) {
        if (Config.outputLog && msg != null) {
            Log.v(TAG, msg.toString())
        }
    }

    fun Logi(msg: Any?) {
        Logi(TAG, msg)
    }

    fun Logi(TAG: String, msg: Any?) {
        if (Config.outputLog && msg != null) {
            Log.i(TAG, msg.toString())
        }
    }

    //调试
    fun Logd(msg: Any?) {
        if (Config.outputLog && msg != null) {
            Log.d(TAG, msg.toString())
        }
    }

    fun Logw(msg: Any?) {
        Logw(TAG, msg)
    }

    fun Logw(TAG: String, msg: Any?) {
        if (Config.outputLog && msg != null) {
            Log.w(TAG, msg.toString())
        }
    }

    fun Loge(msg: Any?) {
        Loge(TAG, msg)
    }

    fun Loge(TAG: String, msg: Any?) {
        if (Config.outputLog && msg != null) {
            Log.e(TAG, msg.toString())
        }
    }
}