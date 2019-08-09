package com.lyl.mvptest.mvp.secondfragment.loading

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lyl.mvptest.R
import kotlinx.android.synthetic.main.activity_loading.*

class LoadingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

        loadingview.startAllAnimator()
    }
}
