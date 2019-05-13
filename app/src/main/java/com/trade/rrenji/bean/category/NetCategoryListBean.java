package com.trade.rrenji.bean.category;

import java.util.List;

public class NetCategoryListBean {


    /**
     * code : 0
     * msg : null
     * data : {"resultList":[{"goodsId":"25","goodsCode":"1510818476191","discoverImg":"http://qiniu.rrenji.com/FoyxDla9TdELwUa7S-l4Ap0H8a4W","goodsPrice":0.01,"originalPrice":6388,"goodsName":" iPhone 7 Plus 128G玫瑰金","subtitleList":["上新"],"conditionId":2,"version":"国行","memory":"128G","color":"玫瑰金　","newLog":2},{"goodsId":"26","goodsCode":"1510818908094","discoverImg":"http://qiniu.rrenji.com/FrX-gBL9bKJ5DJDLAkoqv09BS1SZ","goodsPrice":0.01,"originalPrice":6388,"goodsName":" iPhone 7 Plus 128G磨砂黑","subtitleList":["上新"],"conditionId":1,"version":"国行","memory":"128G","color":"黑色","newLog":3},{"goodsId":"27","goodsCode":"1510819007862","discoverImg":"http://qiniu.rrenji.com/FoyxDla9TdELwUa7S-l4Ap0H8a4W","goodsPrice":0.01,"originalPrice":6388,"goodsName":" iPhone 7 Plus 32G玫瑰金","subtitleList":["上新"],"conditionId":2,"version":"其他","memory":"32G","color":"玫瑰金　","newLog":2},{"goodsId":"28","goodsCode":"1510819096799","discoverImg":"http://qiniu.rrenji.com/FvivzNeUZ_N4tZvAfzA_oW8HWJLf","goodsPrice":0.01,"originalPrice":6388,"goodsName":" iPhone 7 Plus 128G金色","subtitleList":[],"conditionId":0,"version":"其他","memory":"128G","color":"金","newLog":1},{"goodsId":"29","goodsCode":"1510819249063","discoverImg":"http://qiniu.rrenji.com/FrX-gBL9bKJ5DJDLAkoqv09BS1SZ","goodsPrice":0.01,"originalPrice":6388,"goodsName":" iPhone 7 Plus 32G磨砂黑","subtitleList":["上新"],"conditionId":2,"version":"其他","memory":"32G","color":"黑色","newLog":2},{"goodsId":"82","goodsCode":"1521003336333","discoverImg":"http://qiniu.rrenji.com/FvNI__Xm5bo3UFx8-Rhr3iL5jSH5","goodsPrice":0.01,"originalPrice":6388,"goodsName":" iPhone7 plus 128G白色","subtitleList":["上新"],"conditionId":4,"version":"其他","memory":"128G","color":"银","newLog":4},{"goodsId":"83","goodsCode":"1521003461297","discoverImg":"http://qiniu.rrenji.com/FvNI__Xm5bo3UFx8-Rhr3iL5jSH5","goodsPrice":0.01,"originalPrice":6388,"goodsName":" iPhone7 plus 32G白色","subtitleList":["上新"],"conditionId":2,"version":"其他","memory":"32G","color":"银","newLog":5},{"goodsId":"84","goodsCode":"1521003532989","discoverImg":"http://qiniu.rrenji.com/FvivzNeUZ_N4tZvAfzA_oW8HWJLf","goodsPrice":0.01,"originalPrice":6388,"goodsName":" iPhone7 plus 32G金色","subtitleList":["上新"],"conditionId":2,"version":"其他","memory":"32G","color":"金","newLog":3}]}
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
             *  //注释：成色：0-无数据；1-99新；2-95新；3-9新；4-85新
             */
            /**
             * goodsId : 25
             * goodsCode : 1510818476191
             * discoverImg : http://qiniu.rrenji.com/FoyxDla9TdELwUa7S-l4Ap0H8a4W
             * goodsPrice : 0.01
             * originalPrice : 6388
             * goodsName :  iPhone 7 Plus 128G玫瑰金
             * subtitleList : ["上新"]
             * conditionId : 2
             * version : 国行
             * memory : 128G
             * color : 玫瑰金　
             * newLog : 2
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
