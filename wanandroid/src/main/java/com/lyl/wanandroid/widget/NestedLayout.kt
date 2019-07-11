package com.lyl.wanandroid.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.core.view.NestedScrollingParent2
import androidx.core.view.ViewCompat

/**
 * User: lyl
 * Date: 2019-07-11 11:44
 */
class NestedLayout(context: Context?, attrs: AttributeSet?) : RelativeLayout(context, attrs), NestedScrollingParent2 {

    private lateinit var image_head: View
    lateinit var recyView: View
    var top_height: Int = 0

    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        val hiddenTop = dy > 0 && scrollY < top_height
        val showTop = dy < 0 && scrollY >= 0 && !ViewCompat.canScrollVertically(target, -1)
        if (hiddenTop || showTop) {
            scrollBy(0, dy)
            consumed!![1] = dy  //消耗此次滚动值,这样子View就不会滑动,如果去掉这句父View和子View同时滚动
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        top_height = image_head.measuredHeight
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        image_head = getChildAt(0)
        recyView = getChildAt(1)
    }

    override fun onStopNestedScroll(target: View, type: Int) {
    }

    override fun onStartNestedScroll(child: View, target: View, axes: Int, type: Int): Boolean {
        return true //true 表明父容器接受嵌套滚动,如果为false 则其他方法将不会调用
    }

    override fun onNestedScrollAccepted(child: View, target: View, axes: Int, type: Int) {
    }

    override fun onNestedScroll(target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int, type: Int) {
    }


    override fun scrollTo(x: Int, y: Int) {
        var y = y
        if (y < 0) {
            y = 0
        }
        if (y > top_height) {//以防止下拉超出image高度
            y = top_height
        }
        if (y != scrollY) {
//            val layoutParams = this.layoutParams
//            var yyyy=layoutParams.width-y
//            layoutParams.height=yyyy
//            this.layoutParams=layoutParams
//            requestLayout()
            super.scrollTo(x, y)
        }
    }




}