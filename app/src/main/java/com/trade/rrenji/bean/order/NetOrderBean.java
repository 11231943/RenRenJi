package com.trade.rrenji.bean.order;

import java.io.Serializable;
import java.util.List;

public class NetOrderBean {


    /**
     * code : 0
     * msg : null
     * data : {"resultList":[{"orderId":"155213369102471006","goodsName":"iPhone8 256G 白色","goodsImg":"http://qiniu.rrenji.com/FsTt4G_xpjIWS-tLO4v5ZbOY7onL","payStatus":"1","orderType":"1","orderSum":899.01,"restMoney":899.01,"createTime":"2019-03-09 20:14:51","accessoryList":[{"accessoryId":"1","accessoryName":"(Apple) AirPods 无线耳机","count":1,"payPrice":899}]},{"orderId":"155211304981325205","goodsName":"iPhone8 256G 白色","goodsImg":"http://qiniu.rrenji.com/FsTt4G_xpjIWS-tLO4v5ZbOY7onL","payStatus":"1","orderType":"1","orderSum":0.01,"restMoney":0.01,"createTime":"2019-03-09 14:30:50","accessoryList":[]},{"orderId":"155201555183428939","goodsName":"iPhone8 256G 白色","goodsImg":"http://qiniu.rrenji.com/FsTt4G_xpjIWS-tLO4v5ZbOY7onL","payStatus":"1","orderType":"1","orderSum":0.01,"restMoney":0.01,"createTime":"2019-03-08 11:25:51","accessoryList":[]}]}
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
        private List<ResultListBean> resultList;

        public List<ResultListBean> getResultList() {
            return resultList;
        }

        public void setResultList(List<ResultListBean> resultList) {
            this.resultList = resultList;
        }

        public static class ResultListBean implements Serializable {
            /**
             * orderId : 155213369102471006
             * goodsName : iPhone8 256G 白色
             * goodsImg : http://qiniu.rrenji.com/FsTt4G_xpjIWS-tLO4v5ZbOY7onL
             * payStatus : 1
             * orderType : 1
             * orderSum : 899.01
             * restMoney : 899.01
             * createTime : 2019-03-09 20:14:51
             * accessoryList : [{"accessoryId":"1","accessoryName":"(Apple) AirPods 无线耳机","count":1,"payPrice":899}]
             * addressDetail: 北京市 北京市 宣武区 呼家楼圣世一品,
             * addressPhone: 17603009825,
             * addressId: "281"
             */
            private String addressDetail;
            private String addressPhone;
            private String addressId;

            private String orderId;
            private String goodsName;
            private String goodsImg;
            private String payStatus;
            private String orderType;
            private double orderSum;
            private double goodsPrice;
            private double restMoney;
            private double couponValue;

            public double getCouponValue() {
                return couponValue;
            }

            public void setCouponValue(double couponValue) {
                this.couponValue = couponValue;
            }

            private String createTime;
            private List<AccessoryListBean> accessoryList;

            public String getAddressDetail() {
                return addressDetail;
            }

            public void setAddressDetail(String addressDetail) {
                this.addressDetail = addressDetail;
            }

            public String getAddressPhone() {
                return addressPhone;
            }

            public void setAddressPhone(String addressPhone) {
                this.addressPhone = addressPhone;
            }

            public String getAddressId() {
                return addressId;
            }

            public void setAddressId(String addressId) {
                this.addressId = addressId;
            }

            public double getGoodsPrice() {
                return goodsPrice;
            }

            public void setGoodsPrice(double goodsPrice) {
                this.goodsPrice = goodsPrice;
            }

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
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

            public String getPayStatus() {
                return payStatus;
            }

            public void setPayStatus(String payStatus) {
                this.payStatus = payStatus;
            }

            public String getOrderType() {
                return orderType;
            }

            public void setOrderType(String orderType) {
                this.orderType = orderType;
            }

            public double getOrderSum() {
                return orderSum;
            }

            public void setOrderSum(double orderSum) {
                this.orderSum = orderSum;
            }

            public double getRestMoney() {
                return restMoney;
            }

            public void setRestMoney(double restMoney) {
                this.restMoney = restMoney;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public List<AccessoryListBean> getAccessoryList() {
                return accessoryList;
            }

            public void setAccessoryList(List<AccessoryListBean> accessoryList) {
                this.accessoryList = accessoryList;
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
}
