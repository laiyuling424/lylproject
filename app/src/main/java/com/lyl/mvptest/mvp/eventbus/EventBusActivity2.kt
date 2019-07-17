package com.lyl.mvptest.mvp.eventbus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lyl.mvptest.R
import kotlinx.android.synthetic.main.activity_event_bus2.*

class EventBusActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_bus2)

        btn.setOnClickListener {
            EventBus.getDefault.post("dabaicai")
        }
    }
}
