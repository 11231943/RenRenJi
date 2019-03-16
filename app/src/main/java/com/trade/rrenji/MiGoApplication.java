package com.trade.rrenji;

import android.app.Application;
import android.support.multidex.MultiDexApplication;

import com.trade.rrenji.utils.ConfigUtils;
import com.trade.rrenji.utils.SettingUtils;

import org.xutils.x;

import java.io.File;

import butterknife.ButterKnife;

/**
 * Created by monster on 8/4/18.
 */

public class MiGoApplication extends MultiDexApplication {

    private static MiGoApplication app;//app对象

    public static String mHostKey;

    public static Application getApp() {
        return app;
    }

    public static String CACHE_PATH = "/sdcard/renrenji/image/temp/";


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
    }
}
