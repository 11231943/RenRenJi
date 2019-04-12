package com.trade.rrenji.bean.goods;

import java.util.List;

public class RecyCommentBean {


    /**
     * code : 0
     * msg : SUCCESS
     * result : {"pageInfo":{"currentPage":1,"totalPage":0,"pageSize":10,"totalRow":0},"evaluatelist":[{"evaluateId":61,"evaluateTitle":"你好，是我","shareTime":"2018-11-15T00:37:21.000+08:00","userName":"158****0821","evaluateDesc":"dsgsdfg","locationCity":null,"userImg":null,"sharePictures":[{"minPic":"[http:\\/\\/rrj.rhole.com/FgXLegjsrmyS2qUhKq9zf1sKCQ7-]?imageMogr2/thumbnail/!300x300r","maxPic":"[http:\\/\\/rrj.rhole.com/FgXLegjsrmyS2qUhKq9zf1sKCQ7-]"}]},{"evaluateId":60,"evaluateTitle":"123","shareTime":"2018-11-15T00:36:27.000+08:00","userName":"158****0821","evaluateDesc":"asdfasdfa","locationCity":null,"userImg":null,"sharePictures":[{"minPic":"[http:\\/\\/rrj.rhole.com/FgXLegjsrmyS2qUhKq9zf1sKCQ7-]?imageMogr2/thumbnail/!300x300r","maxPic":"[http:\\/\\/rrj.rhole.com/FgXLegjsrmyS2qUhKq9zf1sKCQ7-]"}]},{"evaluateId":59,"evaluateTitle":"123","shareTime":"2018-11-15T00:27:09.000+08:00","userName":"158****0821","evaluateDesc":"asdfasdfa","locationCity":null,"userImg":null,"sharePictures":null},{"evaluateId":51,"evaluateTitle":" iPhone 7 Plus 128G磨砂黑","shareTime":"2018-06-27T21:46:36.000+08:00","userName":"151****8876","evaluateDesc":"阿施","locationCity":null,"userImg":null,"sharePictures":[{"minPic":"http://qiniu.rrenji.com/ad43ac5f-1f0b-4ac1-8666-c861be421aa8_jpeg?imageMogr2/thumbnail/!300x300r","maxPic":"http://qiniu.rrenji.com/ad43ac5f-1f0b-4ac1-8666-c861be421aa8_jpeg"},{"minPic":"http://qiniu.rrenji.com/48c04bcc-5980-4806-80b7-611cd5a7fb25_jpeg?imageMogr2/thumbnail/!300x300r","maxPic":"http://qiniu.rrenji.com/48c04bcc-5980-4806-80b7-611cd5a7fb25_jpeg"}]},{"evaluateId":35,"evaluateTitle":"95新 iPhone7 plus 32G白色","shareTime":"2018-04-22T23:12:57.000+08:00","userName":"178****0064","evaluateDesc":"手机已拿到，银色32g7p，成色不错，基本全新，经过pp助手还有爱思助手检测，内部硬件为原装，安兔兔跑分为19万多，正常水平，电池效率为百分之九十七，屏幕和后壳也都为原装，刺激战场也很流畅，店家和质检小哥态度都很好，值得推荐，希望以后手机能有稳定的表现。","locationCity":null,"userImg":null,"sharePictures":[{"minPic":"http://qiniu.rrenji.com/FpFwdAJ4b92Facl-aBNv79rZTBMX?imageMogr2/thumbnail/!300x300r","maxPic":"http://qiniu.rrenji.com/FpFwdAJ4b92Facl-aBNv79rZTBMX"},{"minPic":"http://qiniu.rrenji.com/Fn6JvFT82VEXajyc6Nnbuw-OuD7F?imageMogr2/thumbnail/!300x300r","maxPic":"http://qiniu.rrenji.com/Fn6JvFT82VEXajyc6Nnbuw-OuD7F"}]}]}
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
         * pageInfo : {"currentPage":1,"totalPage":0,"pageSize":10,"totalRow":0}
         * evaluatelist : [{"evaluateId":61,"evaluateTitle":"你好，是我","shareTime":"2018-11-15T00:37:21.000+08:00","userName":"158****0821","evaluateDesc":"dsgsdfg","locationCity":null,"userImg":null,"sharePictures":[{"minPic":"[http:\\/\\/rrj.rhole.com/FgXLegjsrmyS2qUhKq9zf1sKCQ7-]?imageMogr2/thumbnail/!300x300r","maxPic":"[http:\\/\\/rrj.rhole.com/FgXLegjsrmyS2qUhKq9zf1sKCQ7-]"}]},{"evaluateId":60,"evaluateTitle":"123","shareTime":"2018-11-15T00:36:27.000+08:00","userName":"158****0821","evaluateDesc":"asdfasdfa","locationCity":null,"userImg":null,"sharePictures":[{"minPic":"[http:\\/\\/rrj.rhole.com/FgXLegjsrmyS2qUhKq9zf1sKCQ7-]?imageMogr2/thumbnail/!300x300r","maxPic":"[http:\\/\\/rrj.rhole.com/FgXLegjsrmyS2qUhKq9zf1sKCQ7-]"}]},{"evaluateId":59,"evaluateTitle":"123","shareTime":"2018-11-15T00:27:09.000+08:00","userName":"158****0821","evaluateDesc":"asdfasdfa","locationCity":null,"userImg":null,"sharePictures":null},{"evaluateId":51,"evaluateTitle":" iPhone 7 Plus 128G磨砂黑","shareTime":"2018-06-27T21:46:36.000+08:00","userName":"151****8876","evaluateDesc":"阿施","locationCity":null,"userImg":null,"sharePictures":[{"minPic":"http://qiniu.rrenji.com/ad43ac5f-1f0b-4ac1-8666-c861be421aa8_jpeg?imageMogr2/thumbnail/!300x300r","maxPic":"http://qiniu.rrenji.com/ad43ac5f-1f0b-4ac1-8666-c861be421aa8_jpeg"},{"minPic":"http://qiniu.rrenji.com/48c04bcc-5980-4806-80b7-611cd5a7fb25_jpeg?imageMogr2/thumbnail/!300x300r","maxPic":"http://qiniu.rrenji.com/48c04bcc-5980-4806-80b7-611cd5a7fb25_jpeg"}]},{"evaluateId":35,"evaluateTitle":"95新 iPhone7 plus 32G白色","shareTime":"2018-04-22T23:12:57.000+08:00","userName":"178****0064","evaluateDesc":"手机已拿到，银色32g7p，成色不错，基本全新，经过pp助手还有爱思助手检测，内部硬件为原装，安兔兔跑分为19万多，正常水平，电池效率为百分之九十七，屏幕和后壳也都为原装，刺激战场也很流畅，店家和质检小哥态度都很好，值得推荐，希望以后手机能有稳定的表现。","locationCity":null,"userImg":null,"sharePictures":[{"minPic":"http://qiniu.rrenji.com/FpFwdAJ4b92Facl-aBNv79rZTBMX?imageMogr2/thumbnail/!300x300r","maxPic":"http://qiniu.rrenji.com/FpFwdAJ4b92Facl-aBNv79rZTBMX"},{"minPic":"http://qiniu.rrenji.com/Fn6JvFT82VEXajyc6Nnbuw-OuD7F?imageMogr2/thumbnail/!300x300r","maxPic":"http://qiniu.rrenji.com/Fn6JvFT82VEXajyc6Nnbuw-OuD7F"}]}]
         */

        private PageInfoBean pageInfo;
        private List<EvaluatelistBean> evaluatelist;

        public PageInfoBean getPageInfo() {
            return pageInfo;
        }

        public void setPageInfo(PageInfoBean pageInfo) {
            this.pageInfo = pageInfo;
        }

        public List<EvaluatelistBean> getEvaluatelist() {
            return evaluatelist;
        }

        public void setEvaluatelist(List<EvaluatelistBean> evaluatelist) {
            this.evaluatelist = evaluatelist;
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

        public static class EvaluatelistBean {
            /**
             * evaluateId : 61
             * evaluateTitle : 你好，是我
             * shareTime : 2018-11-15T00:37:21.000+08:00
             * userName : 158****0821
             * evaluateDesc : dsgsdfg
             * locationCity : null
             * userImg : null
             * sharePictures : [{"minPic":"[http:\\/\\/rrj.rhole.com/FgXLegjsrmyS2qUhKq9zf1sKCQ7-]?imageMogr2/thumbnail/!300x300r","maxPic":"[http:\\/\\/rrj.rhole.com/FgXLegjsrmyS2qUhKq9zf1sKCQ7-]"}]
             */

            private int evaluateId;
            private String evaluateTitle;
            private String shareTime;
            private String userName;
            private String evaluateDesc;
            private Object locationCity;
            private Object userImg;
            private List<SharePicturesBean> sharePictures;

            public int getEvaluateId() {
                return evaluateId;
            }

            public void setEvaluateId(int evaluateId) {
                this.evaluateId = evaluateId;
            }

            public String getEvaluateTitle() {
                return evaluateTitle;
            }

            public void setEvaluateTitle(String evaluateTitle) {
                this.evaluateTitle = evaluateTitle;
            }

            public String getShareTime() {
                return shareTime;
            }

            public void setShareTime(String shareTime) {
                this.shareTime = shareTime;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getEvaluateDesc() {
                return evaluateDesc;
            }

            public void setEvaluateDesc(String evaluateDesc) {
                this.evaluateDesc = evaluateDesc;
            }

            public Object getLocationCity() {
                return locationCity;
            }

            public void setLocationCity(Object locationCity) {
                this.locationCity = locationCity;
            }

            public Object getUserImg() {
                return userImg;
            }

            public void setUserImg(Object userImg) {
                this.userImg = userImg;
            }

            public List<SharePicturesBean> getSharePictures() {
                return sharePictures;
            }

            public void setSharePictures(List<SharePicturesBean> sharePictures) {
                this.sharePictures = sharePictures;
            }

            public static class SharePicturesBean {
                /**
                 * minPic : [http:\/\/rrj.rhole.com/FgXLegjsrmyS2qUhKq9zf1sKCQ7-]?imageMogr2/thumbnail/!300x300r
                 * maxPic : [http:\/\/rrj.rhole.com/FgXLegjsrmyS2qUhKq9zf1sKCQ7-]
                 */

                private String minPic;
                private String maxPic;

                public String getMinPic() {
                    return minPic;
                }

                public void setMinPic(String minPic) {
                    this.minPic = minPic;
                }

                public String getMaxPic() {
                    return maxPic;
                }

                public void setMaxPic(String maxPic) {
                    this.maxPic = maxPic;
                }
            }
        }
    }
}
