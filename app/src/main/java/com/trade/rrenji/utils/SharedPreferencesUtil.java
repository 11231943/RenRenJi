package com.trade.rrenji.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.trade.rrenji.MiGoApplication;

public class SharedPreferencesUtil {

    private static String FILE_COMMON = "login";

    private static String KEY_ID = "KEY_ID";

    private static String KEY_PHONE = "KEY_PHONE";

    private static String KEY_TOKEN = "token";

    private static String KEY_USERNAME = "KEY_USERNAME";

    private static String KEY_IMAGE = "KEY_IMAGE";

    private static String KEY_GENDER = "KEY_GENDER";

    private static String KEY_HX_ID = "KEY_HX_ID";

    private static String KEY_CHANNELID = "channelId";

    private static String KEY_LOGIN_TYPE = "login_type";

    private static String KEY_QQ_ID = "qqOpenId";

    private static String KEY_WECHAT_ID = "wechatOpenId";

    private static String KEY_SINA_ID = "sinaOpenId";

    private static String KEY_EDIT_INFO = "hasEditedInfo";

    private static String KEY_LAST_WELCOME_VERSION = "LAST_WELCOME_VERSION";

//    public static void clear(Context mContext) {
//        setUserImage("");
//        setID("");
//        setPhone("");
//        setUserToken("");
//        setUserName("");
//        setGender("");
//        setUserLoginType(-1);
//        setQqOpenId("");
//        setWechatOpenId("");
//        setSinaOpenId("");
//        setHxId("");
//        setParam(mContext, Constants.mBirthday, "");
//        setParam(mContext, Constants.mStature, "");
//        setParam(mContext, Constants.mWeight, "");
//        setParam(mContext, Constants.mProfession, "");
//        setParam(mContext, Constants.mIncome, "");
//        setParam(mContext, Constants.mHobby, "");
//        setParam(mContext, Constants.mConstellation, "");
//        setParam(mContext, Constants.mAttentionToCount, 0);
//        setParam(mContext, Constants.mAge, "");
//        setParam(mContext, Constants.attentionToMeCount, 0);
//        setParam(mContext, Constants.EASRMOB_ID, "");
//        setParam(mContext, Constants.KEY_HX_ID, "");
//    }

    public static void setHxId(String hxId) {
        setStringValue(KEY_HX_ID, hxId);
    }

    public static String getHxId() {
        return getStringValue(KEY_HX_ID, "");
    }

    public static void setUserImage(String token) {
        setStringValue(KEY_IMAGE, token);
    }

    public static String getUserImage() {
        return getStringValue(KEY_IMAGE, "");
    }

    public static void setGender(String gender) {
        setStringValue(KEY_GENDER, gender);
    }

    public static String getGender() {
        return getStringValue(KEY_GENDER, "");
    }

    public static void setUserName(String token) {
        setStringValue(KEY_USERNAME, token);
    }

    public static String getUserName() {
        return getStringValue(KEY_USERNAME, "");
    }

    public static void setID(String phone) {
        setStringValue(KEY_ID, phone);
    }

    public static String getID() {
        return getStringValue(KEY_ID, "");
    }

    public static void setChannelId(String channelId) {
        setStringValue(KEY_CHANNELID, channelId);
    }

    public static String getChannelId() {
        return getStringValue(KEY_CHANNELID, "");
    }

    public static void setPhone(String phone) {
        setStringValue(KEY_PHONE, phone);
    }

    public static String getPhone() {
        return getStringValue(KEY_PHONE, "");
    }

    public static void setUserToken(String value) {
        setStringValue(KEY_TOKEN, value);
    }

    public static String getUserToken() {
        return getStringValue(KEY_TOKEN, "");
    }

    public static void setUserLoginType(int value) {
        setIntValue(KEY_LOGIN_TYPE, value);
    }

    public static void setHasEditInfo(boolean hasEditInfo) {
        setBooleanValue(KEY_EDIT_INFO, hasEditInfo);
    }

    public static boolean hasEditinfo() {
        return getBooleanValue(KEY_EDIT_INFO);
    }

    public static int getUserLoginType(int defaultValue) {
        return getIntValue(KEY_LOGIN_TYPE, defaultValue);
    }

    public static void setQqOpenId(String qqOpenIdValue) {
        setStringValue(KEY_QQ_ID, qqOpenIdValue);
    }

    public static String getQqOpenId() {
        return getStringValue(KEY_QQ_ID, "");
    }

    public static void setSinaOpenId(String sinaOpenIdValue) {
        setStringValue(KEY_SINA_ID, sinaOpenIdValue);
    }

    public static String getSinaOpenId() {
        return getStringValue(KEY_SINA_ID, "");
    }

    public static void setWechatOpenId(String wechatOpenIdValue) {
        setStringValue(KEY_WECHAT_ID, wechatOpenIdValue);
    }

    public static String getWechatOpenId() {
        return getStringValue(KEY_WECHAT_ID, "");
    }

    public static void setLastWelcomeVersion(String value) {
        setStringValue(KEY_LAST_WELCOME_VERSION, value);
    }

    public static String getIsVersionFirstEnter() {
        return getStringValue(KEY_LAST_WELCOME_VERSION, "");
    }

    public static void setStringValue(String key, String val) {
        Context context = MiGoApplication.getApp();
        SharedPreferences preferences = context.getSharedPreferences(FILE_COMMON, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, val);
        editor.commit();
    }

    public static String getStringValue(String key, String val) {
        Context context = MiGoApplication.getApp();
        SharedPreferences preferences = context.getSharedPreferences(FILE_COMMON, Context.MODE_PRIVATE);
        return preferences.getString(key, val);
    }

    public static void setBooleanValue(String key, boolean value) {
        Context context = MiGoApplication.getApp();
        SharedPreferences preferences = context.getSharedPreferences(FILE_COMMON, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static boolean getBooleanValue(String key) {
        Context context = MiGoApplication.getApp();
        SharedPreferences preferences = context.getSharedPreferences(FILE_COMMON, Context.MODE_PRIVATE);
        return preferences.getBoolean(key, false);
    }

    public static void setLongValue(String key, Long value) {
        Context context = MiGoApplication.getApp();
        SharedPreferences preferences = context.getSharedPreferences(FILE_COMMON, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public static Long getLongValue(String key, Long defaultValue) {
        Context context = MiGoApplication.getApp();
        SharedPreferences preferences = context.getSharedPreferences(FILE_COMMON, Context.MODE_PRIVATE);
        return preferences.getLong(key, defaultValue);
    }

    public static void setIntValue(String key, int value) {
        Context context = MiGoApplication.getApp();
        SharedPreferences preferences = context.getSharedPreferences(FILE_COMMON, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static int getIntValue(String key, int defaultValue) {
        Context context = MiGoApplication.getApp();
        SharedPreferences preferences = context.getSharedPreferences(FILE_COMMON, Context.MODE_PRIVATE);
        return preferences.getInt(key, defaultValue);
    }

    public static boolean isLogined() {
        return !getID().isEmpty();
    }

    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     *
     * @param context
     * @param key
     * @param object
     */
    public static void setParam(Context context, String key, Object object) {

        String type = object.getClass().getSimpleName();
        SharedPreferences sp = context.getSharedPreferences(FILE_COMMON, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        if ("String".equals(type)) {
            editor.putString(key, (String) object);
        } else if ("Integer".equals(type)) {
            editor.putInt(key, (Integer) object);
        } else if ("Boolean".equals(type)) {
            editor.putBoolean(key, (Boolean) object);
        } else if ("Float".equals(type)) {
            editor.putFloat(key, (Float) object);
        } else if ("Long".equals(type)) {
            editor.putLong(key, (Long) object);
        }

        editor.commit();
    }


    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     *
     * @param context
     * @param key
     * @param defaultObject
     * @return
     */
    public static Object getParam(Context context, String key, Object defaultObject) {
        String type = defaultObject.getClass().getSimpleName();
        SharedPreferences sp = context.getSharedPreferences(FILE_COMMON, Context.MODE_PRIVATE);

        if ("String".equals(type)) {
            return sp.getString(key, (String) defaultObject);
        } else if ("Integer".equals(type)) {
            return sp.getInt(key, (Integer) defaultObject);
        } else if ("Boolean".equals(type)) {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if ("Float".equals(type)) {
            return sp.getFloat(key, (Float) defaultObject);
        } else if ("Long".equals(type)) {
            return sp.getLong(key, (Long) defaultObject);
        }

        return null;
    }

    public static void loginOut(Context mContext) {
//        EMClient.getInstance().logout(true);
        //友盟的退出登陆
//        MobclickAgent.onProfileSignOff();
//        clear(mContext);
    }
}
