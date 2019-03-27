package com.trade.rrenji.bean.personal;

public class NetMineBean {


    /**
     * code : 0
     * msg : null
     * data : {"userImg":"http://qiniu.rrenji.com/FrRf3YXUKCQouw2D1QKfqW5rOsuy","address":"湖北武汉","sex":"0","userName":"小百","userId":"455"}
     */

    private String code;
    private String msg;
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * userImg : http://qiniu.rrenji.com/FrRf3YXUKCQouw2D1QKfqW5rOsuy
         * address : 湖北武汉
         * sex : 0
         * userName : 小百
         * userId : 455
         */

        private String userImg;
        private String address;
        private String sex;
        private String userName;
        private String userId;

        public String getUserImg() {
            return userImg;
        }

        public void setUserImg(String userImg) {
            this.userImg = userImg;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }

    @Override
    public String toString() {
        return "NetMineBean{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
