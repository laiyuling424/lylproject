package com.lyl.view.heart_tree

import android.graphics.Bitmap
import android.graphics.Canvas

/**
 * Create By: lyl
 * Date: 2019-07-24 09:30
 */
class SnapShot(bitmap: Bitmap) {
    var canvas: Canvas? = null
    var bitmap: Bitmap? = null

    init {
        this.bitmap = bitmap
        canvas = Canvas(bitmap)
    }
}