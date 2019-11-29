package com.lyl.mvptest.mvp.secondfragment.eventbus

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lyl.mvptest.R
import kotlinx.android.synthetic.main.activity_event_bus2.*

class EventBusActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_bus2)

        btn.setOnClickListener {
            com.lyl.mvptest.mvp.secondfragment.eventbus.EventBus.getDefault.post("dabaicai")
        }
    }
}
