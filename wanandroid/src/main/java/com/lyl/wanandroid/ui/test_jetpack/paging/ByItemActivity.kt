package com.lyl.wanandroid.ui.test_jetpack.paging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.lyl.wanandroid.R
import kotlinx.android.synthetic.main.activity_by_item.*

class ByItemActivity : AppCompatActivity() ,LifecycleOwner{

    private lateinit var mByItemViewModel: ByItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_by_item)
        mByItemViewModel = ViewModelProviders.of(this).get(ByItemViewModel::class.java)

        val adapter = ByItemAdapter()
        rv.adapter = adapter
        mByItemViewModel.accounts.observe(this, Observer(adapter::submitList))

    }
}
