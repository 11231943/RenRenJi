package com.trade.rrenji.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.trade.rrenji.bean.account.LoginJsonBean;

public class SettingUtils {
    /**
     * "baichuanPassWord": "renrenji123",
     * "userImg": "",
     * "weChatAuthState": 0,
     * "sessionKey": "11cdf167edab48e9a03351cc1fe16bcc",
     * "phone": "17603009825",
     * "baichuanAccount": "13332959300@139.com",
     * "userName": "雷锋",
     * "message": "已加入人人机5天",
     * "userId": "455",
     * "aliPayAuthState": 0
     */
    public static final String TAG = "SettingUtils";
    public static final String BAICHUAN_PASSWORD = "baichuanPassWord";
    public static final String USER_IMG = "userImg";
    public static final String SESSIONKEY = "sessionKey";
    public static final String PHONE = "phone";
    public static final String BAICHUAN_ACCOUNT = "baichuanAccount";
    public static final String USERNAME = "userName";
    public static final String MESSAGE = "message";
    public static final String KEY_USER_ID = "userId";
    public static final String ALIPAY_AUTH_STATE = "aliPayAuthState";
    public static final String WECHAT_AUTH_STATE = "weChatAuthState";

    public static final String KEY_CURRENT_UID = "current_uid";
    public static final String KEY_CURRENT_SEX = "current_sex";
    public static final String KEY_CURRENT_ADDRESS= "current_address";
    public static final String NAME_USER_SETTING_PREFIX = "cookie_";
    public static final String NAME_APP_SETTING = "cookie";

    protected Context mContext;
    /**
     * 用户级别的偏好设置。
     * 不要直接使用此字段，需要用时使用{@link # }
     */
    protected SharedPreferences mUserSetting;

    /**
     * app级别的偏好设置。
     */
    protected SharedPreferences mAppSetting;


    private static SettingUtils sInstance;

    public SettingUtils() {

    }

    public synchronized static SettingUtils getInstance() {
        if (sInstance == null) {
            sInstance = new SettingUtils();
        }
        return sInstance;
    }

    public void init(Application context) {
        mContext = context;
        mAppSetting = mContext.getSharedPreferences(NAME_APP_SETTING, Context.MODE_PRIVATE);
    }

    public SharedPreferences getCurrentUserSetting() {
        synchronized (KEY_CURRENT_UID) {
            if (mUserSetting == null ||
                    !TextUtils.equals(getCurrentUid(), mUserSetting.getString(KEY_USER_ID, ""))) {
                String name = NAME_USER_SETTING_PREFIX + getCurrentUid();
                mUserSetting = mContext.getSharedPreferences(name, Context.MODE_PRIVATE);
            }
        }
        return mUserSetting;
    }

    public String getCurrentUid() {
        if (mAppSetting != null) {
            return mAppSetting.getString(KEY_CURRENT_UID, "0");
        } else {
            return "0";
        }
    }

    public void setCurrentUid(String uid) {
        synchronized (KEY_CURRENT_UID) {
            mAppSetting.edit().putString(KEY_CURRENT_UID, uid).apply();
        }
    }

    public void setBaichuanPassword(String baichuanPassword) {
        getCurrentUserSetting().edit().putString(BAICHUAN_PASSWORD, baichuanPassword).apply();
    }

    public String getBaichuanPassword() {
        return getCurrentUserSetting().getString(BAICHUAN_PASSWORD, "");
    }





    public void setUserSex(String userImg) {
        getCurrentUserSetting().edit().putString(KEY_CURRENT_SEX, userImg).apply();
    }

    public void setUserAddress(String userImg) {
        getCurrentUserSetting().edit().putString(KEY_CURRENT_ADDRESS, userImg).apply();
    }

    public String getUserAddress() {
        return getCurrentUserSetting().getString(KEY_CURRENT_ADDRESS, "");
    }


    public String getUserSex() {
        return getCurrentUserSetting().getString(KEY_CURRENT_SEX, "");
    }

    public void setUserImg(String userImg) {
        getCurrentUserSetting().edit().putString(USER_IMG, userImg).apply();
    }

    public String getUserImg() {
        return getCurrentUserSetting().getString(USER_IMG, "");
    }

    public void setSessionkey(String sessionkey) {
        getCurrentUserSetting().edit().putString(SESSIONKEY, sessionkey).apply();
    }

    public String getSessionkey() {
        return getCurrentUserSetting().getString(SESSIONKEY, "");
    }

    public String getSessionkeyString() {
        String sessionkey = "-1";
        if (TextUtils.isEmpty(getSessionkey())) {
            return sessionkey;
        } else {
            return getSessionkey();
        }
    }

    public void setPhone(String phone) {
        getCurrentUserSetting().edit().putString(PHONE, phone).apply();
    }

    public String getPhone() {
        return getCurrentUserSetting().getString(PHONE, "");
    }

    public void setBaichuanAccount(String baichuanAccount) {
        getCurrentUserSetting().edit().putString(BAICHUAN_ACCOUNT, baichuanAccount).apply();
    }

    public String getBaichuanAccount() {
        return getCurrentUserSetting().getString(BAICHUAN_ACCOUNT, "");
    }

    public void setUsername(String username) {
        getCurrentUserSetting().edit().putString(USERNAME, username).apply();
    }

    public String getUsername() {
        return getCurrentUserSetting().getString(USERNAME, "");
    }

    public void setMessage(String message) {
        getCurrentUserSetting().edit().putString(MESSAGE, message).apply();
    }

    public String getMessage() {
        return getCurrentUserSetting().getString(MESSAGE, "");
    }


    public void setAlipayAuthState(String alipayAuthState) {
        getCurrentUserSetting().edit().putString(ALIPAY_AUTH_STATE, alipayAuthState).apply();
    }

    public String getAlipayAuthState() {
        return getCurrentUserSetting().getString(ALIPAY_AUTH_STATE, "");
    }

    public void setWechatAuthState(String wechatAuthState) {
        getCurrentUserSetting().edit().putString(WECHAT_AUTH_STATE, wechatAuthState).apply();
    }

    public String getWechatAuthState() {
        return getCurrentUserSetting().getString(WECHAT_AUTH_STATE, "");
    }

    public void saveMineInfo(LoginJsonBean.DataBean dataBean) {
        setCurrentUid(dataBean.getUserId());
        setBaichuanAccount(dataBean.getBaichuanAccount());
        setBaichuanPassword(dataBean.getBaichuanPassWord());
        setMessage(dataBean.getMessage());
        setPhone(dataBean.getPhone());
        setSessionkey(dataBean.getSessionKey());
        setUserImg(dataBean.getUserImg());
        setUsername(dataBean.getUserName());
        setCurrentUid(dataBean.getUserId());
        setWechatAuthState(dataBean.getWeChatAuthState() + "");
        setAlipayAuthState(dataBean.getAliPayAuthState() + "");
//        SharedPreferences.Editor editor = getCurrentUserSetting().edit();
//        editor.putString(USERNAME,dataBean.getUserName());
//        editor.putString(USER_IMG,dataBean.getUserImg());
//        editor.putString(PHONE,dataBean.getPhone());
//        editor.putString(SESSIONKEY,dataBean.getSessionKey());
//        editor.putString(BAICHUAN_ACCOUNT,dataBean.getBaichuanAccount());
//        editor.putString(BAICHUAN_PASSWORD,dataBean.getBaichuanPassWord());
//        editor.putString(MESSAGE,dataBean.getMessage());
//        editor.putString(KEY_USER_ID,dataBean.getUserId());
//        editor.putInt(WECHAT_AUTH_STATE,dataBean.getWeChatAuthState());
//        editor.putInt(ALIPAY_AUTH_STATE,dataBean.getAliPayAuthState());
//        editor.apply();
    }

    public void clear() {
        setCurrentUid("");
        SharedPreferences.Editor editor = getCurrentUserSetting().edit();
        editor.clear();
        editor.commit();
    }
}
