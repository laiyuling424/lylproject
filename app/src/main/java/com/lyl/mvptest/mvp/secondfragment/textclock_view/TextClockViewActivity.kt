package com.lyl.mvptest.mvp.secondfragment.textclock_view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lyl.mvptest.R
import kotlinx.android.synthetic.main.activity_text_clock_view.*
import java.util.*
import kotlin.concurrent.timer

class TextClockViewActivity : AppCompatActivity() {

    private var mTimer: Timer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_clock_view)

        mTimer = timer(period = 1000) {
            runOnUiThread {
                stage_textClock.doInvalidate()
            }
        }
    }
}
