package com.lyl.view.textcolor

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.lyl.view.R
import kotlinx.android.synthetic.main.activity_text_color.*

class SimpleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_color)
    }
    // onClick事件写在了不居中  --> android:onClick="left"
    fun left(view: View) {
        // 设置朝向
        mCttv.setDirection(ColorTrackTextView.Direction.DIRECTION_LEFT)
        // 用属性动画来控制，当然也可以用线程去控制
        val animator = ObjectAnimator.ofFloat(mCttv, "progress", 0f, 1f)
        animator.setDuration(2000)
                .start()
        // 添加动画的监听，不断的改变当前的进度
//        animator.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener() {
//            fun onAnimationUpdate(animation: ValueAnimator) {
//                val progress = animation.getAnimatedValue() as Float
//                Log.e("lyll", "progress --> $progress")
//                mCttv.setCurrentProgress(progress)
//            }
//        })
        animator.addUpdateListener {
            val progress = it.animatedValue as Float
            Log.e("lyll", "progress --> $progress")
            mCttv.setCurrentProgress(progress)
        }
    }

    // 这与上面类似，只是朝向不一样
    fun right(view: View) {
        mCttv.setDirection(ColorTrackTextView.Direction.DIRECTION_RIGHT)
        val animator = ObjectAnimator.ofFloat(mCttv, "progress", 0f, 1f)
        animator.setDuration(2000)
                .start()
        animator.addUpdateListener { animation ->
            val progress = animation.animatedValue as Float
            Log.e("lyll", "progress --> $progress")
            mCttv.setCurrentProgress(progress)
        }
    }
}
