package com.lyl.mvptest.mvp.huanfu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.LayoutInflaterCompat
import com.lyl.mvptest.R
import kotlinx.android.synthetic.main.activity_huanfu1.*

class HuanfuActivity1 : BaseActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_huanfu1)

        jump.setOnClickListener {
            startActivity(Intent(this@HuanfuActivity1,HuanfuActivity2::class.java))
        }

    }


}
