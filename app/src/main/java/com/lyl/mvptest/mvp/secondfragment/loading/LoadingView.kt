package com.lyl.mvptest.mvp.secondfragment.loading

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator
import com.lyl.mvptest.R

/**
 * User: lyl
 * Date: 2019-07-31 09:39
 */
class LoadingView : SurfaceView, SurfaceHolder.Callback, Runnable {

    enum class LoadingState {
        DOWN, UP, FREE
    }

    private var loadingState = LoadingState.DOWN
    private var ball_color: Int? = null
    private var line_color: Int? = null
    private var line_width: Int? = null
    private var stroke_width: Int? = null

    private var canvas: Canvas? = null
    private var surfaceHoder: SurfaceHolder? = null
    private var path: Path? = null
    private var paint: Paint? = null

    private var downDistance: Float? = null
    private var upDistance: Float? = 0f
    private var freeDownDistance: Float? = null

    private var downControl: ValueAnimator? = null
    private var upControl: ValueAnimator? = null
    private var freeDownControl: ValueAnimator? = null
    private var animatorSet: AnimatorSet? = null

    private var isRunning: Boolean = false
    private var isAnimationShowing: Boolean = false

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initAttr(context, attrs)
        init()
        initControl()
    }

    private fun initControl() {
        downControl = ValueAnimator.ofFloat(0f, 1f)
        downControl!!.duration = 300
        downControl!!.interpolator = AccelerateDecelerateInterpolator()
        downControl!!.addUpdateListener {
            downDistance = 50f * (it.animatedValue as Float)
        }
        downControl!!.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
            }

            override fun onAnimationStart(animation: Animator?) {
                loadingState = LoadingState.DOWN
                isAnimationShowing = true
            }
        })


        upControl = ValueAnimator.ofFloat(0f, 1f)
        upControl!!.duration = 300
        upControl!!.interpolator = DecelerateInterpolator()
        upControl!!.addUpdateListener {
            upDistance = 50f * (it.animatedValue as Float)
            if (upDistance!! >= 50 && !freeDownControl!!.isStarted && !freeDownControl!!.isRunning) {
                freeDownControl!!.start()
            }
        }
        upControl!!.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
            }

            override fun onAnimationStart(animation: Animator?) {
                loadingState = LoadingState.UP
            }
        })


        freeDownControl = ValueAnimator.ofFloat(0f, (2 * Math.sqrt(10.toDouble())).toFloat())
        freeDownControl!!.duration = 300
        freeDownControl!!.interpolator = LinearInterpolator()
        freeDownControl!!.addUpdateListener {
            var t = it.animatedValue as Float
            freeDownDistance = (10 * Math.sqrt(10.toDouble())*t).toFloat() - 5f * t * t
        }
        freeDownControl!!.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                isAnimationShowing = false
                startAllAnimator()
            }

            override fun onAnimationStart(animation: Animator?) {
                loadingState = LoadingState.FREE
            }
        })

        animatorSet = AnimatorSet()
        animatorSet!!.play(downControl).before(upControl)
    }

    private fun initAttr(context: Context?, attrs: AttributeSet?) {
        var typedArray: TypedArray = context!!.obtainStyledAttributes(attrs, R.styleable.LoadingView)
        ball_color = typedArray.getColor(R.styleable.LoadingView_ball_color, Color.BLUE)
        line_color = typedArray.getColor(R.styleable.LoadingView_line_color, Color.BLUE)
        line_width = typedArray.getDimensionPixelOffset(R.styleable.LoadingView_line_width, 200)
        stroke_width = typedArray.getDimensionPixelOffset(R.styleable.LoadingView_stroke_width, 2)
        typedArray.recycle()
    }

    private fun init() {
        paint = Paint()
        paint!!.isAntiAlias = true
        paint!!.strokeWidth = stroke_width!!.toFloat()
        path = Path()
        surfaceHoder = holder
        surfaceHoder!!.addCallback(this)
    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {

    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
        isRunning = false
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        isRunning = true
        Thread(this).start()
    }

    override fun run() {
        while (true) {
            drawView()
            try {
                Thread.sleep(100)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun drawView() {
        try {
            if (surfaceHoder == null) {

            } else {
                canvas = surfaceHoder!!.lockCanvas()
                canvas!!.drawColor(Color.WHITE)
                paint!!.color = line_color!!
                path!!.reset()
                path!!.moveTo(width / 2 - line_width!! / 2f, height / 2f)
                if (loadingState == LoadingState.DOWN) {
                    //三阶贝塞尔曲线
                    path!!.rQuadTo(line_width!! / 2f,2* downDistance!!, line_width!!.toFloat(), 0f)
                    paint!!.color = line_color!!
                    paint!!.style = Paint.Style.STROKE
                    canvas!!.drawPath(path, paint)
                    paint!!.color = ball_color!!
                    paint!!.style = Paint.Style.FILL
                    canvas!!.drawCircle(width / 2f, height / 2f + downDistance!! - 10 - stroke_width!! / 2f, 10f, paint!!)
                } else {
                    // up or free down
                    path!!.rQuadTo(line_width!! / 2f, 2f * (50 - upDistance!!), line_width!!.toFloat(), 0f)
                    paint!!.color = line_color!!
                    paint!!.style = Paint.Style.STROKE
                    canvas!!.drawPath(path, paint)

                    paint!!.color = ball_color!!
                    paint!!.style = Paint.Style.FILL
                    if (loadingState == LoadingState.FREE) {
                        canvas!!.drawCircle(width / 2f, height / 2f - freeDownDistance!! - 10 - stroke_width!! / 2f, 10f, paint)
                    } else {
                        canvas!!.drawCircle(width / 2f, height / 2f + (50 - upDistance!!) - 10 - stroke_width!! / 2f, 10f, paint)
                    }
                }
                paint!!.color = ball_color!!
                paint!!.style = Paint.Style.FILL
                canvas!!.drawCircle(width / 2f - line_width!! / 2f, height / 2f, 10f, paint!!)
                canvas!!.drawCircle(width / 2f + line_width!! / 2f, height / 2f, 10f, paint!!)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            if (canvas == null) {

            } else {
                surfaceHoder!!.unlockCanvasAndPost(canvas)
            }
        }
    }

    public fun startAllAnimator() {
        if (isAnimationShowing) return
        if (animatorSet!!.isRunning) {
            animatorSet!!.end()
            animatorSet!!.cancel()
        }
        animatorSet!!.start()
    }

    class ShockInterpolator : Interpolator {
        override fun getInterpolation(input: Float): Float {
            return ((1 - Math.exp((-3).toDouble() * input.toDouble())) * Math.cos(10.toDouble() * input.toDouble())).toFloat()
        }
    }
}