package com.lyl.mvptest.mvp.cv

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.lyl.mvptest.R
import com.lyl.mvptest.config.Config
import com.lyl.mvptest.utils.MyLog
import org.opencv.android.OpenCVLoader
import org.opencv.android.Utils
import org.opencv.core.Mat
import org.opencv.imgproc.Imgproc

class CvTest2Activity : AppCompatActivity() {

    internal var bitmap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cv_test2)

        initOpenCV()

        val btn1 = findViewById<Button>(R.id.btn1)
        val image = findViewById<ImageView>(R.id.image)

        val option = BitmapFactory.Options()
        option.inPreferredConfig = Bitmap.Config.ARGB_8888

        bitmap = BitmapFactory.decodeResource(this.resources, R.mipmap.aa, option)

        image!!.setImageBitmap(bitmap)

        btn1.setOnClickListener {
            val bitmapCopy = bitmap
            val result: Bitmap? = convertGray(bitmapCopy)
            image!!.setImageBitmap(result)
        }
    }


    //OpenCV库静态加载并初始化
    private fun initOpenCV() {
        val load = OpenCVLoader.initDebug()
        if (load && Config.outputLog) MyLog.Logi("lyll", "Open CV Libraries loaded...")
    }

    private fun convertGray(bitmap: Bitmap?): Bitmap? {
//        var result: Bitmap? = null
        var src = Mat()
        var dst = Mat()
        var temp = Mat()
        Utils.bitmapToMat(bitmap, src)
        Imgproc.cvtColor(src, dst, Imgproc.COLOR_BGRA2BGR)
        Imgproc.cvtColor(dst, temp, Imgproc.COLOR_BGR2GRAY)
        Utils.matToBitmap(temp, bitmap)
        return bitmap
    }
}
