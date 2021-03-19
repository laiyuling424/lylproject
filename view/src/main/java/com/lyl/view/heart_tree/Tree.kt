package com.lyl.view.heart_tree

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import java.util.*
import kotlin.collections.ArrayList

/**
 * Create By: lyl
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

    private var step = Step.BRANCHES_GROWING

    //绘制树干
    private var branchesDx: Float? = null
    private var branchesDy: Float? = null
    private var growingBranches: LinkedList<Branch>? = LinkedList()


    companion object {
        //scale factor
        private var CROWN_RADIUS_FACTOR = 0.35f
        private var STAND_FACTOR = (CROWN_RADIUS_FACTOR / 0.28f)
        private var BRANCHES_FACTOR = 1.3f * STAND_FACTOR
        private var resolutionFactor: Float? = null

        //snapshot
        private var treeSnapShot: SnapShot? = null
        private var snapShotPaint = Paint()

        //Bloom params
        private final var BLOOM_NUM = 200
        private final var BLOOMING_NUM = BLOOM_NUM / 8
    }

    //offset
    private var snapShotDx: Float? = 0f
    private var xoffset: Float? = 0f
    private var maxoffset: Float? = null

    //falling bloom
    private var fMaxY: Float? = null
    private var fallingBlooms: ArrayList<FallingBloom>? = ArrayList()

    //crown of a tree
    private var bloomDx: Float? = null
    private var bloomDy: Float? = null
    private var growingBlooms: LinkedList<Bloom>? = LinkedList()
    private var cacheBlooms: LinkedList<Bloom>? = LinkedList()


    public constructor(canvasWidth: Int, canvasHeight: Int) {
        //数据初始化
        resolutionFactor = canvasHeight / 1080f
        TreeMaker.init(canvasHeight, CROWN_RADIUS_FACTOR)
        Bloom.initDisplayParam(resolutionFactor!!)
        //snapshot
        var snapshotWidth = 816f * STAND_FACTOR * resolutionFactor!!
        treeSnapShot = SnapShot(Bitmap.createBitmap(Math.round(snapshotWidth), canvasHeight, Bitmap.Config.ARGB_8888))
        snapShotDx = (canvasWidth - snapshotWidth) / 2f
        //Branches
        var branchesWidth = 375f * BRANCHES_FACTOR * resolutionFactor!!
        var branchesHeight = 490 * BRANCHES_FACTOR * resolutionFactor!!
        branchesDx = (snapshotWidth - branchesWidth) / 2f - 40f * STAND_FACTOR
//        branchesDx = (snapshotWidth - branchesWidth) / 2f
        branchesDy = canvasHeight - branchesHeight
        growingBranches!!.add(TreeMaker.getBranches())
        //Blooms
        bloomDx = snapshotWidth / 2f
        bloomDy = 435f * STAND_FACTOR * resolutionFactor!!
        TreeMaker.fillBloom(cacheBlooms!!, BLOOM_NUM)

        //Moving snapshot
        maxoffset = (canvasWidth - snapshotWidth) / 2f - 40f

        //Falling blooms
        fMaxY = canvasHeight - bloomDy!!
        TreeMaker.fillFallingBloom(fallingBlooms!!, 3)
    }

    public fun draw(canvas: Canvas?) {
        canvas!!.drawColor(0xffffee)
        canvas.save()
        canvas.translate(snapShotDx!! + xoffset!!, 0f)
        when (step) {
            Step.BRANCHES_GROWING -> {
                drawBranches()
                drawSnapshot(canvas)
            }
            Step.BLOOMS_GROWING -> {
                drawBlooms()
                drawSnapshot(canvas)
            }
            Step.MOVING_SNAPSHOT -> {
                movingSnapshot()
                drawSnapshot(canvas)
            }
            Step.BLOOMS_FALLING -> {
                drawFallingBloom(canvas)
                drawSnapshot(canvas)
            }
        }
        canvas.restore()
    }

    private fun drawFallingBloom(canvas: Canvas) {
        var iterator = fallingBlooms!!.iterator()
        canvas.save()
        canvas.translate(bloomDx!!, bloomDy!!)
        while (iterator.hasNext()) {
            val bloom = iterator.next()
            if (!bloom.fall(canvas, fMaxY!!)) {
                iterator.remove()
                TreeMaker.recycleBloom(bloom)
            }
        }
        canvas.restore()
        if (fallingBlooms!!.size < 3) {
            TreeMaker.fillFallingBloom(fallingBlooms!!, CommonUtil.random(1, 2))
        }
    }

    private fun movingSnapshot() {
        if (xoffset!! > maxoffset!!) {
            step = Step.BLOOMS_FALLING
        } else {
            xoffset = xoffset!! + 4f
        }
    }

    private fun drawBlooms() {
        while (growingBlooms!!.size < BLOOMING_NUM && !cacheBlooms!!.isEmpty()) {
            growingBlooms!!.add(cacheBlooms!!.pop())
        }
        var iterator = growingBlooms!!.iterator()
        treeSnapShot!!.canvas!!.save()
        treeSnapShot!!.canvas!!.translate(bloomDx!!, bloomDy!!)
        while (iterator.hasNext()) {
            var bloom = iterator.next()
            if (!bloom.grow(treeSnapShot!!.canvas!!)) {
                iterator.remove()
            }
        }
        treeSnapShot!!.canvas!!.restore()
        if (growingBranches!!.isEmpty() && cacheBlooms!!.isEmpty()) {
            step = Step.MOVING_SNAPSHOT
        }
    }

    private fun drawSnapshot(canvas: Canvas) {
        treeSnapShot!!.bitmap?.let { canvas.drawBitmap(it, 0f, 0f, snapShotPaint) }

    }

    private fun drawBranches() {
        if (!growingBranches!!.isEmpty()) {
            var temPBranches: LinkedList<Branch>? = LinkedList()
            treeSnapShot!!.canvas!!.save()
            treeSnapShot!!.canvas!!.translate(branchesDx!!, branchesDy!!)
            var iterator = growingBranches!!.iterator()
            while (iterator.hasNext()) {
                val branch = iterator.next()
                if (!branch.grow(treeSnapShot!!.canvas!!, BRANCHES_FACTOR * resolutionFactor!!)) {
                    iterator.remove()
                    if (branch.childList != null) {
                        if (temPBranches == null) {
                            temPBranches = branch.childList
                        } else {
                            temPBranches.addAll(branch.childList!!)
                        }
                    }
                }
            }
            treeSnapShot!!.canvas!!.restore()
            if (temPBranches != null) {
                growingBranches!!.addAll(temPBranches)
            }
        }
        if (growingBranches!!.isEmpty()) {
            //绘制树干完成
            step = Step.BLOOMS_GROWING
        }
    }


}