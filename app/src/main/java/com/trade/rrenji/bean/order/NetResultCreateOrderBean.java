package com.trade.rrenji.bean.order;

import java.util.List;

public class NetResultCreateOrderBean {


    /**
     * code : 0
     * msg : SUCCESS
     * result : {"orderId":"","sign":"","resultGroupPayList":[{"payType":"2","money":1800,"orderId":"155177383820624624","sonOrderId":"155177383820625609","sign":"alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2017082308339871&biz_content=%7B%22body%22%3A%22%E5%95%86%E5%93%81%E8%B4%AD%E5%8D%96%22%2C%22out_trade_no%22%3A%22155177383820624624%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E4%BA%BA%E4%BA%BA%E6%9C%BA-+iPhone+7+Plus+128G%E7%A3%A8%E7%A0%82%E9%BB%91%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%221899%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay¬ify_url=http%3A%2F%2F112.124.98.145%3A8090%2Fapi%2Frs%2FaliCallback&sign=Vt8Ic6GfyMh2jLTUVUnrx68Rbt3L%2BFbT9vLObkoIF6g3kaI5D9cQtI8kyWKHiUzK5WakiueT6pHLyRwfMki9X7ZpbOKDJ%2Bi3CNb225sEA7DEikcS8VSoejGYbC%2B4ZLybHwH5%2FU%2Fz8OGQx11fa3hEOFOCrz9RCFMzWkx%2BJ%2F9tV28ikIuAG2iD3yjFiWrVhBGBsH0kPSG3M1s85uiM6sNPqL%2FztAQNB6uBJHkQbvbgY%2FTfFlVZGERLrzD1AP0cG5fTooweDNKUce%2BJ%2BHIPYs%2BcJkIvaZaH5AZ%2FfrCVpK%2B1KiO5uE7R5HRRU3Q1gsI7%2BaLOHY5zaY8JHgtB%2FWE0nVR%2Fow%3D%3D&sign_type=RSA2×tamp=2019-03-05+16%3A17%3A18&version=1.0"},{"payType":"3","money":99,"orderId":"62dbcb0a37a19f97911cdae2738011c8","sonOrderId":"155177383841917224","sign":"1034155180264161888380"}]}
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
         * orderId :
         * sign :
         * resultGroupPayList : [{"payType":"2","money":1800,"orderId":"155177383820624624","sonOrderId":"155177383820625609","sign":"alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2017082308339871&biz_content=%7B%22body%22%3A%22%E5%95%86%E5%93%81%E8%B4%AD%E5%8D%96%22%2C%22out_trade_no%22%3A%22155177383820624624%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E4%BA%BA%E4%BA%BA%E6%9C%BA-+iPhone+7+Plus+128G%E7%A3%A8%E7%A0%82%E9%BB%91%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%221899%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay¬ify_url=http%3A%2F%2F112.124.98.145%3A8090%2Fapi%2Frs%2FaliCallback&sign=Vt8Ic6GfyMh2jLTUVUnrx68Rbt3L%2BFbT9vLObkoIF6g3kaI5D9cQtI8kyWKHiUzK5WakiueT6pHLyRwfMki9X7ZpbOKDJ%2Bi3CNb225sEA7DEikcS8VSoejGYbC%2B4ZLybHwH5%2FU%2Fz8OGQx11fa3hEOFOCrz9RCFMzWkx%2BJ%2F9tV28ikIuAG2iD3yjFiWrVhBGBsH0kPSG3M1s85uiM6sNPqL%2FztAQNB6uBJHkQbvbgY%2FTfFlVZGERLrzD1AP0cG5fTooweDNKUce%2BJ%2BHIPYs%2BcJkIvaZaH5AZ%2FfrCVpK%2B1KiO5uE7R5HRRU3Q1gsI7%2BaLOHY5zaY8JHgtB%2FWE0nVR%2Fow%3D%3D&sign_type=RSA2×tamp=2019-03-05+16%3A17%3A18&version=1.0"},{"payType":"3","money":99,"orderId":"62dbcb0a37a19f97911cdae2738011c8","sonOrderId":"155177383841917224","sign":"1034155180264161888380"}]
         */

        private String orderId;
        private String sign;
        private List<ResultGroupPayListBean> resultGroupPayList;

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public List<ResultGroupPayListBean> getResultGroupPayList() {
            return resultGroupPayList;
        }

        public void setResultGroupPayList(List<ResultGroupPayListBean> resultGroupPayList) {
            this.resultGroupPayList = resultGroupPayList;
        }

        public static class ResultGroupPayListBean {
            /**
             * payType : 2
             * money : 1800
             * orderId : 155177383820624624
             * sonOrderId : 155177383820625609
             * sign : alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2017082308339871&biz_content=%7B%22body%22%3A%22%E5%95%86%E5%93%81%E8%B4%AD%E5%8D%96%22%2C%22out_trade_no%22%3A%22155177383820624624%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E4%BA%BA%E4%BA%BA%E6%9C%BA-+iPhone+7+Plus+128G%E7%A3%A8%E7%A0%82%E9%BB%91%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%221899%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay¬ify_url=http%3A%2F%2F112.124.98.145%3A8090%2Fapi%2Frs%2FaliCallback&sign=Vt8Ic6GfyMh2jLTUVUnrx68Rbt3L%2BFbT9vLObkoIF6g3kaI5D9cQtI8kyWKHiUzK5WakiueT6pHLyRwfMki9X7ZpbOKDJ%2Bi3CNb225sEA7DEikcS8VSoejGYbC%2B4ZLybHwH5%2FU%2Fz8OGQx11fa3hEOFOCrz9RCFMzWkx%2BJ%2F9tV28ikIuAG2iD3yjFiWrVhBGBsH0kPSG3M1s85uiM6sNPqL%2FztAQNB6uBJHkQbvbgY%2FTfFlVZGERLrzD1AP0cG5fTooweDNKUce%2BJ%2BHIPYs%2BcJkIvaZaH5AZ%2FfrCVpK%2B1KiO5uE7R5HRRU3Q1gsI7%2BaLOHY5zaY8JHgtB%2FWE0nVR%2Fow%3D%3D&sign_type=RSA2×tamp=2019-03-05+16%3A17%3A18&version=1.0
             */

            private String payType;
            private int money;
            private String orderId;
            private String sonOrderId;
            private String sign;

            public String getPayType() {
                return payType;
            }

            public void setPayType(String payType) {
                this.payType = payType;
            }

            public int getMoney() {
                return money;
            }

            public void setMoney(int money) {
                this.money = money;
            }

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
            }

            public String getSonOrderId() {
                return sonOrderId;
            }

            public void setSonOrderId(String sonOrderId) {
                this.sonOrderId = sonOrderId;
            }

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }
        }
    }
}
