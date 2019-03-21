package com.trade.rrenji.bean.home;

import java.util.List;

public class NetHotDataBean {


    /**
     * code : 0
     * msg : null
     * data : {"resultList":[{"goodsId":"42","goodsCode":"1512372246441","discoverImg":null,"goodsPrice":0.01,"originalPrice":5888,"goodsName":" iPhone8 64G 深空灰","subtitleList":[],"conditionId":0,"version":"国行","memory":"64G","color":"深空灰","newLog":1},{"goodsId":"44","goodsCode":"1512566100124","discoverImg":null,"goodsPrice":0.01,"originalPrice":3499,"goodsName":" 一加 5 128G 月岩灰","subtitleList":[],"conditionId":0,"version":"国行","memory":"128G","color":"黑色","newLog":1},{"goodsId":"43","goodsCode":"1512565733859","discoverImg":null,"goodsPrice":0.01,"originalPrice":2900,"goodsName":" 一加 5 64G 月岩灰","subtitleList":[],"conditionId":0,"version":"国行","memory":"64G","color":"黑色","newLog":1}]}
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

        public static class ResultListBean {
            /**
             * goodsId : 42
             * goodsCode : 1512372246441
             * discoverImg : null
             * goodsPrice : 0.01
             * originalPrice : 5888
             * goodsName :  iPhone8 64G 深空灰
             * subtitleList : []
             * conditionId : 0
             * version : 国行
             * memory : 64G
             * color : 深空灰
             * newLog : 1
             */

            private String goodsId;
            private String goodsCode;
            private String discoverImg;
            private double goodsPrice;
            private int originalPrice;
            private String goodsName;
            private int conditionId;
            private String version;
            private String memory;
            private String color;
            private int newLog;
            private List<String> subtitleList;

            public String getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(String goodsId) {
                this.goodsId = goodsId;
            }

            public String getGoodsCode() {
                return goodsCode;
            }

            public void setGoodsCode(String goodsCode) {
                this.goodsCode = goodsCode;
            }

            public String getDiscoverImg() {
                return discoverImg;
            }

            public void setDiscoverImg(String discoverImg) {
                this.discoverImg = discoverImg;
            }

            public double getGoodsPrice() {
                return goodsPrice;
            }

            public void setGoodsPrice(double goodsPrice) {
                this.goodsPrice = goodsPrice;
            }

            public int getOriginalPrice() {
                return originalPrice;
            }

            public void setOriginalPrice(int originalPrice) {
                this.originalPrice = originalPrice;
            }

            public String getGoodsName() {
                return goodsName;
            }

            public void setGoodsName(String goodsName) {
                this.goodsName = goodsName;
            }

            public int getConditionId() {
                return conditionId;
            }

            public void setConditionId(int conditionId) {
                this.conditionId = conditionId;
            }

            public String getVersion() {
                return version;
            }

            public void setVersion(String version) {
                this.version = version;
            }

            public String getMemory() {
                return memory;
            }

            public void setMemory(String memory) {
                this.memory = memory;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }

            public int getNewLog() {
                return newLog;
            }

            public void setNewLog(int newLog) {
                this.newLog = newLog;
            }

            public List<String> getSubtitleList() {
                return subtitleList;
            }

            public void setSubtitleList(List<String> subtitleList) {
                this.subtitleList = subtitleList;
            }
        }
    }
}
