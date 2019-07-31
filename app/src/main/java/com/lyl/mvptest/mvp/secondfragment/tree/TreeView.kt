package com.lyl.mvptest.mvp.secondfragment.tree

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import java.util.*

/**
 * User: lyl
 * Date: 2019-07-24 09:22
 */
class TreeView : View {

    var snapShot: SnapShot? = null
    var paint: Paint? = null
    var growingBranches: LinkedList<Branch>? = LinkedList()
    var hasEnd = false

    constructor(context: Context) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        paint = Paint()
        growingBranches!!.add(getBranches())
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        snapShot = SnapShot(Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888))
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        drawBranches()

        canvas!!.drawBitmap(snapShot!!.bitmap, 0f, 0f, paint)

        if (!hasEnd) invalidate()
    }

    private fun drawBranches() {
        if (!growingBranches!!.isEmpty()) {
            var tempBranches: LinkedList<Branch>? = null
            snapShot!!.canvas!!.save()
            snapShot!!.canvas!!.translate(width/2-434f,height-980f)
            val iterator = growingBranches!!.iterator()
            while (iterator.hasNext()) {
                var branch = iterator.next()
                if (!branch.grow(snapShot!!.canvas!!, paint!!, 2f)) {
                    iterator.remove()
                    if (branch.childList != null) {
                        if (tempBranches == null) {
                            tempBranches = branch.childList
                        } else {
                            tempBranches.addAll(branch.childList!!)
                        }
                    }
                }
            }


            snapShot!!.canvas!!.restore()

            if (tempBranches != null) {
                growingBranches!!.addAll(tempBranches)
            }
            if (growingBranches!!.isEmpty()) {
                hasEnd = true
            }
        }
    }

    fun getBranches(): Branch {
        var data = Array(11) { IntArray(10) }
        data[0] = intArrayOf(0, -1, 217, 490, 252, 60, 182, 10, 30, 100)
        data[1] = intArrayOf(1, 0, 222, 310, 137, 227, 22, 210, 13, 100)
        data[2] = intArrayOf(2, 1, 132, 245, 116, 240, 76, 205, 2, 40)
        data[3] = intArrayOf(3, 0, 232, 255, 282, 166, 362, 155, 12, 100)
        data[4] = intArrayOf(4, 3, 260, 210, 330, 219, 343, 236, 3, 80)
        data[5] = intArrayOf(5, 0, 217, 91, 219, 58, 216, 27, 3, 40)
        data[6] = intArrayOf(6, 0, 228, 207, 95, 57, 10, 54, 9, 80)
        data[7] = intArrayOf(7, 6, 109, 96, 65, 63, 53, 15, 2, 40)
        data[8] = intArrayOf(8, 6, 180, 155, 117, 125, 77, 140, 4, 60)
        data[9] = intArrayOf(9, 0, 228, 167, 290, 62, 360, 31, 6, 100)
        data[10] = intArrayOf(10, 9, 272, 103, 328, 87, 330, 81, 2, 80)

        var n = data.size
        var branches = arrayOfNulls<Branch>(n)
        for (i in 0 until n) {
            branches[i] = Branch(data[i])
            var parent = data[i][1]
            if (parent != -1) {
                branches[parent]!!.addChild(branches[i])
            }
        }

        return branches[0]!!
    }

}