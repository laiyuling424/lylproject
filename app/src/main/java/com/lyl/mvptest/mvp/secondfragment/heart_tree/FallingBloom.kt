package com.lyl.mvptest.mvp.secondfragment.heart_tree

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.PorterDuff
import kotlinx.android.synthetic.main.item_view.view.*

/**
 * User: lyl
 * Date: 2019/7/26 21:06
 */
class FallingBloom(position: Point) : Bloom(position) {

    private var xSpeed: Float? = null
    private var ySpeed: Float? = null

    private var snapshot: SnapShot? = null
    private var validate = false

    init {
        color = 0xffff0000.toInt()
        scale = sMaxScale
        validate = false
        snapshot = SnapShot(Bitmap.createBitmap(sMaxRadius * 2, sMaxRadius * 2, Bitmap.Config.ARGB_8888))
        initSpeed()
    }

    /**
     * 设置掉落速度
     */
    private fun initSpeed() {
        xSpeed = CommonUtil.random(1.6f * sFactor!!, 2.7f * sFactor!!)
        ySpeed = CommonUtil.random(0.5f * sFactor!!, 1.5f * sFactor!!)
    }

    public fun fall(canvas: Canvas, fMaxY: Float): Boolean {
        if (!validate) {
            //绘制飘落的心形
            makeSnapShot()
            validate = true
        }
        var r = getRadius()
        if (position!!.y!! - r < fMaxY) {
            position!!.x = position!!.x!! - xSpeed!!
            position!!.y = position!!.y!! + xSpeed!!
            angle = angle!! + 1f
            draw(canvas)
            return true
        } else {
            return false
        }
    }

    private fun makeSnapShot() {
        //绘制飘落的心形
        var paint = CommonUtil.getPaint()
        paint.color = color!!
        var canvas = snapshot!!.canvas
        canvas!!.drawColor(0, PorterDuff.Mode.CLEAR)
        canvas!!.save()
        canvas.translate(sMaxRadius.toFloat(), sMaxRadius.toFloat())
        canvas.rotate(angle!!)
        canvas.scale(scale!!, scale!!)
        canvas.drawPath(Heart.getPath(), paint)
        canvas.restore()
        paint.isAntiAlias = false
    }

    private fun draw(canvas: Canvas) {
        var paint = CommonUtil.getPaint()
        paint.color = color!!
        canvas.save()
        canvas.translate(position!!.x!!, position!!.y!!)
        canvas.rotate(angle!!)
        canvas.translate(-sMaxRadius.toFloat(), -sMaxRadius.toFloat())
        canvas.drawBitmap(snapshot!!.bitmap, 0f, 0f, paint)
        canvas.restore()
    }

    public fun reset(x: Float, y: Float) {
        this.position!!.x = x
        this.position!!.y = y
        this.color = Color.argb(255, 255, CommonUtil.random(255), CommonUtil.random(255))
        this.angle = CommonUtil.random(360).toFloat()
        initSpeed()
        validate = false
    }
}