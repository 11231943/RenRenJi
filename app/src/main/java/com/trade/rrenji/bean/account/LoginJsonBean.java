package com.trade.rrenji.bean.account;

/**
 * Created by ${Wangyun} on 2017/10/13.
 */

public class LoginJsonBean {

    /**
     * code : 0
     * data : {"baichuanPassWord":"renrenji123","userImg":"","weChatAuthState":0,"sessionKey":"11cdf167edab48e9a03351cc1fe16bcc","phone":"17603009825","baichuanAccount":"13332959300@139.com","userName":"雷锋","message":"已加入人人机5天","userId":"455","aliPayAuthState":0}
     */

    private String code;
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * baichuanPassWord : renrenji123
         * userImg :
         * weChatAuthState : 0
         * sessionKey : 11cdf167edab48e9a03351cc1fe16bcc
         * phone : 17603009825
         * baichuanAccount : 13332959300@139.com
         * userName : 雷锋
         * message : 已加入人人机5天
         * userId : 455
         * aliPayAuthState : 0
         */

        private String baichuanPassWord;
        private String userImg;
        private int weChatAuthState;
        private String sessionKey;
        private String phone;
        private String baichuanAccount;
        private String userName;
        private String message;
        private String userId;
        private int aliPayAuthState;

        public String getBaichuanPassWord() {
            return baichuanPassWord;
        }

        public void setBaichuanPassWord(String baichuanPassWord) {
            this.baichuanPassWord = baichuanPassWord;
        }

        public String getUserImg() {
            return userImg;
        }

        public void setUserImg(String userImg) {
            this.userImg = userImg;
        }

        public int getWeChatAuthState() {
            return weChatAuthState;
        }

        public void setWeChatAuthState(int weChatAuthState) {
            this.weChatAuthState = weChatAuthState;
        }

        public String getSessionKey() {
            return sessionKey;
        }

        public void setSessionKey(String sessionKey) {
            this.sessionKey = sessionKey;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getBaichuanAccount() {
            return baichuanAccount;
        }

        public void setBaichuanAccount(String baichuanAccount) {
            this.baichuanAccount = baichuanAccount;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public int getAliPayAuthState() {
            return aliPayAuthState;
        }

        public void setAliPayAuthState(int aliPayAuthState) {
            this.aliPayAuthState = aliPayAuthState;
        }
    }
}
