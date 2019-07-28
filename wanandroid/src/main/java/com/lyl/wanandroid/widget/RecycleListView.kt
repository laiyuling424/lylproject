package com.lyl.wanandroid.widget

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.lyl.wanandroid.R
import com.lyl.wanandroid.listener.OnItemClickListener
import com.lyl.wanandroid.ui.fragment.first.main.MainArticleBean
import com.lyl.wanandroid.util.MyLog

import java.util.ArrayList
import java.util.Random


/**
 * User: lyl
 * Date: 2019-07-10 11:55
 */
class RecycleListView : ViewGroup, View.OnClickListener {

    private var mContext: Context? = null
    var labelTextSize: Float = 0.toFloat()
        set(size) {
            if (labelTextSize != size) {
                field = size
                val count = childCount
                for (i in 0 until count) {
                    val label = getChildAt(i) as TextView
                    label.setTextSize(TypedValue.COMPLEX_UNIT_PX, size)
                }
            }
        }
    var textPaddingLeft: Int = 0
        private set
    var textPaddingTop: Int = 0
        private set
    var textPaddingRight: Int = 0
        private set
    var textPaddingBottom: Int = 0
        private set
    var wordMargin: Int = 0
        set(margin) {
            if (wordMargin != margin) {
                field = margin
                requestLayout()
            }
        }
    var lineMargin: Int = 0
        set(margin) {
            if (lineMargin != margin) {
                field = margin
                requestLayout()
            }
        }

    private val mList = ArrayList<String>()
    internal var colors: MutableList<Int> = ArrayList()

    private val mTextColor: ColorStateList? = null
    private val mLabelBg: Drawable? = null

    var onItemClickListener: OnItemClickListener<String>? = null

    constructor(context: Context) : super(context) {
        mContext = context
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        mContext = context
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mContext = context
        init()
    }

    private fun init() {

        colors.add(R.color.aa)
        colors.add(R.color.ab)
        colors.add(R.color.ac)
        colors.add(R.color.ad)
        colors.add(R.color.ae)
        colors.add(R.color.af)
        colors.add(R.color.ag)
        colors.add(R.color.ah)
        colors.add(R.color.ai)
        colors.add(R.color.aj)
        colors.add(R.color.ak)
        colors.add(R.color.aq)
        colors.add(R.color.aw)
        colors.add(R.color.ar)
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        val count = childCount
        val maxWidth = View.MeasureSpec.getSize(widthMeasureSpec) - paddingLeft - paddingRight
        var contentHeight = 0 //记录内容的高度
        var lineWidth = 0 //记录行的宽度
        var maxLineWidth = 0 //记录最宽的行宽
        var maxItemHeight = 0 //记录一行中item高度最大的高度
        var begin = true //是否是行的开头

        for (i in 0 until count) {
            val view = getChildAt(i)
            measureChild(view, widthMeasureSpec, heightMeasureSpec)

            if (!begin) {
                lineWidth += wordMargin
            } else {
                begin = false
            }

            if (maxWidth <= lineWidth + view.measuredWidth + wordMargin) {
                contentHeight += lineMargin
                contentHeight += maxItemHeight
                maxItemHeight = 0
                maxLineWidth = Math.max(maxLineWidth, lineWidth)
                lineWidth = 0
                begin = true
            }
            maxItemHeight = Math.max(maxItemHeight, view.measuredHeight)

            lineWidth += view.measuredWidth
        }

        contentHeight += maxItemHeight
        maxLineWidth = Math.max(maxLineWidth, lineWidth)

        setMeasuredDimension(measureWidth(widthMeasureSpec, maxLineWidth),
                measureHeight(heightMeasureSpec, contentHeight))
    }

    private fun measureWidth(measureSpec: Int, contentWidth: Int): Int {
        var result = 0
        val specMode = View.MeasureSpec.getMode(measureSpec)
        val specSize = View.MeasureSpec.getSize(measureSpec)

        if (specMode == View.MeasureSpec.EXACTLY) {
            result = specSize
        } else {
            result = contentWidth + paddingLeft + paddingRight
            if (specMode == View.MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize)
            }
        }
        result = Math.max(result, suggestedMinimumWidth)
        return result
    }

    private fun measureHeight(measureSpec: Int, contentHeight: Int): Int {
        var result = 0
        val specMode = View.MeasureSpec.getMode(measureSpec)
        val specSize = View.MeasureSpec.getSize(measureSpec)

        if (specMode == View.MeasureSpec.EXACTLY) {
            result = specSize
        } else {
            result = contentHeight + paddingTop + paddingBottom
            if (specMode == View.MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize)
            }
        }
        result = Math.max(result, suggestedMinimumHeight)
        return result
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {

        var x = paddingLeft
        var y = paddingTop

        val contentWidth = right - left
        var maxItemHeight = 0

        val count = childCount
        for (i in 0 until count) {
            val view = getChildAt(i)

            if (contentWidth < x + view.measuredWidth + paddingRight) {
                x = paddingLeft
                y += lineMargin
                y += maxItemHeight
                maxItemHeight = 0
            }
            view.layout(x, y, x + view.measuredWidth, y + view.measuredHeight)
            x += view.measuredWidth
            x += wordMargin
            maxItemHeight = Math.max(maxItemHeight, view.measuredHeight)
        }
    }

    private fun addViewText(data: String, pos: Int) {
        val textView = TextView(mContext)
        textView.setPadding(15, 15, 15, 15)
        textView.textSize = 16f
        val random = Random()
        textView.setTextColor(resources.getColor(colors[random.nextInt(colors.size)]))
        addView(textView)
        textView.text = data

        textView.setOnClickListener { onItemClickListener!!.itemClick(data, pos) }
    }

    fun setListText(list: ArrayList<String>?) {
        removeAllViews()
        mList.clear()

        if (list != null) {
            mList.addAll(list)
            val size = list.size
            for (i in 0 until size) {
                addViewText(list[i] as String, i)
            }
        }
    }

    fun <T> getListText(): List<T> {
        return mList as List<T>
    }


    override fun onClick(v: View) {

    }

    fun setLabelTextPadding(left: Int, top: Int, right: Int, bottom: Int) {
        if (textPaddingLeft != left || textPaddingTop != top
                || textPaddingRight != right || textPaddingBottom != bottom) {
            textPaddingLeft = left
            textPaddingTop = top
            textPaddingRight = right
            textPaddingBottom = bottom
            val count = childCount
            for (i in 0 until count) {
                val label = getChildAt(i) as TextView
                label.setPadding(left, top, right, bottom)
            }
        }
    }

    companion object {

        fun sp2px(context: Context, spVal: Float): Int {
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                    spVal, context.resources.displayMetrics).toInt()
        }
    }

}
