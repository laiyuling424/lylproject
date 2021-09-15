package com.lyl.appmonitor.ui

import android.app.Activity
import android.os.Handler
import android.os.Looper
import android.view.Choreographer
import android.view.ViewTreeObserver

/**
 * author : lyl
 * e-mail : laiyuling424@gmail.com
 * date   : 6/3/21 2:51 PM
 */
object FpsMonitor {

    private const val FPS_INTERVAL_TIME = 1000L
    private var count = 0
    private var isFpsOpen = false
    private val fpsRunnable by lazy { FpsRunnable() }
    private val mainHandler by lazy { Handler(Looper.getMainLooper()) }
    private val listeners = arrayListOf<(Int) -> Unit>()

    fun startMonitor(listener: (Int) -> Unit) {
        // 防止重复开启
        if (!isFpsOpen) {
            isFpsOpen = true
            listeners.add(listener)
            mainHandler.postDelayed(fpsRunnable, FPS_INTERVAL_TIME)
            Choreographer.getInstance().postFrameCallback(fpsRunnable)
        }

//        val aa :Activity? = null
//        aa!!.window.decorView.viewTreeObserver.addOnDrawListener(object : ViewTreeObserver.OnDrawListener{
//            override fun onDraw() {
//
//            }
//
//        })
    }

    fun stopMonitor() {
        count = 0
        mainHandler.removeCallbacks(fpsRunnable)
        Choreographer.getInstance().removeFrameCallback(fpsRunnable)
        isFpsOpen = false
    }

    class FpsRunnable : Choreographer.FrameCallback, Runnable {
        override fun doFrame(frameTimeNanos: Long) {
            count++
            Choreographer.getInstance().postFrameCallback(this)
        }

        override fun run() {
            listeners.forEach { it.invoke(count) }
            count = 0
            mainHandler.postDelayed(this, FPS_INTERVAL_TIME)
        }
    }
}