package com.lyl.lylrecycleview.simple

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lyl.lylrecycleview.R
import com.lyl.lylrecycleview.ViewHolder
import com.lyl.lylrecycleview.adapter.CommonRecyclerAdapter
import com.lyl.lylrecycleview.adapter.WrapRecyclerAdapter
import com.lyl.lylrecycleview.creator.DefaultLoadCreator
import com.lyl.lylrecycleview.creator.DefaultRefreshCreator
import kotlinx.android.synthetic.main.activity_recycle_view.*
import java.util.*

class LylRecycleViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycle_view)

        var data = mutableListOf("aa")
        for (i in 0 until 20) {
            data.add(i, "$i---aaa")
        }

        var ll = LinearLayoutManager(this)
        ll.orientation = RecyclerView.VERTICAL
        recycle.layoutManager = ll
        var commonRecyclerAdapter = object : CommonRecyclerAdapter<String>(this@LylRecycleViewActivity, data, R.layout.view_item) {
            override fun convert(holder: ViewHolder?, item: String?) {
                holder!!.setText(com.lyl.lylrecycleview.R.id.tv, item)
            }

        }
        var wrapRecyclerAdapter = WrapRecyclerAdapter(commonRecyclerAdapter)
        recycle.adapter = wrapRecyclerAdapter


        recycle.addRefreshViewCreator(DefaultRefreshCreator())
        recycle.addLoadViewCreator(DefaultLoadCreator())


        var b = Looper.getMainLooper() == Looper.myLooper()
        Log.d("lyll", Thread.currentThread().toString() + "              $b")
        Log.d("lyll", "--------------------------")
        var timer = Timer()

        recycle.setOnLoadMoreListener {
            timer.schedule(object : TimerTask() {
                override fun run() {
                    this@LylRecycleViewActivity.runOnUiThread {
                        recycle.onStopLoad()
                    }

                }
            }, 3000)
        }

        recycle.setOnRefreshListener {
            timer.schedule(object : TimerTask() {
                override fun run() {
                    this@LylRecycleViewActivity.runOnUiThread {
                        recycle.onStopRefresh()
                    }

                }
            }, 3000)
        }

    }

}
