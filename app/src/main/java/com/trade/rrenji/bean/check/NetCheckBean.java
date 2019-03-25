package com.trade.rrenji.bean.check;

public class NetCheckBean {

    /**
     * code : 0
     * msg : SUCCESS
     * result : {"needUpdate":true,"force":true,"msg":"1､更新\n2､更新","apkUrl":"http://www.baidu.com"}
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
         * needUpdate : true
         * force : true
         * msg : 1､更新
         2､更新
         * apkUrl : http://www.baidu.com
         */

        private boolean needUpdate;
        private boolean force;
        private String msg;
        private String apkUrl;

        public boolean isNeedUpdate() {
            return needUpdate;
        }

        public void setNeedUpdate(boolean needUpdate) {
            this.needUpdate = needUpdate;
        }

        public boolean isForce() {
            return force;
        }

        public void setForce(boolean force) {
            this.force = force;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getApkUrl() {
            return apkUrl;
        }

        public void setApkUrl(String apkUrl) {
            this.apkUrl = apkUrl;
        }
    }
}
