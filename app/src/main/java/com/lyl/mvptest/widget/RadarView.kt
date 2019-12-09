package com.lyl.mvptest.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorInt
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin


/**
 * Create By: lyl
 * Date: 2019-06-17 09:50
 */

class RadarView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    //全局画笔
    var paint: Paint? = null

    //最大的半径
    var radius: Float = 0f

    //层数
    var later: Int = 0

    //半径对应进度（得分），不可以超过最大半径
    var score: Float = 0f

    //实线的宽度
    var lineWidth: Float = 0f

    //得分连线的颜色
    @ColorInt
    var scoreLineColor: Int = 0

    //得分连线中心区域的颜色
    @ColorInt
    var scoreColor: Int = 0

    //几边形
    var n: Int = 0

    //文字数组,描述
    var nText: List<String>? = null

    private val mPath = Path()

    init {
        paint = Paint()
        //这里默认值使用白色，可处理掉系统渲染抗锯齿时，人眼可观察到像素颜色
        paint!!.color = Color.parseColor("#FFFFFF")
        paint!!.isAntiAlias = true
        paint!!.style = Paint.Style.STROKE
        paint!!.strokeWidth = 0f

        initAttributes(context, attrs)

        //关闭硬件加速
        setLayerType(View.LAYER_TYPE_SOFTWARE, null)
    }

    fun setTextArray(textList: List<String>) {
        this.nText = textList
    }

    private fun initAttributes(context: Context, attrs: AttributeSet?) {
        val a = context.obtainStyledAttributes(attrs, com.lyl.mvptest.R.styleable.RadarView)
        try {
            a?.run {
                radius = a.getDimension(com.lyl.mvptest.R.styleable.RadarView_radius, context.dpf2pxf(100f))
                score = a.getDimension(com.lyl.mvptest.R.styleable.RadarView_score, context.dpf2pxf(1.5f))
                lineWidth = a.getDimension(com.lyl.mvptest.R.styleable.RadarView_lineWidth, context.dpf2pxf(3f))

                scoreLineColor = a.getColor(com.lyl.mvptest.R.styleable.RadarView_scoreLineColor, Color.BLUE)
                scoreColor = a.getColor(com.lyl.mvptest.R.styleable.RadarView_scoreColor, Color.BLUE)

                later = a.getInt(com.lyl.mvptest.R.styleable.RadarView_later, 5)
                n = a.getInt(com.lyl.mvptest.R.styleable.RadarView_n, 5)

            }
        } finally {
            a?.recycle()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas == null) return
//        canvas.helpGreenCurtain(true)

        canvas.save()
        canvas.translate(width / 2f, height / 2f)
        canvas.rotate(270f)

        paint!!.color = Color.GREEN
        canvas.drawLine(-width / 2f, 0f, width / 2f, 0f, paint)//绘制x轴
        canvas.drawLine(0f, -height / 2f, 0f, height / 2f, paint)//绘制y轴


        //绘制网状图形
        drawWeb(canvas)

        //绘制文字数组
        drawTextArray(canvas)

        //绘制连接区域
        drawConnectionArea(canvas)

        canvas.restore()
    }

    private fun drawWeb(canvas: Canvas) {
        val angle: Float = 360f / n
        val anglez = (angle * PI / 180).toFloat()

        var pointXY = arrayOfNulls<PointF>(n)
        val radiuss = radius / later
        for (i in 1..later + 1) {
            radius = radiuss * i
            for (j in 0 until n) {
                pointXY.set(j, PointF(radius * cos(anglez * j), radius * sin(anglez * j)))
//                Log.d("lyll", "index:" + j + "  point(" + radius * cos(angle * j) + "," + radius * sin(angle * j) + ")")
            }
            for (j in 0 until n) {
                if (j == n - 1) canvas.drawLine(pointXY[0]!!.x, pointXY[0]!!.y, pointXY[n - 1]!!.x, pointXY[n - 1]!!.y, paint)
                else canvas.drawLine(pointXY[j]!!.x, pointXY[j]!!.y, pointXY[j + 1]!!.x, pointXY[j + 1]!!.y, paint)
            }
        }

        for (j in 0 until n) {
            paint!!.pathEffect = DashPathEffect(floatArrayOf(4f, 4f), 0f)
            canvas.drawLine(0f, 0f, pointXY[j]!!.x, pointXY[j]!!.y, paint)
        }
    }

    private fun drawTextArray(canvas: Canvas) {

    }

    private fun drawConnectionArea(canvas: Canvas) {
        //模拟分数数组
        var nScore = arrayOf<Float>(4.1f, 0.3f, 1.3f, 3.5f, 2.5f)

        //分数半径计算:nRadius=radius*nScore/later   later(层数)为满分最大值 radius为最大半径 nScore为输入分数
        var nRadius = arrayOfNulls<Float>(n)
        var pointScoreXY = arrayOfNulls<PointF>(n)
        var mRadius = 0f
        val angle: Float = 360f / n
        val anglez = (angle * PI / 180).toFloat()
        for (i in 0 until nScore.size) {
            mRadius = radius * nScore[i] / later
            nRadius.set(i, mRadius)
            pointScoreXY.set(i, PointF(mRadius * cos(anglez * i), mRadius * sin(anglez * i)))
        }

        for (j in 0 until nRadius.size) {
            paint!!.style = Paint.Style.FILL_AND_STROKE
            paint!!.strokeWidth = 3f
            paint!!.color = Color.BLUE
            paint!!.pathEffect = null

//            Log.d("lyll", "point=" + nRadius.size+"   "+j)
//            Log.d("lyll", "point=" + pointScoreXY[j]!!.toString())
            if (j == 0) mPath.moveTo(pointScoreXY[0]!!.x, pointScoreXY[0]!!.y)
//            else if (j == nRadius.size-1) mPath.lineTo(pointScoreXY[0]!!.x, pointScoreXY[0]!!.y) //好像不需要 会自动连接成封闭区域
            else mPath.lineTo(pointScoreXY[j]!!.x, pointScoreXY[j]!!.y)

            if (j == nRadius.size - 1) {
                mPath.close()
            }

            canvas.drawPath(mPath, paint)

//            //绘制区域路径
//            paint!!.utilReset()
//            paint!!.color = Color.parseColor("#CCFFFF00")
//            canvas.drawPath(mPath, paint)
//            paint!!.utilReset()
//
//            //绘制区域路径的边框
//            paint!!.color = Color.parseColor("#CCFFFF00")
//            paint!!.style = Paint.Style.STROKE
//            paint!!.strokeWidth = 3f
//            paint!!.strokeJoin = Paint.Join.ROUND
//            canvas.drawPath(mPath, paint)
//            mPath.reset()
//            paint!!.utilReset()
//
//            paint!!.color = Color.BLACK
//            if (j == n - 1) canvas.drawLine(pointScoreXY[0]!!.x, pointScoreXY[0]!!.y, pointScoreXY[nRadius.size - 1]!!.x, pointScoreXY[nRadius.size - 1]!!.y, paint)
//            else canvas.drawLine(pointScoreXY[j]!!.x, pointScoreXY[j]!!.y, pointScoreXY[j + 1]!!.x, pointScoreXY[j + 1]!!.y, paint)
        }

    }


    //辅助绿幕背景
    fun Canvas.helpGreenCurtain(debug: Boolean) {
        if (debug) {
            this.drawColor(Color.GREEN)
        }
    }

    private fun Context.dpf2pxf(dpValue: Float): Float {
        if (dpValue == 0f) return 0f
        val scale = resources.displayMetrics.density
        return (dpValue * scale + 0.5f)
    }

    fun Paint.utilReset(colorString: String? = null, @ColorInt color: Int? = null) {
        this.reset()
        this.color = color ?: Color.parseColor(colorString ?: "#FFFFFF")
        this.isAntiAlias = true
        this.style = Paint.Style.FILL
        this.strokeWidth = 0f
    }
}

