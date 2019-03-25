package com.trade.rrenji.bean.upload;

public class NetUploadBean {


    /**
     * code : 0
     * msg : SUCCESS
     * result : {"url":"http://owptno2wx.bkt.clouddn.com/FviU0H-wBWEmsteRC_ESE1mXDmFI"}
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
         * url : http://owptno2wx.bkt.clouddn.com/FviU0H-wBWEmsteRC_ESE1mXDmFI
         */

        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
