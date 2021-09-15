package com.lyl.appmonitor.ui;


import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

/**
 * author : lyl
 * e-mail : laiyuling424@gmail.com
 * date   : 6/4/21 9:05 AM
 */
class KadunMonitorThread extends Thread {

    private Handler mainHandler;
    private final int DELAY_TIME = 3 * 1000;
    private final int MESSAGE_DELAY_WHAT = 10086;

    public KadunMonitorThread() {
        super("KadunMonitorThread");
        mainHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case MESSAGE_DELAY_WHAT:
                        Log.e("lyll", "发生卡顿");
                        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
                        for (StackTraceElement stackTraceElement : stackTrace) {
                            Log.e("lyll", stackTraceElement.toString());
                        }
                        break;
                    case 111:
                        try {
                            sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                }
            }
        };
    }

    @Override
    public void run() {
        while (true) {
            mainHandler.sendEmptyMessageDelayed(MESSAGE_DELAY_WHAT, DELAY_TIME);
            mainHandler.sendEmptyMessage(111);
            try {
                sleep(DELAY_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mainHandler.removeMessages(MESSAGE_DELAY_WHAT);
        }
    }
}
