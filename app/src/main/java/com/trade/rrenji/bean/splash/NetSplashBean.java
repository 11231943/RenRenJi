package com.trade.rrenji.bean.splash;

public class NetSplashBean {


    /**
     * code : 0
     * msg : null
     * data : {"imgUrl":"http://qiniu.rrenji.com/FgxTgxu2lOGhuqZ1laxcq_tfiLx5","isSkip":"0","startTime":"2019-04-15 20:04:38","countDown":"3","endTime":"2019-04-26 20:04:40"}
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
         * imgUrl : http://qiniu.rrenji.com/FgxTgxu2lOGhuqZ1laxcq_tfiLx5
         * isSkip : 0
         * startTime : 2019-04-15 20:04:38
         * countDown : 3
         * endTime : 2019-04-26 20:04:40
         */

        private String imgUrl;
        private String isSkip;
        private String startTime;
        private String countDown;
        private String endTime;

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getIsSkip() {
            return isSkip;
        }

        public void setIsSkip(String isSkip) {
            this.isSkip = isSkip;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getCountDown() {
            return countDown;
        }

        public void setCountDown(String countDown) {
            this.countDown = countDown;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }
    }
}
