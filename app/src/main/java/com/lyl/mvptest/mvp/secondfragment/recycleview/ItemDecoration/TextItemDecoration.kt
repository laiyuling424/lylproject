package com.lyl.mvptest.mvp.secondfragment.recycleview.ItemDecoration

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView


/**
 * Create By: lyl
 * Date: 2019-09-23 13:35
 */
internal class TextItemDecoration : RecyclerView.ItemDecoration() {
    private val mPaint: Paint = Paint()
    private val textPaint: Paint = Paint()

    init {
        mPaint.color = Color.YELLOW
        textPaint.color = Color.BLACK
        textPaint.textSize = 20f
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)
        if (position % 5 == 0) {
            outRect.set(0, 50, 0, 0)
        }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val index = parent.getChildAdapterPosition(child)
            if (index % 5 == 0) {
                val left = 0
                val top = child.top - 50
                val right = child.right
                val bottom = child.top
                c.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), mPaint)
            }
        }
    }


    public override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val index = parent.getChildAdapterPosition(child)
            if (index % 5 == 0) {
                val item = index / 5
                if (i < 5) {
                    if (i == 1 && child.top < 100) {
                        val left = 0
                        val top = child.top - 100
                        val right = child.right
                        val bottom = child.top - 50
                        c.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), mPaint)
                        c.drawText("这是条目1---" + item + "视图i是+" + i + "   顶部的高低" + child.top + "   index++===" + index, left.toFloat(), (top + 50).toFloat(), textPaint)
                    } else {
                        val left = 0
                        val top = 0
                        val right = child.right
                        val bottom = 50
                        c.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), mPaint)
                        if (i == 0) {
                            c.drawText("这是条目2---" + (item + 1) + "视图i是+" + i + "   顶部的高低" + child.top + "   index++===" + index, left.toFloat(), (top + 50).toFloat(), textPaint)
                        } else {
                            c.drawText("这是条目3---" + item + "视图i是+" + i + "   顶部的高低" + child.top + "   index++===" + index, left.toFloat(), (top + 50).toFloat(), textPaint)
                        }
                    }

                }
                if (i != 0) {
                    val left = 0
                    val top = child.top - 50
                    val right = child.right
                    val bottom = child.top
                    c.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), mPaint)
                    c.drawText("这是条目" + item + "视图i是+" + i + "顶部的高低" + child.top + "index++" + index, left.toFloat(), (top + 50).toFloat(), textPaint)
                }
            }
        }
    }

}