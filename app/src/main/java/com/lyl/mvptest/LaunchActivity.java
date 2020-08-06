package com.lyl.mvptest;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.lyl.mvptest.aboutc.JniClass;
import com.lyl.mvptest.aboutc.VaccaeSurfaceView;
import com.permissionx.guolindev.PermissionX;
import com.permissionx.guolindev.callback.RequestCallback;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class LaunchActivity extends AppCompatActivity {

    private File detectionFaceFile;


    private VaccaeSurfaceView surfaceView;
    private RelativeLayout surfaceviewlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        requestPermission();
        copyCascadeFile();
        JniClass.loadcascade(detectionFaceFile.getAbsolutePath());

        surfaceviewlayout = findViewById(R.id.surfaceviewlayout);
    }


    public void start(View view) {
        Log.d("lyll", "start click");

        surfaceView = new VaccaeSurfaceView(LaunchActivity.this);
        surfaceviewlayout.addView(surfaceView);
    }

    private void requestPermission() {
        PermissionX.init(LaunchActivity.this)
                .permissions(Manifest.permission.READ_CONTACTS, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .request(new RequestCallback() {
                    @Override
                    public void onResult(boolean allGranted, List<String> grantedList, List<String> deniedList) {
                        if (allGranted) {
                            Toast.makeText(LaunchActivity.this, "All permissions are granted", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(LaunchActivity.this, "These permissions are denied: $deniedList", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void toMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }

    private void copyCascadeFile() {
        try {
            InputStream is = getResources().openRawResource(R.raw.haarcascade_frontalface_default);
            File cascadeDir = getDir("cascade", Context.MODE_PRIVATE);
            detectionFaceFile = new File(cascadeDir, "haarcascade_frontalface_default.xml");
            if (detectionFaceFile.exists()) {
                return;
            }
            FileOutputStream os = new FileOutputStream(detectionFaceFile);

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            is.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}