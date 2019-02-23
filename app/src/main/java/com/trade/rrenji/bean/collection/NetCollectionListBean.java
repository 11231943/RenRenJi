package com.trade.rrenji.bean.collection;

import java.util.List;

public class NetCollectionListBean {


    /**
     * code : 0
     * msg : SUCCESS
     * result : [{"id":"8","goodsName":" 三星 S7 Edge 64G白色","goodsPrice":0.01,"favoritePrice":2450,"price":2449.99,"goodsCode":"1522235560110","goodsImg":"http://qiniu.rrenji.com/Fm054Fbe8LY_RcqyA09BdBU_Zp1i","userId":"455"},{"id":"7","goodsName":"iPhone8 256G 白色","goodsPrice":0.01,"favoritePrice":4499,"price":4498.99,"goodsCode":"1521435825157","goodsImg":"http://qiniu.rrenji.com/FsTt4G_xpjIWS-tLO4v5ZbOY7onL","userId":"455"},{"id":"6","goodsName":" iPhone 7 32G黑色","goodsPrice":0.01,"favoritePrice":2299,"price":2298.99,"goodsCode":"1516348991814","goodsImg":"http://qiniu.rrenji.com/Fg7CZUoZ0dzBjvu9wVn0aBELf6TZ","userId":"455"},{"id":"5","goodsName":" iPhone 7 Plus 32G磨砂黑","goodsPrice":0.01,"favoritePrice":2999,"price":2998.99,"goodsCode":"1510819249063","goodsImg":"http://qiniu.rrenji.com/FrX-gBL9bKJ5DJDLAkoqv09BS1SZ","userId":"455"}]
     */

    private String code;
    private String msg;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 8
         * goodsName :  三星 S7 Edge 64G白色
         * goodsPrice : 0.01
         * favoritePrice : 2450
         * price : 2449.99
         * goodsCode : 1522235560110
         * goodsImg : http://qiniu.rrenji.com/Fm054Fbe8LY_RcqyA09BdBU_Zp1i
         * userId : 455
         */

        private String id;
        private String goodsName;
        private double goodsPrice;
        private int favoritePrice;
        private double price;
        private String goodsCode;
        private String goodsImg;
        private String userId;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public double getGoodsPrice() {
            return goodsPrice;
        }

        public void setGoodsPrice(double goodsPrice) {
            this.goodsPrice = goodsPrice;
        }

        public int getFavoritePrice() {
            return favoritePrice;
        }

        public void setFavoritePrice(int favoritePrice) {
            this.favoritePrice = favoritePrice;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getGoodsCode() {
            return goodsCode;
        }

        public void setGoodsCode(String goodsCode) {
            this.goodsCode = goodsCode;
        }

        public String getGoodsImg() {
            return goodsImg;
        }

        public void setGoodsImg(String goodsImg) {
            this.goodsImg = goodsImg;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}
