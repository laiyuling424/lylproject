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
        canvas!!.drawColor(0xffffee)
        canvas.save()
        canvas.translate(snapShotDx!! + xoffset!!,0f)
        when(step){
            Step.BRANCHES_GROWING->{
                drawBranches()
                drawSnapshot(canvas)
            }
            Step.BLOOMS_GROWING->{

            }
            Step.MOVING_SNAPSHOT->{

            }
            Step.BLOOMS_FALLING->{

            }
        }
        canvas.restore()
    }

    private fun drawSnapshot(canvas: Canvas) {
        canvas.drawBitmap(treeSnapShot!!.bitmap,0f,0f, snapShotPaint)

    }

    private fun drawBranches() {
        if (!growingBranches!!.isEmpty()){
            var temPBranches:LinkedList<Branch>?= LinkedList()
            treeSnapShot!!.canvas!!.save()
            treeSnapShot!!.canvas!!.translate(branchesDx!!, branchesDy!!)
            var iterator= growingBranches!!.iterator()
            while (iterator.hasNext()){
                val branch = iterator.next()
                if (!branch.grow(treeSnapShot!!.canvas!!, BRANCHES_FACTOR* resolutionFactor!!)){
                    iterator.remove()
                    if (branch.childList!=null){
                        if (temPBranches==null){
                            temPBranches=branch.childList
                        }else{
                            temPBranches.addAll(branch.childList!!)
                        }
                    }
                }
            }
            treeSnapShot!!.canvas!!.restore()
            if (temPBranches!=null){
                growingBranches!!.addAll(temPBranches)
            }
        }
        if (growingBranches!!.isEmpty()){
            //绘制树干完成
            step=Step.BLOOMS_GROWING
        }
    }


}