package com.lyl.view.peiqi

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.lyl.view.R
import kotlinx.android.synthetic.main.activity_pei_qi_test.*

class PeiQiTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pei_qi_test)
    }

    fun start(view: View) {
        pageView.startAnimation()
    }
}