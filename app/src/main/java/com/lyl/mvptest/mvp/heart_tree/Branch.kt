package com.lyl.mvptest.mvp.heart_tree

import android.graphics.Canvas
import android.graphics.Paint
import java.util.*

/**
 * User: lyl
 * Date: 2019-07-26 11:21
 */
public class Branch(data: IntArray) {

    companion object {
        public var branchColor=0xFF775533
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
        cp[0] = Point(data[2].toFloat(), data[3].toFloat())
        cp[1] = Point(data[4].toFloat(), data[5].toFloat())
        cp[2] = Point(data[6].toFloat(), data[7].toFloat())
        radius = data[8].toFloat()
        maxLength = data[9].toFloat()
        part = 1 / maxLength!!
    }

    public fun grow(canvas: Canvas,scaleFactor:Float):Boolean{
        if (currentLength< maxLength!!){
            bezier(currentLength!! * part!!)
            draw(canvas, scaleFactor)
            radius = radius!! * 0.97f
            currentLength = currentLength!! + 1
            return true
        }else{
            return false
        }
    }

    private fun bezier(t: Float) {
        var c0 = (1 - t) * (1 - t)
        var c1 = 2 * t * (1 - t)
        var c2 = t * t
        growX = c0 * cp[0]!!.x!!.toFloat() + c1 * cp[1]!!.x!!.toFloat() + c2 * cp[2]!!.x!!.toFloat()
        growY = c0 * cp[0]!!.y!!.toFloat() + c1 * cp[1]!!.y!!.toFloat() + c2 * cp[2]!!.y!!.toFloat()
    }

    private fun draw(canvas: Canvas, scaleFactory: Float) {
        var paint=CommonUtil.getPaint()
        paint.color= branchColor.toInt()
        canvas.save()
        canvas.scale(scaleFactory, scaleFactory)
        canvas.translate(growX!!, growY!!)
        canvas.drawCircle(0f, 0f, radius!!,paint)
        canvas.restore()
    }

    fun addChild(branch: Branch?) {
        if (childList == null) {
            childList = LinkedList()
        } else {
            childList!!.add(branch!!)
        }
    }


}