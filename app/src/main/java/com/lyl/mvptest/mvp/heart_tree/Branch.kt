package com.lyl.mvptest.mvp.heart_tree

import java.util.*

/**
 * User: lyl
 * Date: 2019-07-26 11:21
 */
public class Branch{

    companion object {
        public var branchColor=0xFF775533
    }

    private var cp = arrayOfNulls<Point>(3)
    private var radius: Float? = null
    private var maxLength: Float? = null
    private var currentLength: Int = 0
    private var part: Float? = null
    private var growX: Float? = null
    private var growY: Float? = null
    var childList: LinkedList<Branch>? = LinkedList()

}