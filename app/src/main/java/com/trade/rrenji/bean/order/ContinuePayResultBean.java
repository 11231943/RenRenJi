package com.trade.rrenji.bean.order;

import java.util.List;

public class ContinuePayResultBean {

    /**
     * code : 0
     * msg : SUCCESS
     * result : {"orderSign":"alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2017082308339871&biz_content=%7B%22body%22%3A%22%E5%95%86%E5%93%81%E8%B4%AD%E5%8D%96%22%2C%22out_trade_no%22%3A%22155261533041304123%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E4%BA%BA%E4%BA%BA%E6%9C%BA-+iPhone+7+32G%E9%BB%91%E8%89%B2%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%22137.01%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F112.124.98.145%3A8090%2Fapi%2Frs%2FaliCallback&sign=EYJ4cjB28X4t2%2FT7XZrMaCWlZhsb8wG45MA%2FYge1EAkJruAL2lFuTuM6K%2FMLWPFQAtvTX07GmkcfXDcEfzPG9%2BJarS5wf4helTi77dOIpiQnbdmb9QswW00GBhSQmWROH%2FIvvxXqxc9ChpEg0jTT1N5zA1vjwO9rtQ8RV88MpTWcOn6oLZtR3Z8j1k%2BhR0TQU9%2BlJKi%2FmHDHNSZ9bEzxfG1CAAEjB44b9113M2ZdD%2BSF6ZwcrSI6y4fA4cPN4w9D0vgVXuWU9THlXtntQqoI4G3piBQlBTjcTXTgw5xDMDEevB07STzQXOphKFgekIarQ4RouMHP0DIfessw5dwuqQ%3D%3D&sign_type=RSA2&timestamp=2019-03-15+10%3A35%3A38&version=1.0","orderId":"155261533041304123","resultGroupPayList":[]}
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
        /**
         * orderSign : alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2017082308339871&biz_content=%7B%22body%22%3A%22%E5%95%86%E5%93%81%E8%B4%AD%E5%8D%96%22%2C%22out_trade_no%22%3A%22155261533041304123%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E4%BA%BA%E4%BA%BA%E6%9C%BA-+iPhone+7+32G%E9%BB%91%E8%89%B2%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%22137.01%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F112.124.98.145%3A8090%2Fapi%2Frs%2FaliCallback&sign=EYJ4cjB28X4t2%2FT7XZrMaCWlZhsb8wG45MA%2FYge1EAkJruAL2lFuTuM6K%2FMLWPFQAtvTX07GmkcfXDcEfzPG9%2BJarS5wf4helTi77dOIpiQnbdmb9QswW00GBhSQmWROH%2FIvvxXqxc9ChpEg0jTT1N5zA1vjwO9rtQ8RV88MpTWcOn6oLZtR3Z8j1k%2BhR0TQU9%2BlJKi%2FmHDHNSZ9bEzxfG1CAAEjB44b9113M2ZdD%2BSF6ZwcrSI6y4fA4cPN4w9D0vgVXuWU9THlXtntQqoI4G3piBQlBTjcTXTgw5xDMDEevB07STzQXOphKFgekIarQ4RouMHP0DIfessw5dwuqQ%3D%3D&sign_type=RSA2&timestamp=2019-03-15+10%3A35%3A38&version=1.0
         * orderId : 155261533041304123
         * resultGroupPayList : []
         */

        private String orderSign;
        private String orderId;
        private List<?> resultGroupPayList;

        public String getOrderSign() {
            return orderSign;
        }

        public void setOrderSign(String orderSign) {
            this.orderSign = orderSign;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public List<?> getResultGroupPayList() {
            return resultGroupPayList;
        }

        public void setResultGroupPayList(List<?> resultGroupPayList) {
            this.resultGroupPayList = resultGroupPayList;
        }
    }
}
