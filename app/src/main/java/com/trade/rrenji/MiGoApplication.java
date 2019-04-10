package com.trade.rrenji;

import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.Bugly;
import com.trade.rrenji.utils.ConfigUtils;
import com.trade.rrenji.utils.GsonUtils;
import com.trade.rrenji.utils.SettingUtils;
import com.trade.rrenji.utils.reservoir.Reservoir;

import org.xutils.x;

import java.io.File;

import butterknife.ButterKnife;

/**
 * Created by monster on 8/4/18.
 */

public class MiGoApplication extends MultiDexApplication {

    private static MiGoApplication app;//app对象

    public static String mHostKey;

    public static MiGoApplication getApp() {
        return app;
    }

    public static String CACHE_PATH = "/sdcard/renrenji/image/temp/";

    private boolean isExiting;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        x.Ext.init(this);
        x.Ext.setDebug(true);
        ConfigUtils.getInstance().init(this, R.raw.app_config);
        SettingUtils.getInstance().init(this);
        File file = new File(CACHE_PATH);
        if (!file.exists()) {
            file.mkdirs();
        }
        LeakCanary.install(this);
        try {
            Reservoir.init(this, 1024 * 1000 * 5, GsonUtils.getGson()); //in bytes
        } catch (Exception e) {
            Log.e("init reservoir FAILED", e.getMessage());
        }
    }

    /**
     * 初始化bugly
     */
    public static void initBugly() {
        /**
         第三个参数为SDK调试模式开关，调试模式的行为特性如下：
         1.输出详细的Bugly SDK的Log；
         2.每一条Crash都会被立即上报；
         3.自定义日志将会在Logcat中输出。
         建议在测试阶段建议设置成true，发布时设置为false
         */
        if(BuildConfig.API_TYPE.equals("release")){
            Bugly.init(getApp(), "b7d70aae45", false);
        }else {
            Bugly.init(getApp(), "b7d70aae45", true);
        }
        //CrashReport.initCrashReport(mContext.getApplicationContext(), "b7d70aae45", false);
    }
    public void exit(boolean killApplication, boolean killNotification) {
        isExiting = true;
        NotificationManager nm = (NotificationManager) getSystemService(
                Context.NOTIFICATION_SERVICE);
        nm.cancelAll();

        if (killApplication) {
            System.exit(-1);
        }
    }
}
