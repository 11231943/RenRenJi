package com.trade.rrenji.bean.collection;

import java.util.List;

public class NetCollectionListBean {


    /**
     * code : 0
     * msg : SUCCESS
     * result : {"favoriteProductList":[{"id":1,"goodsCode":"PG00001","title":"苹果64G","salePrice":5500,"originalPrice":"6500","tags":null,"pics":[{"maxImg":"http://gw.alicdn.com/bao/uploaded/TB1S9cmOFXXXXboXFXXSutbFXXX.jpg_q90","minImg":"http://gw.alicdn.com/bao/uploaded/TB1S9cmOFXXXXboXFXXSutbFXXX.jpg_q90"}]}],"pageInfo":{"currentPage":1,"totalPage":0,"pageSize":10,"totalRow":0}}
     */

    private String code;
    private String msg;
    private ResultBean result;

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

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * favoriteProductList : [{"id":1,"goodsCode":"PG00001","title":"苹果64G","salePrice":5500,"originalPrice":"6500","tags":null,"pics":[{"maxImg":"http://gw.alicdn.com/bao/uploaded/TB1S9cmOFXXXXboXFXXSutbFXXX.jpg_q90","minImg":"http://gw.alicdn.com/bao/uploaded/TB1S9cmOFXXXXboXFXXSutbFXXX.jpg_q90"}]}]
         * pageInfo : {"currentPage":1,"totalPage":0,"pageSize":10,"totalRow":0}
         */

        private PageInfoBean pageInfo;
        private List<FavoriteProductListBean> favoriteProductList;

        public PageInfoBean getPageInfo() {
            return pageInfo;
        }

        public void setPageInfo(PageInfoBean pageInfo) {
            this.pageInfo = pageInfo;
        }

        public List<FavoriteProductListBean> getFavoriteProductList() {
            return favoriteProductList;
        }

        public void setFavoriteProductList(List<FavoriteProductListBean> favoriteProductList) {
            this.favoriteProductList = favoriteProductList;
        }

        public static class PageInfoBean {
            /**
             * currentPage : 1
             * totalPage : 0
             * pageSize : 10
             * totalRow : 0
             */

            private int currentPage;
            private int totalPage;
            private int pageSize;
            private int totalRow;

            public int getCurrentPage() {
                return currentPage;
            }

            public void setCurrentPage(int currentPage) {
                this.currentPage = currentPage;
            }

            public int getTotalPage() {
                return totalPage;
            }

            public void setTotalPage(int totalPage) {
                this.totalPage = totalPage;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public int getTotalRow() {
                return totalRow;
            }

            public void setTotalRow(int totalRow) {
                this.totalRow = totalRow;
            }
        }

        public static class FavoriteProductListBean {
            /**
             * id : 1
             * goodsCode : PG00001
             * title : 苹果64G
             * salePrice : 5500
             * originalPrice : 6500
             * tags : null
             * pics : [{"maxImg":"http://gw.alicdn.com/bao/uploaded/TB1S9cmOFXXXXboXFXXSutbFXXX.jpg_q90","minImg":"http://gw.alicdn.com/bao/uploaded/TB1S9cmOFXXXXboXFXXSutbFXXX.jpg_q90"}]
             */

            private int id;
            private String goodsCode;
            private String title;
            private int salePrice;
            private String originalPrice;
            private Object tags;
            private List<PicsBean> pics;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getGoodsCode() {
                return goodsCode;
            }

            public void setGoodsCode(String goodsCode) {
                this.goodsCode = goodsCode;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getSalePrice() {
                return salePrice;
            }

            public void setSalePrice(int salePrice) {
                this.salePrice = salePrice;
            }

            public String getOriginalPrice() {
                return originalPrice;
            }

            public void setOriginalPrice(String originalPrice) {
                this.originalPrice = originalPrice;
            }

            public Object getTags() {
                return tags;
            }

            public void setTags(Object tags) {
                this.tags = tags;
            }

            public List<PicsBean> getPics() {
                return pics;
            }

            public void setPics(List<PicsBean> pics) {
                this.pics = pics;
            }

            public static class PicsBean {
                /**
                 * maxImg : http://gw.alicdn.com/bao/uploaded/TB1S9cmOFXXXXboXFXXSutbFXXX.jpg_q90
                 * minImg : http://gw.alicdn.com/bao/uploaded/TB1S9cmOFXXXXboXFXXSutbFXXX.jpg_q90
                 */

                private String maxImg;
                private String minImg;

                public String getMaxImg() {
                    return maxImg;
                }

                public void setMaxImg(String maxImg) {
                    this.maxImg = maxImg;
                }

                public String getMinImg() {
                    return minImg;
                }

                public void setMinImg(String minImg) {
                    this.minImg = minImg;
                }
            }
        }
    }
}
