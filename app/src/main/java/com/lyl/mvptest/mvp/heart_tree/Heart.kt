package com.lyl.mvptest.mvp.heart_tree

import java.nio.file.Path

/**
 * User: lyl
 * Date: 2019/7/26 21:06
 */
class Heart {

    companion object {
        private var PATH = android.graphics.Path()
        private var SCALE_FACTOR = 10F
        private var RADIUS = 18 * SCALE_FACTOR

        //x=16sin^3t
        //y=13*cost -5*cos2t -2 *cos3t -cos4t
        var n = 100
        var points = arrayOfNulls<Point>(100)
        var t = 0f
        var dt = (2 * Math.PI / (n - 1)).toFloat()

        init {
            for (i in 0 until n) {
                var x = (16 * Math.pow(Math.sin(t.toDouble()), 3.0)).toFloat()
                var y = (13 * Math.cos(t.toDouble()) - 5 * Math.cos(2 * t.toDouble()) - 2 * Math.cos(3 * t.toDouble()) - Math.cos(4 * t.toDouble())).toFloat()
                points[i] = Point(x * SCALE_FACTOR, y * SCALE_FACTOR)
                t += dt
                if (i == 0) {
                    PATH.moveTo(points[0]!!.x!!, points[0]!!.y!!)
                } else {
                    PATH.lineTo(points[i]!!.x!!, points[i]!!.y!!)
                }
            }
            PATH.close()
        }

        public fun getPath(): android.graphics.Path {
            return PATH
        }

        public fun getRadius(): Float {
            return RADIUS
        }
    }

}