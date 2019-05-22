package com.lyl.mvptest;

import android.app.Application;

import com.lyl.mvptest.config.Config;
import com.lyl.mvptest.utils.CatchHandler;
/**
 * create 2018/8/24
 * author lyl
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        if (Config.catchException) CatchHandler.getInstance().init(this);
    }
}
