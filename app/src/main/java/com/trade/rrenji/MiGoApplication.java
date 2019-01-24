package com.trade.rrenji;

import android.app.Application;
import android.support.multidex.MultiDexApplication;

import com.trade.rrenji.utils.ConfigUtils;

import org.xutils.x;

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

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        x.Ext.init(this);
        x.Ext.setDebug(true);
        ConfigUtils.getInstance().init(this, R.raw.app_config);
    }
}
