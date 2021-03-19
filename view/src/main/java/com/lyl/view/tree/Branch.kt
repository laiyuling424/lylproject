package com.lyl.view.tree

import android.graphics.Canvas
import android.graphics.Paint

import java.util.*

/**
 * Create By: lyl
 * Date: 2019-07-24 09:33
 */
class Branch(data: IntArray) {

    companion object {
        var branchColor = 0xff775533
    }

    private var cp = arrayOfNulls<Point>(3)
    private var radius: Float? = null
    private var maxLength: Float? = null
    private var currentLength: Int = 0
    private var part: Float? = null
    private var growX: Float? = null
    private var growY: Float? = null
    var childList: LinkedList<Branch>? = LinkedList()

    init {
        cp[0] = Point(data[2].toDouble(), data[3].toDouble())
        cp[1] = Point(data[4].toDouble(), data[5].toDouble())
        cp[2] = Point(data[6].toDouble(), data[7].toDouble())
        radius = data[8].toFloat()
        maxLength = data[9].toFloat()
        part = 1 / maxLength!!
    }

    fun grow(canvas: Canvas, paint: Paint, scaleFactory: Float): Boolean {
        return if (currentLength!! < maxLength!!) {
            bezier(currentLength!! * part!!)
            draw(canvas, paint, scaleFactory)
            radius = radius!! * 0.97f
            currentLength = currentLength!! + 1
            true
        } else {
            false
        }
    }

    private fun draw(canvas: Canvas, paint: Paint, scaleFactory: Float) {
        paint.color = branchColor.toInt()
        canvas.save()
        canvas.scale(scaleFactory, scaleFactory)
        canvas.translate(growX!!, growY!!)
        canvas.drawCircle(0f, 0f, radius!!, paint)
        canvas.restore()
    }

    private fun bezier(t: Float) {
        var c0 = (1 - t) * (1 - t)
        var c1 = 2 * t * (1 - t)
        var c2 = t * t
        growX = c0 * cp[0]!!.x!!.toFloat() + c1 * cp[1]!!.x!!.toFloat() + c2 * cp[2]!!.x!!.toFloat()
        growY = c0 * cp[0]!!.y!!.toFloat() + c1 * cp[1]!!.y!!.toFloat() + c2 * cp[2]!!.y!!.toFloat()
    }

    fun addChild(branch: Branch?) {
        if (childList == null) {
            childList = LinkedList()
        } else {
            childList!!.add(branch!!)
        }
    }

}