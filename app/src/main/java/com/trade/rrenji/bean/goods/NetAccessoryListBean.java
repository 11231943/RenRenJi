package com.trade.rrenji.bean.goods;

import java.util.List;

public class NetAccessoryListBean {


    /**
     * code : 0
     * msg : null
     * data : {"resultList":[{"accessoryId":5,"accessoryName":"原装数据线","price":49,"url":"http://qiniu.rrenji.com/FqFlal9nbSFIjMGBcps_LKD5K6Wq"},{"accessoryId":4,"accessoryName":"原装耳机（3.5mm）","price":88,"url":"http://qiniu.rrenji.com/Fq6xQaSGcqW-mea1VskStw3NoCKd"},{"accessoryId":3,"accessoryName":"原装5W充电器","price":49,"url":"http://qiniu.rrenji.com/FuND6jhQfwIrBWknheA7_lYoGMWz"},{"accessoryId":2,"accessoryName":"耳机音频转接头","price":69,"url":"http://qiniu.rrenji.com/FtmBGO61CsHpv1Z0lRF2q_lE1DKV"}]}
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
             * accessoryId : 5
             * accessoryName : 原装数据线
             * price : 49
             * url : http://qiniu.rrenji.com/FqFlal9nbSFIjMGBcps_LKD5K6Wq
             */

            private int accessoryId;
            private String accessoryName;
            private int price;
            private String url;

            public int getAccessoryId() {
                return accessoryId;
            }

            public void setAccessoryId(int accessoryId) {
                this.accessoryId = accessoryId;
            }

            public String getAccessoryName() {
                return accessoryName;
            }

            public void setAccessoryName(String accessoryName) {
                this.accessoryName = accessoryName;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
