package com.trade.rrenji.bean.order;

import java.util.List;

public class NetOrderBean {

    /**
     * code : 0
     * msg : SUCCESS
     * result : {"pageInfo":{"currentPage":1,"totalPage":1,"pageSize":10,"totalRow":1},"myOrderList":[{"orderId":"1000000000002065","createOrderTime":"2018-12-14T01:03:53.000+08:00","goodId":"383","goodName":"全新未拆封 iPhone XR 128G 全色","goodImg":"http://qiniu.rrenji.com/FqphBHB1r05jJieLpHq83-QU0cuD","goodPrice":"6599.00","orderSum":"6599.00","goodsCode":"1540962077544","orderStatus":1,"orderStr":"alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2017082308339871&biz_content=%7B%22body%22%3A%22%E5%95%86%E5%93%81%E8%B4%AD%E5%8D%96%22%2C%22out_trade_no%22%3A%221000000000002065%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E4%BA%BA%E4%BA%BA%E6%9C%BA-%E5%85%A8%E6%96%B0%E6%9C%AA%E6%8B%86%E5%B0%81+iPhone+XR+128G+%E5%85%A8%E8%89%B2%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%226599.00%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F112.124.98.145%3A8080%2Fapi%2Frs%2FaliCallback&sign=tSRe1ZpuH69%2FKmbrxZ835d11nypBYieFVUI7hLFdCZc%2Fr2RJ4xp6xGsjn5aKYEI5ESvn35rqVURho0tzMUF7bHLaWEknO5PvlrQcgwZGa3k0AYhJxl%2BT0AOo%2F6E3Lll6KIoHzOdjekFaUXOMnva9Z6ZebeJhCXPmER5SaXN1SzP%2B7S6yqjpsaLh68YPwILouzCfmHpzAB492oax6IC73kx2YIyp6E%2BCRqv7NeO1BvjHO%2Bix1U0jJKg84uvqbg8mRDZfID0whzxDRmOJVuKWvVq3NRgMqunSSGVI2dl6aAzNCmSBube2%2BdyIDF9h%2BHU3kERIO8bXkCT6v4G5xGiaY2Q%3D%3D&sign_type=RSA2&timestamp=2018-12-14+01%3A06%3A23&version=1.0","customerId":"","isShared":false,"payType":2,"jdOrderId":""}]}
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
         * pageInfo : {"currentPage":1,"totalPage":1,"pageSize":10,"totalRow":1}
         * myOrderList : [{"orderId":"1000000000002065","createOrderTime":"2018-12-14T01:03:53.000+08:00","goodId":"383","goodName":"全新未拆封 iPhone XR 128G 全色","goodImg":"http://qiniu.rrenji.com/FqphBHB1r05jJieLpHq83-QU0cuD","goodPrice":"6599.00","orderSum":"6599.00","goodsCode":"1540962077544","orderStatus":1,"orderStr":"alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2017082308339871&biz_content=%7B%22body%22%3A%22%E5%95%86%E5%93%81%E8%B4%AD%E5%8D%96%22%2C%22out_trade_no%22%3A%221000000000002065%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E4%BA%BA%E4%BA%BA%E6%9C%BA-%E5%85%A8%E6%96%B0%E6%9C%AA%E6%8B%86%E5%B0%81+iPhone+XR+128G+%E5%85%A8%E8%89%B2%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%226599.00%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F112.124.98.145%3A8080%2Fapi%2Frs%2FaliCallback&sign=tSRe1ZpuH69%2FKmbrxZ835d11nypBYieFVUI7hLFdCZc%2Fr2RJ4xp6xGsjn5aKYEI5ESvn35rqVURho0tzMUF7bHLaWEknO5PvlrQcgwZGa3k0AYhJxl%2BT0AOo%2F6E3Lll6KIoHzOdjekFaUXOMnva9Z6ZebeJhCXPmER5SaXN1SzP%2B7S6yqjpsaLh68YPwILouzCfmHpzAB492oax6IC73kx2YIyp6E%2BCRqv7NeO1BvjHO%2Bix1U0jJKg84uvqbg8mRDZfID0whzxDRmOJVuKWvVq3NRgMqunSSGVI2dl6aAzNCmSBube2%2BdyIDF9h%2BHU3kERIO8bXkCT6v4G5xGiaY2Q%3D%3D&sign_type=RSA2&timestamp=2018-12-14+01%3A06%3A23&version=1.0","customerId":"","isShared":false,"payType":2,"jdOrderId":""}]
         */

        private PageInfoBean pageInfo;
        private List<MyOrderListBean> myOrderList;

        public PageInfoBean getPageInfo() {
            return pageInfo;
        }

        public void setPageInfo(PageInfoBean pageInfo) {
            this.pageInfo = pageInfo;
        }

        public List<MyOrderListBean> getMyOrderList() {
            return myOrderList;
        }

        public void setMyOrderList(List<MyOrderListBean> myOrderList) {
            this.myOrderList = myOrderList;
        }

        public static class PageInfoBean {
            /**
             * currentPage : 1
             * totalPage : 1
             * pageSize : 10
             * totalRow : 1
             */

            private int currentPage;
            private int totalPage;
            private int pageSize;
            private int totalRow;

            public int getCurrentPage() {
                return currentPage;
            }

            public void setCurrentPage(int currentPage) {
                this.currentPage = currentPage;
            }

            public int getTotalPage() {
                return totalPage;
            }

            public void setTotalPage(int totalPage) {
                this.totalPage = totalPage;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public int getTotalRow() {
                return totalRow;
            }

            public void setTotalRow(int totalRow) {
                this.totalRow = totalRow;
            }
        }

        public static class MyOrderListBean {
            /**
             * orderId : 1000000000002065
             * createOrderTime : 2018-12-14T01:03:53.000+08:00
             * goodId : 383
             * goodName : 全新未拆封 iPhone XR 128G 全色
             * goodImg : http://qiniu.rrenji.com/FqphBHB1r05jJieLpHq83-QU0cuD
             * goodPrice : 6599.00
             * orderSum : 6599.00
             * goodsCode : 1540962077544
             * orderStatus : 1
             * orderStr : alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2017082308339871&biz_content=%7B%22body%22%3A%22%E5%95%86%E5%93%81%E8%B4%AD%E5%8D%96%22%2C%22out_trade_no%22%3A%221000000000002065%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E4%BA%BA%E4%BA%BA%E6%9C%BA-%E5%85%A8%E6%96%B0%E6%9C%AA%E6%8B%86%E5%B0%81+iPhone+XR+128G+%E5%85%A8%E8%89%B2%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%226599.00%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F112.124.98.145%3A8080%2Fapi%2Frs%2FaliCallback&sign=tSRe1ZpuH69%2FKmbrxZ835d11nypBYieFVUI7hLFdCZc%2Fr2RJ4xp6xGsjn5aKYEI5ESvn35rqVURho0tzMUF7bHLaWEknO5PvlrQcgwZGa3k0AYhJxl%2BT0AOo%2F6E3Lll6KIoHzOdjekFaUXOMnva9Z6ZebeJhCXPmER5SaXN1SzP%2B7S6yqjpsaLh68YPwILouzCfmHpzAB492oax6IC73kx2YIyp6E%2BCRqv7NeO1BvjHO%2Bix1U0jJKg84uvqbg8mRDZfID0whzxDRmOJVuKWvVq3NRgMqunSSGVI2dl6aAzNCmSBube2%2BdyIDF9h%2BHU3kERIO8bXkCT6v4G5xGiaY2Q%3D%3D&sign_type=RSA2&timestamp=2018-12-14+01%3A06%3A23&version=1.0
             * customerId :
             * isShared : false
             * payType : 2
             * jdOrderId :
             */

            private String orderId;
            private String createOrderTime;
            private String goodId;
            private String goodName;
            private String goodImg;
            private String goodPrice;
            private String orderSum;
            private String goodsCode;
            private int orderStatus;
            private String orderStr;
            private String customerId;
            private boolean isShared;
            private int payType;
            private String jdOrderId;

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
            }

            public String getCreateOrderTime() {
                return createOrderTime;
            }

            public void setCreateOrderTime(String createOrderTime) {
                this.createOrderTime = createOrderTime;
            }

            public String getGoodId() {
                return goodId;
            }

            public void setGoodId(String goodId) {
                this.goodId = goodId;
            }

            public String getGoodName() {
                return goodName;
            }

            public void setGoodName(String goodName) {
                this.goodName = goodName;
            }

            public String getGoodImg() {
                return goodImg;
            }

            public void setGoodImg(String goodImg) {
                this.goodImg = goodImg;
            }

            public String getGoodPrice() {
                return goodPrice;
            }

            public void setGoodPrice(String goodPrice) {
                this.goodPrice = goodPrice;
            }

            public String getOrderSum() {
                return orderSum;
            }

            public void setOrderSum(String orderSum) {
                this.orderSum = orderSum;
            }

            public String getGoodsCode() {
                return goodsCode;
            }

            public void setGoodsCode(String goodsCode) {
                this.goodsCode = goodsCode;
            }

            public int getOrderStatus() {
                return orderStatus;
            }

            public void setOrderStatus(int orderStatus) {
                this.orderStatus = orderStatus;
            }

            public String getOrderStr() {
                return orderStr;
            }

            public void setOrderStr(String orderStr) {
                this.orderStr = orderStr;
            }

            public String getCustomerId() {
                return customerId;
            }

            public void setCustomerId(String customerId) {
                this.customerId = customerId;
            }

            public boolean isIsShared() {
                return isShared;
            }

            public void setIsShared(boolean isShared) {
                this.isShared = isShared;
            }

            public int getPayType() {
                return payType;
            }

            public void setPayType(int payType) {
                this.payType = payType;
            }

            public String getJdOrderId() {
                return jdOrderId;
            }

            public void setJdOrderId(String jdOrderId) {
                this.jdOrderId = jdOrderId;
            }
        }
    }
}
