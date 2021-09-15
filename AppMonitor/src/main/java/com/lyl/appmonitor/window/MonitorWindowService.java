package com.lyl.appmonitor.window;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.lyl.appmonitor.R;

public class MonitorWindowService extends Service {

    private Boolean isStart;
    private final String UP_DATA = "UP_DATA";
    private FloatingWindowHelper mFloatingWindowHelper;
    private View view;
    private TextView fpsTextView;

    private BroadcastReceiver mLocalBroadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getIntExtra("code", -1)) {
                case 1:
                    fpsTextView.setText("fps:55");
                    break;
                default:
                    fpsTextView.setText("fps:0");
                    break;
            }
        }
    };

    public static void startService(Context context) {
        if (FloatingWindowHelper.canDrawOverlays(context, true)) {
            context.startService(new Intent(context, MonitorWindowService.class));
        }
    }


    public MonitorWindowService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        isStart = true;
        //注册监听本地广播
        LocalBroadcastManager
                .getInstance(this)
                .registerReceiver(mLocalBroadcastReceiver, new IntentFilter(UP_DATA));

        mFloatingWindowHelper = new FloatingWindowHelper(this);
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        view = layoutInflater.inflate(R.layout.layout_monitor_window, null, false);
        fpsTextView = view.findViewById(R.id.fps);

        if (FloatingWindowHelper.canDrawOverlays(this, true)) {
            mFloatingWindowHelper.addView(view);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}