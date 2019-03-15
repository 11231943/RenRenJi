package com.trade.rrenji.bean.order;

import java.io.Serializable;
import java.util.List;

public class ContinuePayBean implements Serializable {
    private String orderId;
    private String orderType;
    private String payType;
    private List<CreateOrderBean.OrderGroupPayListBean> orderGroupPayList;


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public void setOrderGroupPayList(List<CreateOrderBean.OrderGroupPayListBean> orderGroupPayList) {
        this.orderGroupPayList = orderGroupPayList;
    }

    public List<CreateOrderBean.OrderGroupPayListBean> getOrderGroupPayList() {
        return orderGroupPayList;
    }

}
