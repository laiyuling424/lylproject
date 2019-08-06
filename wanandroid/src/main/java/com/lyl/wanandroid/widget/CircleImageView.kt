package com.lyl.wanandroid.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.widget.ImageView
import android.graphics.drawable.BitmapDrawable


/**
 * User: lyl
 * Date: 2019-08-06 17:23
 */
class CircleImageView : ImageView {

    private var radius = 0f

    private var paint: Paint? = null

    private var mMatrix: Matrix? = null

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        paint = Paint()
        paint!!.isAntiAlias = true
        mMatrix = Matrix()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        radius = Math.min(width, height) / 2f
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas?) {
        if (drawable == null) {
            super.onDraw(canvas)
            return
        }
        if (drawable is BitmapDrawable) {
            paint!!.shader = initBitmapShader(drawable as BitmapDrawable)
            canvas!!.drawCircle(width / 2f, height / 2f, radius, paint)
            return
        }
        super.onDraw(canvas)
    }

    /**
     * 获取ImageView中资源图片的Bitmap，利用Bitmap初始化图片着色器,通过缩放矩阵将原资源图片缩放到铺满整个绘制区域，避免边界填充
     */
    private fun initBitmapShader(drawable: BitmapDrawable): BitmapShader {
        val bitmap = drawable.bitmap
        val bitmapShader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        val scale = Math.max(width / bitmap.width, height / bitmap.height).toFloat()
        mMatrix!!.setScale(scale, scale)//将图片宽高等比例缩放，避免拉伸
        bitmapShader.setLocalMatrix(matrix)
        return bitmapShader
    }
}