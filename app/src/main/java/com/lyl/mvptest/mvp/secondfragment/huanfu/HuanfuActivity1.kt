package com.lyl.mvptest.mvp.secondfragment.huanfu

import android.content.Intent
import android.os.Bundle
import com.lyl.mvptest.R
import kotlinx.android.synthetic.main.activity_huanfu1.*

class HuanfuActivity1 : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_huanfu1)

        jump.setOnClickListener {
            startActivity(Intent(this@HuanfuActivity1, HuanfuActivity2::class.java))
        }

    }


}
