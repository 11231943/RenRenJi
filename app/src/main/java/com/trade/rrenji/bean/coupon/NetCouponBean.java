package com.trade.rrenji.bean.coupon;

import java.util.List;

public class NetCouponBean {

    /**
     * code : 0
     * msg : SUCCESS
     * result : {"couponList":[{"couponId":8,"couponValue":"1","couponRuleList":["线上使用","满100使用","2017-12-04 00:14:16 至 2019-12-29 00:00:00"],"isOutOfDate":true,"limitPayValue":"0.01"},{"couponId":9,"couponValue":"100","couponRuleList":["线上使用","满100使用","2017-12-04 00:14:16 至 2019-12-29 00:00:00"],"isOutOfDate":true,"limitPayValue":"0.01"},{"couponId":10,"couponValue":"1000","couponRuleList":["线上使用","满100使用","2017-12-04 00:14:16 至 2019-12-29 00:00:00"],"isOutOfDate":true,"limitPayValue":"0.01"},{"couponId":11,"couponValue":"2000","couponRuleList":["线上使用","满100使用","2017-12-04 00:14:16 至 2019-12-29 00:00:00"],"isOutOfDate":true,"limitPayValue":"0.01"},{"couponId":12,"couponValue":"200","couponRuleList":["线上使用","满100使用","2017-12-04 00:14:16 至 2019-12-29 00:00:00"],"isOutOfDate":true,"limitPayValue":"0.01"},{"couponId":13,"couponValue":"300","couponRuleList":["线上使用","满100使用","2017-12-04 00:14:16 至 2019-12-29 00:00:00"],"isOutOfDate":true,"limitPayValue":"0.01"},{"couponId":14,"couponValue":"500","couponRuleList":["线上使用","满100使用","2017-12-04 00:14:16 至 2019-12-29 00:00:00"],"isOutOfDate":true,"limitPayValue":"0.01"},{"couponId":15,"couponValue":"700","couponRuleList":["线上使用","满100使用","2017-12-04 00:14:16 至 2019-12-29 00:00:00"],"isOutOfDate":true,"limitPayValue":"0.01"},{"couponId":16,"couponValue":"100","couponRuleList":["线上使用","满100使用","2017-12-04 00:14:16 至 2019-12-29 00:00:00"],"isOutOfDate":true,"limitPayValue":"0.01"},{"couponId":17,"couponValue":"101","couponRuleList":["线上使用","满100使用","2017-12-04 00:14:16 至 2019-12-29 00:00:00"],"isOutOfDate":true,"limitPayValue":"0.01"}]}
     */

    private String code;
    private String msg;
    private ResultBean result;

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

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private List<CouponListBean> couponList;

        public List<CouponListBean> getCouponList() {
            return couponList;
        }

        public void setCouponList(List<CouponListBean> couponList) {
            this.couponList = couponList;
        }

        public static class CouponListBean {
            /**
             * couponId : 8
             * couponValue : 1
             * couponRuleList : ["线上使用","满100使用","2017-12-04 00:14:16 至 2019-12-29 00:00:00"]
             * isOutOfDate : true
             * limitPayValue : 0.01
             */

            private int couponId;
            private String couponValue;
            private boolean isOutOfDate;
            private String limitPayValue;
            private List<String> couponRuleList;

            public int getCouponId() {
                return couponId;
            }

            public void setCouponId(int couponId) {
                this.couponId = couponId;
            }

            public String getCouponValue() {
                return couponValue;
            }

            public void setCouponValue(String couponValue) {
                this.couponValue = couponValue;
            }

            public boolean isIsOutOfDate() {
                return isOutOfDate;
            }

            public void setIsOutOfDate(boolean isOutOfDate) {
                this.isOutOfDate = isOutOfDate;
            }

            public String getLimitPayValue() {
                return limitPayValue;
            }

            public void setLimitPayValue(String limitPayValue) {
                this.limitPayValue = limitPayValue;
            }

            public List<String> getCouponRuleList() {
                return couponRuleList;
            }

            public void setCouponRuleList(List<String> couponRuleList) {
                this.couponRuleList = couponRuleList;
            }
        }
    }
}
