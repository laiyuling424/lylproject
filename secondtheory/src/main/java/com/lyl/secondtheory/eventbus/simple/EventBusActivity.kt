package com.lyl.secondtheory.eventbus.simple

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.lyl.secondtheory.R
import com.lyl.secondtheory.eventbus.EventBus
import com.lyl.secondtheory.eventbus.Subscrible
import com.lyl.secondtheory.eventbus.ThreadMode

class EventBusActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_bus)

        EventBus.getDefault.registered(this)

        findViewById<Button>(R.id.btn).setOnClickListener { startActivity(Intent(this, EventBusActivity2::class.java)) }
    }

    @Subscrible(threadMode = ThreadMode.Main)
    fun a(aa: String) {
        Log.d("lyll", "aa===>$aa")
    }
}
