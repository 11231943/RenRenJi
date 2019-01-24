package com.trade.rrenji.net;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.trade.rrenji.BuildConfig;
import com.trade.rrenji.utils.ConfigUtils;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pck on 17-10-12.
 */

public class ServiceHelper {

    public static final String TAG = ServiceHelper.class.getSimpleName();

    /**
     * 设置当前连接的服务器环境；
     *
     * @param key 服务器连接地址
     * @return url host 路径
     */
    public static String buildUrl(String key) {
        return buildUrl(key, false);
    }

    public static String buildUrl(String key, boolean https) {
        String hostKey;
        switch (BuildConfig.API_TYPE) {
            case "release":
                hostKey = ConfigUtils.getInstance().getProperty("api.v2.url.release");
                break;
            case "beta":
                hostKey = ConfigUtils.getInstance().getProperty("api.v1.url.slave");
                break;
            case "debug":
                hostKey = ConfigUtils.getInstance().getProperty("api.v2.url.debug");
                break;
            default:
                hostKey = null;
                break;
        }
//        String host = ConfigUtils.getInstance().getProperty(hostKey + (https ? ".https" : ""));
        if (hostKey == null) {
            Log.e(TAG, "Can't find host. BuildConfig.API_TYPE: " + BuildConfig.API_TYPE);
            return "";
        }

        String path = ConfigUtils.getInstance().getProperty(key);
        if (path == null) {
            Log.e(TAG, "Can't find url with key " + key);
            return "";
        }
        return hostKey + path;
    }

    /**
     * 请求参数；
     */
    public static class ParamBuilder {
        Context mContext;
        Map<String, String> mParams;
        boolean mNeedToken = true;

        private static String mUUID;
        private static String mChannel;
        private static String mApkVersion;
        private static String mDevType = "0";

        public ParamBuilder(Context context) {
            mContext = context;
            mParams = new HashMap<>();
        }

        public ParamBuilder add(String key, String value) {
            if (TextUtils.isEmpty(value)) {
                value = "";
            }
            mParams.put(key, value);
            return this;
        }

        public ParamBuilder add(String key, char value) {
            mParams.put(key, String.valueOf(value));
            return this;
        }

        public ParamBuilder add(String key, int value) {
            mParams.put(key, String.valueOf(value));
            return this;
        }

        public ParamBuilder add(String key, long value) {
            mParams.put(key, String.valueOf(value));
            return this;
        }

        public ParamBuilder add(String key, float value) {
            mParams.put(key, String.valueOf(value));
            return this;
        }

        public ParamBuilder add(String key, double value) {
            mParams.put(key, String.valueOf(value));
            return this;
        }

        public ParamBuilder addAll(Map<String, String> params) {
            if (params != null) {
                mParams.putAll(params);
            }
            return this;
        }

        public Map<String, String> build() {
            mParams.put("lon", "");
            mParams.put("lat", "");
            mParams.put("devType", "0");//0Android  1IOS
            String sign = getSign();
            mParams.put("sign", sign);
            return mParams;
        }

        public String getSign() {
            try {
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                for (Map.Entry<String, String> entry : mParams.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    nameValuePairs.add(new NameValuePair(key, value));
                }
                return Md5SecurityUtil.getSignatureValue(nameValuePairs);
            } catch (Exception e) {
                e.printStackTrace();
                return "getLocalSignFailed";
            }
        }
    }
}
