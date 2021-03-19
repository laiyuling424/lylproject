package com.lyl.view.loadview.view1

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.lyl.view.R
import kotlinx.android.synthetic.main.activity_load.*

class LoadActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load)
    }

    fun up(view: View) {
        loadview.up()
    }

    fun fall(view: View) {
        loadview.fall()
    }

    fun start(view: View) {
        loadview.start()
    }

    fun stop(view: View) {
        loadview.stop()
    }
}
