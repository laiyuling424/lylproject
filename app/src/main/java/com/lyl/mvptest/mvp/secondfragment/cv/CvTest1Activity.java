package com.lyl.mvptest.mvp.secondfragment.cv;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.lyl.mvptest.R;
import com.lyl.mvptest.config.Config;
import com.lyl.mvptest.utils.MyLog;

import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CvTest1Activity extends Activity {

    @BindView(R.id.image)
    ImageView image;

    @BindView(R.id.btn1)
    Button btn1;

    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cv_test1);

        ButterKnife.bind(this);

        staticLoadCVLibraries();

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.aa, options);

        if (bitmap == null) {
            MyLog.INSTANCE.Logd("lyll", "bitmap is null ");
            return;
        }

        image.setImageBitmap(bitmap);
    }

    @OnClick({R.id.btn1})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                Log.i("lyll", "image type:");
                Bitmap result = convertGray(bitmap);
                image.setImageBitmap(result);
                break;
        }
    }

    //OpenCV库静态加载并初始化
    private void staticLoadCVLibraries() {
        boolean load = OpenCVLoader.initDebug();
        if (load && Config.outputLog) {
            MyLog.INSTANCE.Logd("lyll", "Open CV Libraries loaded...");
        }
    }

    private Bitmap convertGray(Bitmap bitmap) {
        Mat src = new Mat();
        Mat temp = new Mat();
        Mat dst = new Mat();
        Utils.bitmapToMat(bitmap, src);
        Imgproc.cvtColor(src, temp, Imgproc.COLOR_BGRA2BGR);
        Log.i("lyll", "image type:" + (temp.type() == CvType.CV_8UC3));
        Imgproc.cvtColor(temp, dst, Imgproc.COLOR_BGR2GRAY);
        Utils.matToBitmap(dst, bitmap);
        return bitmap;
    }
}
