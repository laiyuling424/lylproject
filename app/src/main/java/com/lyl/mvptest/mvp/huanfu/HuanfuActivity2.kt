package com.lyl.mvptest.mvp.huanfu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import com.lyl.mvptest.R
import kotlinx.android.synthetic.main.activity_huanfu2.*

class HuanfuActivity2 : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_huanfu2)

        huanfu.setOnClickListener {
            var path=Environment.getExternalStorageDirectory().path+"/skin.apk"
            Log.d("lyll","path=$path")
            SkinManager.getSkinManager().loadSkinApk(Environment.getExternalStorageDirectory().path+"/skin.apk")
            apply()
        }

        jump2.setOnClickListener {
            startActivity(Intent(this@HuanfuActivity2,HuanfuActivity3::class.java))
        }
    }
}
