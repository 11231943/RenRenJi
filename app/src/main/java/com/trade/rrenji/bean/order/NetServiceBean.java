package com.trade.rrenji.bean.order;

import java.util.List;

public class NetServiceBean {

    /**
     * code : 0
     * msg : null
     * data : {"reList":[{"operate":"0","coverImg":"http://qiniu.rrenji.com/Fg7CZUoZ0dzBjvu9wVn0aBELf6TZ","createTime":"2019-04-15 12:01:11","orderId":"","statsMsg":"","goodsCode":"1516348991814","id":155525407120149117,"goodsName":" iPhone 7 32G黑色","remainingTime":""},{"operate":"0","coverImg":"http://qiniu.rrenji.com/FrX-gBL9bKJ5DJDLAkoqv09BS1SZ","createTime":"2019-03-13 09:55:28","orderId":"","statsMsg":"","goodsCode":"1510819249063","id":155239532888937574,"goodsName":" iPhone 7 Plus 32G磨砂黑","remainingTime":""}]}
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
        private List<ReListBean> reList;

        public List<ReListBean> getReList() {
            return reList;
        }

        public void setReList(List<ReListBean> reList) {
            this.reList = reList;
        }

        public static class ReListBean {
            /**
             * operate : 0
             * coverImg : http://qiniu.rrenji.com/Fg7CZUoZ0dzBjvu9wVn0aBELf6TZ
             * createTime : 2019-04-15 12:01:11
             * orderId :
             * statsMsg :
             * goodsCode : 1516348991814
             * id : 155525407120149117
             * goodsName :  iPhone 7 32G黑色
             * remainingTime :
             */

            private String operate;
            private String coverImg;
            private String createTime;
            private String orderId;
            private String statsMsg;
            private String goodsCode;
            private long id;
            private String goodsName;
            private String remainingTime;

            public String getOperate() {
                return operate;
            }

            public void setOperate(String operate) {
                this.operate = operate;
            }

            public String getCoverImg() {
                return coverImg;
            }

            public void setCoverImg(String coverImg) {
                this.coverImg = coverImg;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
            }

            public String getStatsMsg() {
                return statsMsg;
            }

            public void setStatsMsg(String statsMsg) {
                this.statsMsg = statsMsg;
            }

            public String getGoodsCode() {
                return goodsCode;
            }

            public void setGoodsCode(String goodsCode) {
                this.goodsCode = goodsCode;
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public String getGoodsName() {
                return goodsName;
            }

            public void setGoodsName(String goodsName) {
                this.goodsName = goodsName;
            }

            public String getRemainingTime() {
                return remainingTime;
            }

            public void setRemainingTime(String remainingTime) {
                this.remainingTime = remainingTime;
            }
        }
    }
}
