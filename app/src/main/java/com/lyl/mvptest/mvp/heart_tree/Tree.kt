package com.lyl.mvptest.mvp.heart_tree

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import java.util.*

/**
 * User: lyl
 * Date: 2019-07-26 11:08
 */
class Tree {
    //1 绘制树干
    //2 绘制花瓣
    //3 整体平移
    //4 花瓣掉落

    private enum class Step {
        BRANCHES_GROWING,
        BLOOMS_GROWING,
        MOVING_SNAPSHOT,
        BLOOMS_FALLING
    }

    private var step=Step.BRANCHES_GROWING

    //绘制树干
    private var branchesDx:Float?=null
    private var branchesDy:Float?=null
    private var growingBranches:LinkedList<Branch>?= LinkedList()


    companion object {
        //scale factor
        private var CROWN_RADIUS_FACTOR=0.35f
        private var STAND_FACTOR=(CROWN_RADIUS_FACTOR/0.28f)
        private var BRANCHES_FACTOR=1.3f* STAND_FACTOR
        private var resolutionFactor:Float?=null

        //snapshot
        private var treeSnapShot:SnapShot?=null
        private var snapShotPaint=Paint()
    }

    //offset
    private var snapShotDx:Float?=null
    private var xoffset:Float?=null
    private var maxoffset:Float?=null

    public constructor(canvasWidth: Int,canvasHeight: Int){
        //数据初始化
        resolutionFactor=canvasHeight/1080f
        TreeMaker.init(canvasHeight, CROWN_RADIUS_FACTOR)
        Bloom.initDisPlayParam(resolutionFactor)
        //snapshot
        var snapshotWidth=816f* STAND_FACTOR* resolutionFactor!!
        treeSnapShot= SnapShot(Bitmap.createBitmap(Math.round(snapshotWidth),canvasHeight,Bitmap.Config.ARGB_8888))
        //Branches
        var branchesWidth=375f* BRANCHES_FACTOR* resolutionFactor!!
        var branchesHeight=490* BRANCHES_FACTOR* resolutionFactor!!
        branchesDx=(snapshotWidth-branchesWidth)/2f-40f* STAND_FACTOR
        branchesDy=canvasHeight-branchesHeight
        growingBranches!!.add(TreeMaker.getBranches())
    }

    public fun draw(canvas: Canvas?) {

    }


}