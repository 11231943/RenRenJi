package com.trade.rrenji.bean.order;

public class LocalOrderInfoBean {

    private String img;
    private String orderId;
    private String goodsName;
    private double payPrice;
    private String createTime;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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

    public double getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(double payPrice) {
        this.payPrice = payPrice;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "LocalOrderInfoBean{" +
                "img='" + img + '\'' +
                ", orderId='" + orderId + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", payPrice=" + payPrice +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
