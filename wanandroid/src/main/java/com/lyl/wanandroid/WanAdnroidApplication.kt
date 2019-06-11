package com.lyl.wanandroid

import android.app.Application
import android.content.Context

/**
 * User: lyl
 * Date: 2019-06-11 14:54
 */

open class WanAdnroidApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        context=this
    }

    companion object {

        lateinit var context:Context

        fun getGlobalContext():Context{
            return context
        }
    }
}