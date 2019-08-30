package com.lyl.wanandroid

import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.Looper

/**
 * User: lyl
 * Date: 2019-06-11 14:54
 */

open class WanAdnroidApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        context = this
    }


    companion object {
        private var handler:Handler? = null
        private var context:Application? = null
        fun getContext():Context{
            return context!!
        }
        fun getHandler():Handler{
            return handler!!
        }
    }



}