package com.lyl.core.framework.plugin;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.lyl.core.R;

public class NoRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_register);
        Log.d("lyll", "this is target activity");
    }
}
