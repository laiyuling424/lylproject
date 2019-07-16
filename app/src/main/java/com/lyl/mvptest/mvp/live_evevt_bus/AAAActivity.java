package com.lyl.mvptest.mvp.live_evevt_bus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.lyl.mvptest.R;

public class AAAActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aaa);
        LiveDataBus.getInstance().with("dabaicai",Dabaicai.class).observe(
                this, new Observer<Dabaicai>() {
                    @Override
                    public void onChanged(Dabaicai dabaicai) {

                    }
                }
        );
    }


    public void jump(View view) {
    }

    public void send(View view) {
        LiveDataBus.getInstance().with("dabaicai",String.class).postValue("xihuan");
    }
}
