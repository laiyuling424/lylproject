package com.lyl.view.loadview.view2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lyl.view.R
import kotlinx.android.synthetic.main.activity_simple.*

class SimpleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple)


        loadingview.startAllAnimator()
    }
}