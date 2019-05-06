package com.lyl.mvptest.animation;

import android.app.Activity;
import android.os.Bundle;

import com.lyl.mvptest.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnimationActivity extends Activity {

    @BindView(R.id.page)
    PageView pageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn})
    public void click(){
        pageView.startAnimation();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
