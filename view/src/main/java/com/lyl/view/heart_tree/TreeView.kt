package com.lyl.view.heart_tree

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View

/**
 * Create By: lyl
 * Date: 2019-07-26 11:04
 */
class TreeView : View {

    public var tree: Tree? = null

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onDraw(canvas: Canvas?) {
        if (tree == null) {
            tree = Tree(width, height)
        }
        tree!!.draw(canvas)
        postInvalidate()
    }
}