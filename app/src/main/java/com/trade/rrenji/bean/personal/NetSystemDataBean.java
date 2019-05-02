package com.trade.rrenji.bean.personal;

public class NetSystemDataBean {

    /**
     * code : 0
     * msg : null
     * data : {"kefu_wechat":"renrenji","rrj_jiaoliuqun":"hhttps://org.modao.cc/app/gzDhkngtxiHkG4NYDc8YG2A0ZavHQZW#screen=sba4bb5fb5e0de3c83f0300","kefu_email":"renrenji@163.com","kefu_QQ":"123456789","rrj_pingtaiguize":"hhttps://org.modao.cc/app/gzDhkngtxiHkG4NYDc8YG2A0ZavHQZW#screen=sba4bb5fb5e0de3c83f0300","rrj_zhaoshang":"hhttps://org.modao.cc/app/gzDhkngtxiHkG4NYDc8YG2A0ZavHQZW#screen=sba4bb5fb5e0de3c83f0300"}
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
        /**
         * kefu_wechat : renrenji
         * rrj_jiaoliuqun : hhttps://org.modao.cc/app/gzDhkngtxiHkG4NYDc8YG2A0ZavHQZW#screen=sba4bb5fb5e0de3c83f0300
         * kefu_email : renrenji@163.com
         * kefu_QQ : 123456789
         * rrj_pingtaiguize : hhttps://org.modao.cc/app/gzDhkngtxiHkG4NYDc8YG2A0ZavHQZW#screen=sba4bb5fb5e0de3c83f0300
         * rrj_zhaoshang : hhttps://org.modao.cc/app/gzDhkngtxiHkG4NYDc8YG2A0ZavHQZW#screen=sba4bb5fb5e0de3c83f0300
         */

        private String kefu_wechat;
        private String rrj_jiaoliuqun;
        private String kefu_email;
        private String kefu_QQ;
        private String rrj_pingtaiguize;
        private String rrj_zhaoshang;

        public String getKefu_wechat() {
            return kefu_wechat;
        }

        public void setKefu_wechat(String kefu_wechat) {
            this.kefu_wechat = kefu_wechat;
        }

        public String getRrj_jiaoliuqun() {
            return rrj_jiaoliuqun;
        }

        public void setRrj_jiaoliuqun(String rrj_jiaoliuqun) {
            this.rrj_jiaoliuqun = rrj_jiaoliuqun;
        }

        public String getKefu_email() {
            return kefu_email;
        }

        public void setKefu_email(String kefu_email) {
            this.kefu_email = kefu_email;
        }

        public String getKefu_QQ() {
            return kefu_QQ;
        }

        public void setKefu_QQ(String kefu_QQ) {
            this.kefu_QQ = kefu_QQ;
        }

        public String getRrj_pingtaiguize() {
            return rrj_pingtaiguize;
        }

        public void setRrj_pingtaiguize(String rrj_pingtaiguize) {
            this.rrj_pingtaiguize = rrj_pingtaiguize;
        }

        public String getRrj_zhaoshang() {
            return rrj_zhaoshang;
        }

        public void setRrj_zhaoshang(String rrj_zhaoshang) {
            this.rrj_zhaoshang = rrj_zhaoshang;
        }
    }
}
