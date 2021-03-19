package com.lyl.secondtheory.eventbus.simple

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.lyl.secondtheory.R
import com.lyl.secondtheory.eventbus.EventBus

class EventBusActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_bus2)

        findViewById<Button>(R.id.btn).setOnClickListener {
            EventBus.getDefault.post("dabaicai")
        }
    }
}
