package com.lyl.wanandroid.util

import android.content.Context
import android.content.SharedPreferences

/**
 * User: lyl
 * Date: 2019-07-30 11:29
 */
object SharedPreferencesUtil {
    private var sharedPreferences: SharedPreferences? = null


    fun putBoolean(context: Context, key: String, value: Boolean?) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences("config", Context.MODE_PRIVATE)
        }
        sharedPreferences!!.edit().putBoolean(key, value!!).commit()
    }

    fun getBoolean(context: Context, key: String, value: Boolean?): Boolean {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences("config", Context.MODE_PRIVATE)
        }
        return sharedPreferences!!.getBoolean(key, value!!)
    }


    fun putString(context: Context, key: String, value: String) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences("config", Context.MODE_PRIVATE)
        }
        sharedPreferences!!.edit().putString(key, value).commit()
    }


    fun getString(context: Context, key: String, defValue: String): String? {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences("config", Context.MODE_PRIVATE)
        }
        return sharedPreferences!!.getString(key, defValue)
    }


    fun putInt(context: Context, key: String, value: Int) {
        //存储节点文件的名称，读写方式
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences("config", Context.MODE_PRIVATE)
        }
        sharedPreferences!!.edit().putInt(key, value).commit()
    }


    fun getInt(context: Context, key: String, defValue: Int): Int {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences("config", Context.MODE_PRIVATE)
        }
        return sharedPreferences!!.getInt(key, defValue)
    }


    fun remove(context: Context, key: String) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences("config", Context.MODE_PRIVATE)
        }
        sharedPreferences!!.edit().remove(key).commit()
    }
}