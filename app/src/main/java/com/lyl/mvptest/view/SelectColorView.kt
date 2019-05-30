package com.lyl.mvptest.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.ImageView
import com.lyl.mvptest.R

/**
 * User: lyl
 * Date: 2019-05-30 11:01
 */

class SelectColorView(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : ImageView(context, attrs, defStyleAttr) {
    private var iconBitmap: Bitmap? = null
    var iconRadius: Float = 0.toFloat()
    var iconCenterX: Float = 0.toFloat()
    var iconCenterY: Float = 0.toFloat()
    var iconPoint: PointF? = null

    var mBitmappaint: Paint? = null
    internal var imageBitmap: Bitmap? = null
    var viewRadius: Float = 0.toFloat()
    var radius: Float = 0.toFloat()
    var canvas: Canvas? = null

    constructor(context: Context?) : this(context, null, 0)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0) {
        init()
    }

    init {
        init()
    }

    private fun init() {
        iconBitmap = BitmapFactory.decodeResource(context.resources, R.drawable.aaa)
        iconRadius = (iconBitmap!!.width / 2).toFloat()
        mBitmappaint = Paint()
        iconPoint = PointF()
        imageBitmap = (drawable as BitmapDrawable).bitmap
        radius = (imageBitmap!!.width / 2).toFloat()

        iconPoint!!.x = radius
        iconPoint!!.y = radius
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        this.canvas = canvas

        viewRadius = (this.width / 2).toFloat()
        canvas!!.drawBitmap(iconBitmap, iconPoint!!.x - iconRadius, iconPoint!!.y - iconRadius, mBitmappaint)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        var x = event!!.x
        var y = event!!.y
        var pixel: Int
        var r: Int
        var g: Int
        var b: Int
        when (event.action) {
            MotionEvent.ACTION_MOVE -> {
                proofLeft(x, y)
                pixel = getImagePixel(iconPoint!!.x, iconPoint!!.y)
                r = Color.red(pixel)
                g = Color.green(pixel)
                b = Color.blue(pixel)
                if (mChangedListener != null) {
                    mChangedListener!!.onMoveColor(r, g, b)
                }
                if (isMove) {
                    isMove = !isMove
                    invalidate()
                }
            }
            MotionEvent.ACTION_DOWN -> {
                proofLeft(x, y)
                pixel = getImagePixel(iconPoint!!.x, iconPoint!!.y)
                r = Color.red(pixel)
                g = Color.green(pixel)
                b = Color.blue(pixel)
                if (mChangedListener != null) {
                    mChangedListener!!.onMoveColor(r, g, b)
                }
                if (isMove) {
                    isMove = !isMove
                    invalidate()
                }
            }
            MotionEvent.ACTION_UP -> {
                pixel = getImagePixel(iconPoint!!.x, iconPoint!!.y)
                r = Color.red(pixel)
                g = Color.green(pixel)
                b = Color.blue(pixel)
                if (mChangedListener != null) {
                    mChangedListener!!.onColorChanged(r, g, b)
                }
            }
        }
        return true
    }

    var isMove: Boolean = false

    private fun proofLeft(x: Float, y: Float) {
        var x = x
        var y = y

        val h = x - viewRadius // 取xy点和圆点 的三角形宽
        val w = y - viewRadius// 取xy点和圆点 的三角形长
        val h2 = h * h
        val w2 = w * w
        val distance = Math.sqrt((h2 + w2).toDouble()).toFloat() // 勾股定理求 斜边距离
        if (distance > radius) { // 如果斜边距离大于半径，则取点和圆最近的一个点为x,y
            val maxX = x - viewRadius
            val maxY = y - viewRadius
            x = radius * maxX / distance + viewRadius // 通过三角形一边平行原理求出x,y
            y = radius * maxY / distance + viewRadius
        }
        iconPoint!!.x = x
        iconPoint!!.y = y

        isMove = true
    }

    fun getImagePixel(x: Float, y: Float): Int {

        val bitmap = imageBitmap
        // 为了防止越界
        var intX = x.toInt()
        var intY = y.toInt()
        if (intX < 0)
            intX = 0
        if (intY < 0)
            intY = 0
        if (intX >= bitmap!!.getWidth()) {
            intX = bitmap!!.getWidth() - 1
        }
        if (intY >= bitmap!!.getHeight()) {
            intY = bitmap!!.getHeight() - 1
        }
        return bitmap!!.getPixel(intX, intY)

    }

    public fun setOnColorChangedListenner(listenner: OnColorChangedListenner) {
        this.mChangedListener = listenner
    }

    private var mChangedListener: OnColorChangedListenner? = null

    public interface OnColorChangedListenner {

        fun onColorChanged(r: Int, g: Int, b: Int)
        fun onMoveColor(r: Int, g: Int, b: Int)
    }
}