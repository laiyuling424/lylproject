package com.lyl.mvptest.mvp.secondfragment.live_data_bus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.lyl.mvptest.R

class LiveEventBusActivity1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_event_bus1)
        com.lyl.mvptest.mvp.secondfragment.live_data_bus.LiveDataBus.getInstance().with("dabaicai", Dabaicai::class.java).observe(
                this@LiveEventBusActivity1, Observer<Dabaicai> {
            Toast.makeText(this@LiveEventBusActivity1, it.name+" "+it.age, Toast.LENGTH_SHORT).show()
        }
        )
    }

    fun jump(view: View) {
        startActivity(Intent(this, LiveEventBusActivity2::class.java))
    }


    fun send(view: View) {
        val dabaicai=Dabaicai()
        dabaicai.name="dabaicai"
        dabaicai.age="23"

        com.lyl.mvptest.mvp.secondfragment.live_data_bus.LiveDataBus.getInstance().with("dabaicai",Dabaicai::class.java).postValue(dabaicai)
    }
}
