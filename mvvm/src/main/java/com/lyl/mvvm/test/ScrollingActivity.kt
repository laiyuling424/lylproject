package com.lyl.mvvm.test

import android.os.Bundle
import androidx.lifecycle.Observer
import com.lyl.mvvm.core.BaseViewModelActivity

class ScrollingActivity : BaseViewModelActivity<ScrollingViewModel>() {

    override fun providerVMClass(): Class<ScrollingViewModel> = ScrollingViewModel::class.java

    private val TAG = this.javaClass.simpleName

    private val datas = mutableListOf<Data>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val loadDatas = viewModel.loadDatas()
        viewModel.getActicle().observe(this, Observer {
            //获取到数据
            it?.run {

            }
        })
    }


}
