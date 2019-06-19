package com.lyl.mvptest.mvp.recycleview

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lyl.mvptest.R
import java.util.ArrayList

class RecycleViewActivity : AppCompatActivity() {

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycle_view)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val recyclerAdapter = RecyclerAdapter(generateData())
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val itemTouchHelper = ItemTouchHelper(GamItemTouchCallback(recyclerAdapter, dpToPx(this, 120f)))
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun generateData(): List<String> {
        val dataList = ArrayList<String>()
        for (i in 0..99) {
            dataList.add("position = $i")
        }
        return dataList
    }

    fun dpToPx(context: Context, value: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, context.resources.displayMetrics).toInt()
    }
}
