package com.trade.rrenji.bean.order;

import java.util.List;

public class NetPayPlanInfoBean {


    /**
     * code : 0
     * msg : null
     * data : {"aliPayList":[{"plan":"3","rate":"0.023","fee":"45.99","planFee":"15.33","firstPay":"682.00","laterPay":null,"total":"2091.99","isFree":"0"},{"plan":"6","rate":"0.045","fee":"90.00","planFee":"15.00","firstPay":"348.34","laterPay":null,"total":"2180.04","isFree":"0"},{"plan":"12","rate":"0.075","fee":"150.00","planFee":"12.50","firstPay":"179.17","laterPay":null,"total":"2300.04","isFree":"0"}],"jdPayList":[{"plan":"3","rate":"0.500","fee":"30.00","planFee":"10.00","firstPay":"676.67","laterPay":"676.67","total":"2060.01","isFree":null},{"plan":"6","rate":"0.500","fee":"60.00","planFee":"10.00","firstPay":"343.31","laterPay":"343.34","total":"2120.01","isFree":null},{"plan":"12","rate":"0.500","fee":"120.00","planFee":"10.00","firstPay":"176.64","laterPay":"176.67","total":"2240.01","isFree":null}]}
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
        private List<AliPayListBean> aliPayList;
        private List<JdPayListBean> jdPayList;

        public List<AliPayListBean> getAliPayList() {
            return aliPayList;
        }

        public void setAliPayList(List<AliPayListBean> aliPayList) {
            this.aliPayList = aliPayList;
        }

        public List<JdPayListBean> getJdPayList() {
            return jdPayList;
        }

        public void setJdPayList(List<JdPayListBean> jdPayList) {
            this.jdPayList = jdPayList;
        }

        public static class AliPayListBean {
            /**
             * plan : 3
             * rate : 0.023
             * fee : 45.99
             * planFee : 15.33
             * firstPay : 682.00
             * laterPay : null
             * total : 2091.99
             * isFree : 0
             */

            private String plan;
            private String rate;
            private String fee;
            private String planFee;
            private String firstPay;
            private Object laterPay;
            private String total;
            private String isFree;

            public String getPlan() {
                return plan;
            }

            public void setPlan(String plan) {
                this.plan = plan;
            }

            public String getRate() {
                return rate;
            }

            public void setRate(String rate) {
                this.rate = rate;
            }

            public String getFee() {
                return fee;
            }

            public void setFee(String fee) {
                this.fee = fee;
            }

            public String getPlanFee() {
                return planFee;
            }

            public void setPlanFee(String planFee) {
                this.planFee = planFee;
            }

            public String getFirstPay() {
                return firstPay;
            }

            public void setFirstPay(String firstPay) {
                this.firstPay = firstPay;
            }

            public Object getLaterPay() {
                return laterPay;
            }

            public void setLaterPay(Object laterPay) {
                this.laterPay = laterPay;
            }

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

            public String getIsFree() {
                return isFree;
            }

            public void setIsFree(String isFree) {
                this.isFree = isFree;
            }
        }

        public static class JdPayListBean {
            /**
             * plan : 3
             * rate : 0.500
             * fee : 30.00
             * planFee : 10.00
             * firstPay : 676.67
             * laterPay : 676.67
             * total : 2060.01
             * isFree : null
             */

            private String plan;
            private String rate;
            private String fee;
            private String planFee;
            private String firstPay;
            private String laterPay;
            private String total;
            private String isFree;

            public String getPlan() {
                return plan;
            }

            public void setPlan(String plan) {
                this.plan = plan;
            }

            public String getRate() {
                return rate;
            }

            public void setRate(String rate) {
                this.rate = rate;
            }

            public String getFee() {
                return fee;
            }

            public void setFee(String fee) {
                this.fee = fee;
            }

            public String getPlanFee() {
                return planFee;
            }

            public void setPlanFee(String planFee) {
                this.planFee = planFee;
            }

            public String getFirstPay() {
                return firstPay;
            }

            public void setFirstPay(String firstPay) {
                this.firstPay = firstPay;
            }

            public String getLaterPay() {
                return laterPay;
            }

            public void setLaterPay(String laterPay) {
                this.laterPay = laterPay;
            }

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

            public String getIsFree() {
                return isFree;
            }

            public void setIsFree(String isFree) {
                this.isFree = isFree;
            }
        }
    }
}
