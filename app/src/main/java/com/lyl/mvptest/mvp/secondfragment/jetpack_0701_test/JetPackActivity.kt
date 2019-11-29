package com.lyl.mvptest.mvp.secondfragment.jetpack_0701_test

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.lyl.mvptest.R
import kotlinx.android.synthetic.main.activity_jet_pack.*

class JetPackActivity : AppCompatActivity() {

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jet_pack)

        var viewModel: ProvinceViewModel = ViewModelProviders.of(this)[ProvinceViewModel::class.java]
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayout.VERTICAL
        recyclerVieww.layoutManager = linearLayoutManager
        var adapter = ProvinceAdapter()
        recyclerVieww.adapter = adapter
        viewModel.accounts.observe(this, Observer(adapter::submitList))
    }
}
