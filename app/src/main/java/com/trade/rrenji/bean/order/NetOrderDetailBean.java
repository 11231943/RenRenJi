package com.trade.rrenji.bean.order;

import java.io.Serializable;
import java.util.List;

public class NetOrderDetailBean {


    /**
     * code : 0
     * msg : null
     * data : {"orderDetail":{"orderId":"155240735118666065","userId":"455","payType":"2","createTime":"2019-03-13 00:15:51","payStatus":"2","orderSum":0.01,"restSum":0.01,"userCouponId":null,"couponValue":null,"addressId":null,"addressUserName":"彭浩","addressPhone":"17603009825","addressDesc":"北京市 北京市 宣武区 呼家楼圣世一品","goodsId":null,"goodsNum":"1","goodsName":"iPhone8 256G 白色","goodsImg":"http://qiniu.rrenji.com/FsTt4G_xpjIWS-tLO4v5ZbOY7onL","goodsPrice":0.01,"expressType":"1","plan":"0","isFree":null,"userMessage":"","accessoryList":[],"orderGroupPayList":[],"logisticsNumber":null}}
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
         * orderDetail : {"orderId":"155240735118666065","userId":"455","payType":"2","createTime":"2019-03-13 00:15:51","payStatus":"2","orderSum":0.01,"restSum":0.01,"userCouponId":null,"couponValue":null,"addressId":null,"addressUserName":"彭浩","addressPhone":"17603009825","addressDesc":"北京市 北京市 宣武区 呼家楼圣世一品","goodsId":null,"goodsNum":"1","goodsName":"iPhone8 256G 白色","goodsImg":"http://qiniu.rrenji.com/FsTt4G_xpjIWS-tLO4v5ZbOY7onL","goodsPrice":0.01,"expressType":"1","plan":"0","isFree":null,"userMessage":"","accessoryList":[],"orderGroupPayList":[],"logisticsNumber":null}
         */

        private OrderDetailBean orderDetail;

        public OrderDetailBean getOrderDetail() {
            return orderDetail;
        }

        public void setOrderDetail(OrderDetailBean orderDetail) {
            this.orderDetail = orderDetail;
        }

        public static class OrderDetailBean {
            /**
             * orderId : 155240735118666065
             * userId : 455
             * payType : 2
             * createTime : 2019-03-13 00:15:51
             * payStatus : 2
             * orderSum : 0.01
             * restSum : 0.01
             * userCouponId : null
             * couponValue : null
             * addressId : null
             * addressUserName : 彭浩
             * addressPhone : 17603009825
             * addressDesc : 北京市 北京市 宣武区 呼家楼圣世一品
             * goodsId : null
             * goodsNum : 1
             * goodsName : iPhone8 256G 白色
             * goodsImg : http://qiniu.rrenji.com/FsTt4G_xpjIWS-tLO4v5ZbOY7onL
             * goodsPrice : 0.01
             * expressType : 1
             * plan : 0
             * isFree : null
             * userMessage :
             * accessoryList : []
             * orderGroupPayList : []
             * logisticsNumber : null
             */

            private String orderId;
            private String userId;
            private String payType;
            private String createTime;
            private String payStatus;
            private double orderSum;
            private double restSum;
            private String userCouponId;
            private String couponValue;
            private String addressId;
            private String addressUserName;
            private String addressPhone;
            private String addressDesc;
            private String goodsId;
            private String goodsNum;
            private String goodsName;
            private String goodsImg;
            private double goodsPrice;
            private String expressType;
            private String plan;
            private String isFree;
            private String userMessage;
            private String logisticsNumber;
            private List<AccessoryListBean> accessoryList;
            private List<?> orderGroupPayList;

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getPayType() {
                return payType;
            }

            public void setPayType(String payType) {
                this.payType = payType;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getPayStatus() {
                return payStatus;
            }

            public void setPayStatus(String payStatus) {
                this.payStatus = payStatus;
            }

            public double getOrderSum() {
                return orderSum;
            }

            public void setOrderSum(double orderSum) {
                this.orderSum = orderSum;
            }

            public double getRestSum() {
                return restSum;
            }

            public void setRestSum(double restSum) {
                this.restSum = restSum;
            }

            public Object getUserCouponId() {
                return userCouponId;
            }

            public void setUserCouponId(String userCouponId) {
                this.userCouponId = userCouponId;
            }

            public String getCouponValue() {
                return couponValue;
            }

            public void setCouponValue(String couponValue) {
                this.couponValue = couponValue;
            }

            public String getAddressId() {
                return addressId;
            }

            public void setAddressId(String addressId) {
                this.addressId = addressId;
            }

            public String getAddressUserName() {
                return addressUserName;
            }

            public void setAddressUserName(String addressUserName) {
                this.addressUserName = addressUserName;
            }

            public String getAddressPhone() {
                return addressPhone;
            }

            public void setAddressPhone(String addressPhone) {
                this.addressPhone = addressPhone;
            }

            public String getAddressDesc() {
                return addressDesc;
            }

            public void setAddressDesc(String addressDesc) {
                this.addressDesc = addressDesc;
            }

            public String getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(String goodsId) {
                this.goodsId = goodsId;
            }

            public String getGoodsNum() {
                return goodsNum;
            }

            public void setGoodsNum(String goodsNum) {
                this.goodsNum = goodsNum;
            }

            public String getGoodsName() {
                return goodsName;
            }

            public void setGoodsName(String goodsName) {
                this.goodsName = goodsName;
            }

            public String getGoodsImg() {
                return goodsImg;
            }

            public void setGoodsImg(String goodsImg) {
                this.goodsImg = goodsImg;
            }

            public double getGoodsPrice() {
                return goodsPrice;
            }

            public void setGoodsPrice(double goodsPrice) {
                this.goodsPrice = goodsPrice;
            }

            public String getExpressType() {
                return expressType;
            }

            public void setExpressType(String expressType) {
                this.expressType = expressType;
            }

            public String getPlan() {
                return plan;
            }

            public void setPlan(String plan) {
                this.plan = plan;
            }

            public String getIsFree() {
                return isFree;
            }

            public void setIsFree(String isFree) {
                this.isFree = isFree;
            }

            public String getUserMessage() {
                return userMessage;
            }

            public void setUserMessage(String userMessage) {
                this.userMessage = userMessage;
            }

            public String getLogisticsNumber() {
                return logisticsNumber;
            }

            public void setLogisticsNumber(String logisticsNumber) {
                this.logisticsNumber = logisticsNumber;
            }

            public List<AccessoryListBean> getAccessoryList() {
                return accessoryList;
            }

            public void setAccessoryList(List<AccessoryListBean> accessoryList) {
                this.accessoryList = accessoryList;
            }

            public List<?> getOrderGroupPayList() {
                return orderGroupPayList;
            }

            public void setOrderGroupPayList(List<?> orderGroupPayList) {
                this.orderGroupPayList = orderGroupPayList;
            }
        }
        public static class AccessoryListBean implements Serializable {
            /**
             * accessoryId : 1
             * accessoryName : (Apple) AirPods 无线耳机
             * count : 1
             * payPrice : 899
             */

            private String accessoryId;
            private String accessoryName;
            private int count;
            private double payPrice;
            private String imageUrl;

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public String getAccessoryId() {
                return accessoryId;
            }

            public void setAccessoryId(String accessoryId) {
                this.accessoryId = accessoryId;
            }

            public String getAccessoryName() {
                return accessoryName;
            }

            public void setAccessoryName(String accessoryName) {
                this.accessoryName = accessoryName;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public double getPayPrice() {
                return payPrice;
            }

            public void setPayPrice(double payPrice) {
                this.payPrice = payPrice;
            }
        }

    }
}
