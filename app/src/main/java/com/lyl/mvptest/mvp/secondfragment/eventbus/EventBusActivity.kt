package com.lyl.mvptest.mvp.secondfragment.eventbus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lyl.mvptest.R
import com.lyl.mvptest.utils.MyLog
import kotlinx.android.synthetic.main.activity_event_bus.*

class EventBusActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_bus)

        com.lyl.mvptest.mvp.secondfragment.eventbus.EventBus.getDefault.registered(this)

        btn.setOnClickListener { startActivity(Intent(this, EventBusActivity2::class.java)) }
    }

    @Subscrible(threadMode = ThreadMode.Main)
    fun a(aa: String) {
        MyLog.Logd("lyll", "aa===>$aa")
    }
}
