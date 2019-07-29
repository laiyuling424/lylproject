package com.lyl.mvptest.mvp.heart_tree

import android.graphics.Canvas
import android.graphics.Color

/**
 * User: lyl
 * Date: 2019/7/26 21:05
 */
open class Bloom {

    companion object {
        var sMaxScale = 0.2f
        var sMaxRadius = Math.round(sMaxScale * Heart.getRadius())
        var sFactor: Float? = null

        /**
         * 初始化显示参数
         *
         * @param resolutionFactor 根据屏幕分辨率设定缩放因子
         */
        public fun initDisplayParam(resolutionFactor: Float) {
            sFactor = resolutionFactor
            sMaxScale = 0.2f * resolutionFactor
            sMaxRadius = Math.round(sMaxScale * Heart.getRadius())
        }
    }

    var position: Point? = null
    var color: Int? = null
    var angle: Float? = null
    var scale: Float? = 0f
    private var isOdd: Boolean = false

    constructor(position: Point) {
        this.position = position
        this.color = Color.argb(CommonUtil.random(75, 255), 255, CommonUtil.random(255), CommonUtil.random(255))
        this.angle = CommonUtil.random(360).toFloat()
    }

    public fun grow(canvas: Canvas): Boolean {
        if (scale!! <= sMaxScale) {
            if (isOdd) {
                scale = scale!! + 0.0125f * sFactor!!
                draw(canvas)
            }
            isOdd = !isOdd
            return true
        } else {
            return false
        }
    }

    fun getRadius(): Float {
        return Heart.getRadius() * scale!!
    }

    private fun draw(canvas: Canvas) {
        var paint = CommonUtil.getPaint()
        paint.color = color!!
        var r = getRadius()

        canvas.save()
        canvas.translate(position!!.x!!, position!!.y!!)
        canvas.saveLayerAlpha(-r, -r, r, r, Color.alpha(color!!))
        canvas.rotate(angle!!)
        canvas.scale(scale!!, scale!!)
        canvas.drawPath(Heart.getPath(), paint)
        canvas.restore()
        canvas.restore()
    }
}