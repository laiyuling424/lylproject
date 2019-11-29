package com.lyl.wanandroid.widget

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent.ACTION_UP
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_DOWN
import android.view.MotionEvent.ACTION_MOVE
import android.widget.LinearLayout
import com.lyl.wanandroid.util.MyLog


/**
 * Create By: lyl
 * Date: 2019-07-10 17:07
 *
 * 失败了 应该要用NestedcollingParent来实现
 */
class SlidingLinearLayout : LinearLayout {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    var lastY: Float = 0f
    var lastY1: Float = 0f
    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        MyLog.Logd("this.getChildAt(0).bottom===" + this.getChildAt(0).bottom)
        var y = ev!!.y

        when (ev!!.action) {

            ACTION_DOWN -> {
                lastY = y
//                if (this.getChildAt(0).bottom>0){
//                    return true
//                }
            }
            ACTION_MOVE -> {

                var offsetY = y - lastY!!


                var view = this.getChildAt(0)
                if (offsetY > 0) {
                    if (view.top < 0) {
                        MyLog.Logd("this.getChildAt(0).top===" + this.getChildAt(0).top)
                        return true
                    }
                } else {
                    if (view.bottom > 0) {
                        MyLog.Logd("this.getChildAt(1).bottom===" + this.getChildAt(0).bottom)
                        return true
                    }
                }
            }
        }
        return super.onInterceptTouchEvent(ev)
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        var y = event!!.y

        MyLog.Logd("this.getChildAt(2).bottom===" + this.getChildAt(0).bottom)
        when (event!!.action) {
            ACTION_DOWN -> {
                lastY1 = y
                if (this.getChildAt(0).bottom > 0) {
                    return true
                }
            }
            ACTION_UP -> {

            }
            ACTION_MOVE -> {
                MyLog.Logd("465864536156486135218564681516")
                var offsetY = y - lastY1
                var view = this.getChildAt(0)
                MyLog.Logd("offsetY0===" + offsetY)
                MyLog.Logd("y===" + y + "      lastY===" + lastY1)
                if (view.bottom == 0) {

                } else {
                    if (offsetY > 0) {
                        MyLog.Logd("aaaaaaaaaaaaaaaaaaaaaaaaaaa")
                        if (view.top < 0) {
                            view.layout(view.left, view.top + offsetY.toInt(), view.right, view.bottom + offsetY.toInt())
                        }
                    } else {
                        MyLog.Logd("zzzzzzzzzzzzzzzzzzzzzzzzz")
                        if (view.bottom > 0) {
                            view.layout(view.left, view.top - offsetY.toInt(), view.right, view.bottom - offsetY.toInt())
                        }
                    }
                    return true
                }

            }

        }
        return super.onTouchEvent(event)
    }
}