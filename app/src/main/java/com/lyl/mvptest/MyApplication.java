package com.lyl.mvptest;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
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

        if (true) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }
}
