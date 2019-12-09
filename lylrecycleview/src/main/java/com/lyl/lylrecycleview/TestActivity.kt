package com.lyl.lylrecycleview

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_test.*
import java.util.*

class TestActivity : AppCompatActivity() {

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        var data = mutableListOf("aa")
        for (i in 0 until 20) {
            data.add(i, "$i---aaa")
        }

        var ll: LinearLayoutManager = LinearLayoutManager(this)
        ll.orientation = LinearLayout.VERTICAL
        recycle.layoutManager = ll
        var commonRecyclerAdapter = object : CommonRecyclerAdapter<String>(this@TestActivity, data, R.layout.view_item) {
            override fun convert(holder: ViewHolder?, item: String?) {
                holder!!.setText(R.id.tv, item)
            }

        }
        var wrapRecyclerAdapter = WrapRecyclerAdapter(commonRecyclerAdapter)
//        wrapRecyclerAdapter.addHeaderView(LayoutInflater.from(this).inflate(R.layout.head_layout, null, false))
//        wrapRecyclerAdapter.addHeaderView(LayoutInflater.from(this).inflate(R.layout.head_layout, null, false))
//        wrapRecyclerAdapter.addHeaderView(LayoutInflater.from(this).inflate(R.layout.head_layout, null, false))
        recycle.adapter = wrapRecyclerAdapter

//        recycle.addHeaderView(LayoutInflater.from(this).inflate(R.layout.head_layout, null, false))
//        recycle.addHeaderView(LayoutInflater.from(this).inflate(R.layout.head_layout, null, false))
//        recycle.addFooterView(LayoutInflater.from(this).inflate(R.layout.head_layout, null, false))
//        recycle.addFooterView(LayoutInflater.from(this).inflate(R.layout.head_layout, null, false))

        recycle.addRefreshViewCreator(DefaultRefreshCreator())
        recycle.addLoadViewCreator(DefaultLoadCreator())


        var b = Looper.getMainLooper() == Looper.myLooper()
        Log.d("lyll", Thread.currentThread().toString() + "              $b")
        Log.d("lyll", "--------------------------")
        var timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                this@TestActivity.runOnUiThread {
                    recycle.onStopRefresh()
                }

            }
        }, 10000)

    }
}

