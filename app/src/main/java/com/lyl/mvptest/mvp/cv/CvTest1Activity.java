package com.lyl.mvptest.mvp.cv;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.lyl.mvptest.R;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;


public class CvTest1Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cv_test1);

        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(),R.mipmap.aa);

        Drawable drawable=getDrawable(R.mipmap.aa);
        BitmapDrawable bitmapDrawable=(BitmapDrawable) drawable;
        Bitmap bitmap2=bitmapDrawable.getBitmap();

        Mat mat=new Mat(bitmap1.getWidth(),bitmap1.getHeight(), CvType.CV_8UC3);
    }
}
