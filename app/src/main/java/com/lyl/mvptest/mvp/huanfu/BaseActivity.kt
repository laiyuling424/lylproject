package com.lyl.mvptest.mvp.huanfu

import android.app.Activity
import android.os.Bundle
import androidx.core.view.LayoutInflaterCompat

/**
 * User: lyl
 * Date: 2019-07-27 10:17
 */
open class BaseActivity : Activity() {

    var skinFactory: SkinFactory? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SkinManager.getSkinManager().setContext(this@BaseActivity)
        skinFactory = SkinFactory()
        LayoutInflaterCompat.setFactory2(layoutInflater, skinFactory!!)
    }

    public fun apply() {
        skinFactory!!.apply()
    }

    override fun onResume() {
        super.onResume()
        skinFactory!!.apply()
    }
}