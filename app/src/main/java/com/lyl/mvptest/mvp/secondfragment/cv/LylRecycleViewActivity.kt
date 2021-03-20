package com.lyl.mvptest.mvp.secondfragment.cv


import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.lyl.lylrecycleview.ViewHolder
import com.lyl.lylrecycleview.adapter.CommonRecyclerAdapter
import com.lyl.lylrecycleview.adapter.WrapRecyclerAdapter
import com.lyl.lylrecycleview.creator.DefaultLoadCreator
import com.lyl.lylrecycleview.creator.DefaultRefreshCreator
import com.lyl.mvptest.R
import kotlinx.android.synthetic.main.activity_cv.*
import java.util.*

class LylRecycleViewActivity : Activity() {

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cv)


        var data = mutableListOf("aa")
        for (i in 0 until 20) {
            data.add(i, "$i---aaa")
        }

        var ll = LinearLayoutManager(this)
        ll.orientation = LinearLayout.VERTICAL
        recycle.layoutManager = ll
        var commonRecyclerAdapter = object : CommonRecyclerAdapter<String>(this@LylRecycleViewActivity, data, com.lyl.lylrecycleview.R.layout.view_item) {
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
