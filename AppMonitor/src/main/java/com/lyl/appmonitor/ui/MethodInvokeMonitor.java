package com.lyl.appmonitor.ui;

import android.os.Looper;
import android.util.Log;
import android.util.Printer;

import java.lang.reflect.Method;

/**
 * author : lyl
 * e-mail : laiyuling424@gmail.com
 * date   : 6/3/21 2:27 PM
 */
public class MethodInvokeMonitor implements Printer {

    private long time;

    private void startMonitor() {
        Looper looper = Looper.getMainLooper();
        looper.setMessageLogging(this);
    }

    @Override
    public void println(String x) {

        if (x.contains(">>>>> Dispatching to")) {
            time = System.currentTimeMillis();
        }

        if (x.contains("<<<<< Finished to")) {
            time = System.currentTimeMillis() - time;
        }

        if (time > 300) {
            //拿到当前执行的方法
            Log.e("lyll","当前方法执行超时");
        }
    }
}
