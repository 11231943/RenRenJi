package com.trade.rrenji.bean.collection;

import java.util.List;

public class NetCollectionListBean {


    /**
     * code : 0
     * msg : SUCCESS
     * result : [{"id":"8","goodsName":" 三星 S7 Edge 64G白色","goodsPrice":0.01,"favoritePrice":2450,"price":2449.99,"goodsCode":"1522235560110","goodsImg":"http://qiniu.rrenji.com/Fm054Fbe8LY_RcqyA09BdBU_Zp1i","userId":"455"},{"id":"6","goodsName":" iPhone 7 32G黑色","goodsPrice":0.01,"favoritePrice":2299,"price":2298.99,"goodsCode":"1516348991814","goodsImg":"http://qiniu.rrenji.com/Fg7CZUoZ0dzBjvu9wVn0aBELf6TZ","userId":"455"},{"id":"9","goodsName":" 三星 S7 Edge 32G金色","goodsPrice":0.01,"favoritePrice":2150,"price":2149.99,"goodsCode":"1522235636532","goodsImg":"http://qiniu.rrenji.com/FmcS2h-2zG164vPddy4x_II5wA9F","userId":"455"},{"id":"29","goodsName":" iPhone X 64G黑色","goodsPrice":0.01,"favoritePrice":0.01,"price":0,"goodsCode":"1521429526536","goodsImg":"http://qiniu.rrenji.com/FsoAHk5oydaNzAZ1PCY2aPUlGF7h","userId":"455"},{"id":"28","goodsName":" iPhone7 plus 128G白色","goodsPrice":0.01,"favoritePrice":0.01,"price":0,"goodsCode":"1521003336333","goodsImg":"http://qiniu.rrenji.com/FvNI__Xm5bo3UFx8-Rhr3iL5jSH5","userId":"455"},{"id":"27","goodsName":" iPhone7 plus 32G白色","goodsPrice":0.01,"favoritePrice":0.01,"price":0,"goodsCode":"1521003461297","goodsImg":"http://qiniu.rrenji.com/FvNI__Xm5bo3UFx8-Rhr3iL5jSH5","userId":"455"},{"id":"26","goodsName":" iPhone7 plus 32G金色","goodsPrice":0.01,"favoritePrice":0.01,"price":0,"goodsCode":"1521003532989","goodsImg":"http://qiniu.rrenji.com/FvivzNeUZ_N4tZvAfzA_oW8HWJLf","userId":"455"},{"id":"25","goodsName":" 三星 S7 Edge 64G金色","goodsPrice":0.01,"favoritePrice":0.01,"price":0,"goodsCode":"1522235687532","goodsImg":"http://qiniu.rrenji.com/FmcS2h-2zG164vPddy4x_II5wA9F","userId":"455"},{"id":"24","goodsName":" iPhone 7 32G金色","goodsPrice":0.01,"favoritePrice":0.01,"price":0,"goodsCode":"1521604930034","goodsImg":"http://qiniu.rrenji.com/FnH9R8VK4Tq4s6DZQVtfrl_ElYW9","userId":"455"},{"id":"23","goodsName":" iPhone 7 Plus 32G磨砂黑","goodsPrice":0.01,"favoritePrice":0.01,"price":0,"goodsCode":"1510819249063","goodsImg":"http://qiniu.rrenji.com/FrX-gBL9bKJ5DJDLAkoqv09BS1SZ","userId":"455"},{"id":"22","goodsName":" OPPO R9S 金","goodsPrice":0.01,"favoritePrice":0.01,"price":0,"goodsCode":"1510820154120","goodsImg":"http://qiniu.rrenji.com/Frupx3QilrgTrJnN1k6TzXlfUkFd","userId":"455"},{"id":"21","goodsName":" ViVO X9 Plus 金色","goodsPrice":0.01,"favoritePrice":0.01,"price":0,"goodsCode":"1510819953870","goodsImg":"http://qiniu.rrenji.com/FsDZcHz0N92w4GX9EaTLUXfEuf6X","userId":"455"},{"id":"19","goodsName":" ViVO X9 Plus 粉色","goodsPrice":0.01,"favoritePrice":0.01,"price":0,"goodsCode":"1510820071069","goodsImg":"http://qiniu.rrenji.com/FkX6_SOQf1EZX-MXpAHOIHsjOwAz","userId":"455"},{"id":"13","goodsName":"iPhone8 64G金色","goodsPrice":0.01,"favoritePrice":0.01,"price":0,"goodsCode":"1521436303088","goodsImg":"http://qiniu.rrenji.com/Fq-XHbi1ckENwyewRf3kTNiega5g","userId":"455"},{"id":"12","goodsName":" iPad Air 32G黑色","goodsPrice":0.01,"favoritePrice":0.01,"price":0,"goodsCode":"1522316023981","goodsImg":"http://qiniu.rrenji.com/FurpqVC8WFJSptaDXyuNIDuPkbla","userId":"455"},{"id":"11","goodsName":" 三星 S7 Edge 32G白色","goodsPrice":0.01,"favoritePrice":0.01,"price":0,"goodsCode":"1522235484289","goodsImg":"http://qiniu.rrenji.com/Fm054Fbe8LY_RcqyA09BdBU_Zp1i","userId":"455"},{"id":"10","goodsName":" 魅族 note6 32G 金色","goodsPrice":2000.01,"favoritePrice":2000.01,"price":0,"goodsCode":"1524639168210","goodsImg":"http:\\/\\/rrj.rhole.com/FgXLegjsrmyS2qUhKq9zf1sKCQ7-","userId":"455"}]
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
        private double favoritePrice;
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

        public double getFavoritePrice() {
            return favoritePrice;
        }

        public void setFavoritePrice(double favoritePrice) {
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
