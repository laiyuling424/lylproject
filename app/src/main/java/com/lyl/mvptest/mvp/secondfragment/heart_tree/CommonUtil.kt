package com.lyl.mvptest.mvp.secondfragment.heart_tree

import android.graphics.Paint
import kotlin.random.Random

/**
 * Create By: lyl
 * Date: 2019-07-26 11:11
 */
class CommonUtil {

    companion object {

        private var RANDOM = Random(System.currentTimeMillis())
        private var PAINT = Paint()

        public fun random(n: Int): Int {
            return RANDOM.nextInt(n + 1)
        }

        public fun random(m: Int, n: Int): Int {
            var d = n - m
            return m + RANDOM.nextInt(d + 1)
        }

        public fun random(m: Float, n: Float): Float {
            var d = n - m
            return m + RANDOM.nextFloat() * d
        }

        public fun getPaint(): Paint {
            return PAINT
        }
    }
}