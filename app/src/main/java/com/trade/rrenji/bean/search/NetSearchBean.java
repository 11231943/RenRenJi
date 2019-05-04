package com.trade.rrenji.bean.search;

import java.util.List;

public class NetSearchBean {


    /**
     * code : 0
     * msg :
     * data : {"hotSearchList":["iphone","三星","小米 8","苹果6s"]}
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
        private List<String> hotSearchList;

        public List<String> getHotSearchList() {
            return hotSearchList;
        }

        public void setHotSearchList(List<String> hotSearchList) {
            this.hotSearchList = hotSearchList;
        }
    }
}
