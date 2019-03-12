package com.trade.rrenji.bean.order;

import java.util.List;

public class CreateOrderBean {

    /**
     * {
     *     "payType":"6",    // 1-微信；2-支付宝【包含花呗】；3-京东【包含京东白条】；4-蚂蚁花呗分期支付；5-京东白条分期支付；6-组合支付
     *     "plan":"3",    // 分期的期数
     *     "addressId": "59",    // 地址ID
     *     "goodsId": "26",     // 商品ID
     *     "goodsCount": 1,    // 商品数量
     *     "couponId": "",    // 优惠券ID
     *     "userOrderMessage": "",    // 用户下单留言
     *     "extraServer": 1,    //
     *     "total": "1899",    // 总价
     *     "expressType": 1,    // 快递类型，1表示顺丰
     *     "accessories": [    // 配件信息
     *         {
     *             "accessoryId": 1,    // 配件id
     *             "count": 1,    // 配件数量
     *             "payPrice": 899    // 配件价格
     *         }
     *     ],
     *     "orderGroupPayList":[    // 组合支付集合
     *         {
     *             "payType":"2",    // 支付类型：1-微信；2-支付宝；3-京东；
     *             "money":1800    // 金额
     *         },
     *         {
     *             "payType":"3",
     *             "money":99
     *         }
     *     ]
     * }
     */

    /**
     * payType : 6
     * plan : 3
     * addressId : 59
     * goodsId : 26
     * goodsCount : 1
     * couponId :
     * userOrderMessage :
     * extraServer : 1
     * total : 1899
     * expressType : 1
     * accessories : [{"accessoryId":1,"count":1,"payPrice":899}]
     * orderGroupPayList : [{"payType":"2","money":1800},{"payType":"3","money":99}]
     */

    private String payType;
    private String plan;
    private String addressId;
    private String goodsId;
    private int goodsCount;
    private String couponId;
    private String userOrderMessage;
    private int extraServer;
    private String total;
    private int expressType;
    private List<AccessoriesBean> accessories;
    private List<OrderGroupPayListBean> orderGroupPayList;

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public int getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(int goodsCount) {
        this.goodsCount = goodsCount;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getUserOrderMessage() {
        return userOrderMessage;
    }

    public void setUserOrderMessage(String userOrderMessage) {
        this.userOrderMessage = userOrderMessage;
    }

    public int getExtraServer() {
        return extraServer;
    }

    public void setExtraServer(int extraServer) {
        this.extraServer = extraServer;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public int getExpressType() {
        return expressType;
    }

    public void setExpressType(int expressType) {
        this.expressType = expressType;
    }

    public List<AccessoriesBean> getAccessories() {
        return accessories;
    }

    public void setAccessories(List<AccessoriesBean> accessories) {
        this.accessories = accessories;
    }

    public List<OrderGroupPayListBean> getOrderGroupPayList() {
        return orderGroupPayList;
    }

    public void setOrderGroupPayList(List<OrderGroupPayListBean> orderGroupPayList) {
        this.orderGroupPayList = orderGroupPayList;
    }

    public static class AccessoriesBean {
        /**
         * accessoryId : 1
         * count : 1
         * payPrice : 899
         */

        private int accessoryId;
        private int count;
        private int payPrice;

        public int getAccessoryId() {
            return accessoryId;
        }

        public void setAccessoryId(int accessoryId) {
            this.accessoryId = accessoryId;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getPayPrice() {
            return payPrice;
        }

        public void setPayPrice(int payPrice) {
            this.payPrice = payPrice;
        }
    }

    public static class OrderGroupPayListBean {
        /**
         * payType : 2
         * money : 1800
         */

        private String payType;

        private int money;

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
    }

    @Override
    public String toString() {
        return "CreateOrderBean{" +
                "payType='" + payType + '\'' +
                ", plan='" + plan + '\'' +
                ", addressId='" + addressId + '\'' +
                ", goodsId='" + goodsId + '\'' +
                ", goodsCount=" + goodsCount +
                ", couponId='" + couponId + '\'' +
                ", userOrderMessage='" + userOrderMessage + '\'' +
                ", extraServer=" + extraServer +
                ", total='" + total + '\'' +
                ", expressType=" + expressType +
                ", accessories=" + accessories +
                ", orderGroupPayList=" + orderGroupPayList +
                '}';
    }
}
