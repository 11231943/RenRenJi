package com.trade.rrenji.bean.category;


import java.util.List;

public class NetScreenListBean {


    /**
     * code : 0
     * msg : SUCCESS
     * result : {"pageInfo":{"currentPage":1,"totalPage":1,"pageSize":10,"totalRow":4},"productList":[{"goodsId":"27","goodsCode":"1510819007862","title":"iPhone7 Plus 32G","network":"移动4G联通4G电信4G","picUrl":"http://qiniu.rrenji.com/FoyxDla9TdELwUa7S-l4Ap0H8a4W","price":"0.01","originalPrice":"6388.00","conditionId":2,"newLog":2,"subtitleList":["上新"]},{"goodsId":"83","goodsCode":"1521003461297","title":"iPhone7 Plus 32G","network":"移动4G联通4G电信4G","picUrl":"http://qiniu.rrenji.com/FvNI__Xm5bo3UFx8-Rhr3iL5jSH5","price":"0.01","originalPrice":"6388.00","conditionId":2,"newLog":5,"subtitleList":["上新"]},{"goodsId":"84","goodsCode":"1521003532989","title":"iPhone7 Plus 32G","network":"移动4G联通4G电信4G","picUrl":"http://qiniu.rrenji.com/FvivzNeUZ_N4tZvAfzA_oW8HWJLf","price":"0.01","originalPrice":"6388.00","conditionId":2,"newLog":3,"subtitleList":["上新"]},{"goodsId":"29","goodsCode":"1510819249063","title":"iPhone7 Plus 32G","network":"移动4G联通4G电信4G","picUrl":"http://qiniu.rrenji.com/FrX-gBL9bKJ5DJDLAkoqv09BS1SZ","price":"0.01","originalPrice":"6388.00","conditionId":2,"newLog":2,"subtitleList":["上新"]}]}
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
         * pageInfo : {"currentPage":1,"totalPage":1,"pageSize":10,"totalRow":4}
         * productList : [{"goodsId":"27","goodsCode":"1510819007862","title":"iPhone7 Plus 32G","network":"移动4G联通4G电信4G","picUrl":"http://qiniu.rrenji.com/FoyxDla9TdELwUa7S-l4Ap0H8a4W","price":"0.01","originalPrice":"6388.00","conditionId":2,"newLog":2,"subtitleList":["上新"]},{"goodsId":"83","goodsCode":"1521003461297","title":"iPhone7 Plus 32G","network":"移动4G联通4G电信4G","picUrl":"http://qiniu.rrenji.com/FvNI__Xm5bo3UFx8-Rhr3iL5jSH5","price":"0.01","originalPrice":"6388.00","conditionId":2,"newLog":5,"subtitleList":["上新"]},{"goodsId":"84","goodsCode":"1521003532989","title":"iPhone7 Plus 32G","network":"移动4G联通4G电信4G","picUrl":"http://qiniu.rrenji.com/FvivzNeUZ_N4tZvAfzA_oW8HWJLf","price":"0.01","originalPrice":"6388.00","conditionId":2,"newLog":3,"subtitleList":["上新"]},{"goodsId":"29","goodsCode":"1510819249063","title":"iPhone7 Plus 32G","network":"移动4G联通4G电信4G","picUrl":"http://qiniu.rrenji.com/FrX-gBL9bKJ5DJDLAkoqv09BS1SZ","price":"0.01","originalPrice":"6388.00","conditionId":2,"newLog":2,"subtitleList":["上新"]}]
         */

        private PageInfoBean pageInfo;
        private List<NetCategoryListBean.DataBean.ResultListBean> productList;

        public PageInfoBean getPageInfo() {
            return pageInfo;
        }

        public void setPageInfo(PageInfoBean pageInfo) {
            this.pageInfo = pageInfo;
        }

        public List<NetCategoryListBean.DataBean.ResultListBean> getProductList() {
            return productList;
        }

        public void setProductList(List<NetCategoryListBean.DataBean.ResultListBean> productList) {
            this.productList = productList;
        }

        public static class PageInfoBean {
            /**
             * currentPage : 1
             * totalPage : 1
             * pageSize : 10
             * totalRow : 4
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


    }
}
