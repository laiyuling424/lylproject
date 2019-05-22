package com.lyl.mvptest.mvp.cv;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lyl.mvptest.R;

import java.nio.Buffer;

public class CvActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cv);

        ButterKnife.bind(this);
    }

    @OnClick({R.id.cv1, R.id.cv1kt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cv1:
                startActivity(new Intent(CvActivity.this, CvTest1Activity.class));
                break;
            case R.id.cv1kt:
                startActivity(new Intent(CvActivity.this, CvTest2Activity.class));
                break;
        }
    }
}
