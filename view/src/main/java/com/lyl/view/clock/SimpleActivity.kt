package com.lyl.view.clock

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lyl.view.R
import kotlinx.android.synthetic.main.activity_clock.*
import kotlin.concurrent.timer

class SimpleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clock)

        val mTimer = timer(period = 1000) {
            runOnUiThread {
                stage_textClock.doInvalidate()
            }
        }
    }
}