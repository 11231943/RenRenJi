package com.trade.rrenji.bean.tech;

import java.util.List;

public class NetTechBean {


    /**
     * code : 0
     * msg : SUCCESS
     * result : {"pageInfo":{"currentPage":1,"totalPage":1,"pageSize":10,"totalRow":10},"communityList":[{"communityId":33,"title":"魅族16详细评测 实力圈粉 王者归来","readingNum":"302","publishTime":"2018-08-31T12:22:24.000+08:00","showUrl":"http://qiniu.rrenji.com/FvuPckaAGEtbXb95E21U54EHcNvk","acticleUrl":"http://www.365yg.com/i6594769930971775495/#mid=6358858341","praiseNumber":"83"},{"communityId":34,"title":"坚果RPO2S详细评测 稳中求胜 一元碾压","readingNum":"133","publishTime":"2018-08-31T12:37:54.000+08:00","showUrl":"http://qiniu.rrenji.com/FlhmQwgGZxkcohtQC-Gg7l8nvb-O","acticleUrl":"http://www.365yg.com/i6595512305876730372/#mid=6358858341","praiseNumber":"35"},{"communityId":29,"title":"荣耀Note10开箱上手 对比小米Max3","readingNum":"401","publishTime":"2018-08-07T13:02:57.000+08:00","showUrl":"http://qiniu.rrenji.com/FmxfL5t_nNahjSXWAIhIOfUjmC8t","acticleUrl":"http://www.365yg.com/i6585162525443097091/#mid=6358858341","praiseNumber":"60"},{"communityId":27,"title":"荣耀Note10详细评测，全能型大屏王者？","readingNum":"312","publishTime":"2018-08-07T12:46:27.000+08:00","showUrl":"http://qiniu.rrenji.com/FjA_bIaFRKgFj-WyS1JFzV0wBYsb","acticleUrl":"http://www.365yg.com/i6586500107305222663/#mid=6358858341","praiseNumber":"76"},{"communityId":19,"title":"《拍照技巧》用iPhone如何逆光照时拍出好照片？","readingNum":"45","publishTime":"2018-01-27T15:45:28.000+08:00","showUrl":"http://qiniu.rrenji.com/FpANX1lPPQd8eOyQJL8ifxUK9OF7","acticleUrl":"http://kuaibao.qq.com/s/20180114V0FFDU00","praiseNumber":"5"},{"communityId":28,"title":"比大更大 小米Max3超大屏手机详细评测","readingNum":"224","publishTime":"2018-08-07T13:01:10.000+08:00","showUrl":"http://qiniu.rrenji.com/FuPpv01GMe2ZbX0QhEl4uChpir_t","acticleUrl":"http://www.365yg.com/i6582455224663802371/#mid=6358858341","praiseNumber":"55"},{"communityId":18,"title":"《拍照技巧》iPhone 回忆门的个性化窍门","readingNum":"55","publishTime":"2018-01-27T15:43:18.000+08:00","showUrl":"http://qiniu.rrenji.com/FuB-IRc_eTPaaJvkXTnlviYou37-","acticleUrl":"http://kuaibao.qq.com/s/20180117V0Y2UC00","praiseNumber":"5"},{"communityId":17,"title":"《APP推荐》终于找到这款神器，视频来电显示","readingNum":"50","publishTime":"2017-12-13T18:49:00.000+08:00","showUrl":"http://qiniu.rrenji.com/FiSKbAxrOfaYYEtOrnl16b-YHB4r","acticleUrl":"http://mp.weixin.qq.com/s/qqE-EgYgJKn9C8pPdKRTxg","praiseNumber":"3"},{"communityId":16,"title":"《耳机技巧》iPhone7原装耳机的高级使用技巧","readingNum":"45","publishTime":"2017-12-13T18:39:23.000+08:00","showUrl":"http://qiniu.rrenji.com/FhrAD-pUB5IwSIb-sCtbj0_S72mB","acticleUrl":"http://m.toutiaocdn.net/group/6387350539424530689/?iid=12388102270&app=news_article&tt_from=copy_link&utm_source=copy_link&utm_medium=toutiao_ios&utm_campaign=client_share","praiseNumber":"11"},{"communityId":15,"title":"《拍照技巧》IPhone7/7p手机，4个拍照技巧，拍出单反相机的效果","readingNum":"74","publishTime":"2017-12-13T18:32:40.000+08:00","showUrl":"http://qiniu.rrenji.com/Foy437G1LkLaKFKlNZPEPXBZKzJy","acticleUrl":"http://m.toutiaocdn.cn/group/6442537787123040782/?iid=12388102270&app=news_article&tt_from=copy_link&utm_source=copy_link&utm_medium=toutiao_ios&utm_campaign=client_share","praiseNumber":"8"}]}
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
         * pageInfo : {"currentPage":1,"totalPage":1,"pageSize":10,"totalRow":10}
         * communityList : [{"communityId":33,"title":"魅族16详细评测 实力圈粉 王者归来","readingNum":"302","publishTime":"2018-08-31T12:22:24.000+08:00","showUrl":"http://qiniu.rrenji.com/FvuPckaAGEtbXb95E21U54EHcNvk","acticleUrl":"http://www.365yg.com/i6594769930971775495/#mid=6358858341","praiseNumber":"83"},{"communityId":34,"title":"坚果RPO2S详细评测 稳中求胜 一元碾压","readingNum":"133","publishTime":"2018-08-31T12:37:54.000+08:00","showUrl":"http://qiniu.rrenji.com/FlhmQwgGZxkcohtQC-Gg7l8nvb-O","acticleUrl":"http://www.365yg.com/i6595512305876730372/#mid=6358858341","praiseNumber":"35"},{"communityId":29,"title":"荣耀Note10开箱上手 对比小米Max3","readingNum":"401","publishTime":"2018-08-07T13:02:57.000+08:00","showUrl":"http://qiniu.rrenji.com/FmxfL5t_nNahjSXWAIhIOfUjmC8t","acticleUrl":"http://www.365yg.com/i6585162525443097091/#mid=6358858341","praiseNumber":"60"},{"communityId":27,"title":"荣耀Note10详细评测，全能型大屏王者？","readingNum":"312","publishTime":"2018-08-07T12:46:27.000+08:00","showUrl":"http://qiniu.rrenji.com/FjA_bIaFRKgFj-WyS1JFzV0wBYsb","acticleUrl":"http://www.365yg.com/i6586500107305222663/#mid=6358858341","praiseNumber":"76"},{"communityId":19,"title":"《拍照技巧》用iPhone如何逆光照时拍出好照片？","readingNum":"45","publishTime":"2018-01-27T15:45:28.000+08:00","showUrl":"http://qiniu.rrenji.com/FpANX1lPPQd8eOyQJL8ifxUK9OF7","acticleUrl":"http://kuaibao.qq.com/s/20180114V0FFDU00","praiseNumber":"5"},{"communityId":28,"title":"比大更大 小米Max3超大屏手机详细评测","readingNum":"224","publishTime":"2018-08-07T13:01:10.000+08:00","showUrl":"http://qiniu.rrenji.com/FuPpv01GMe2ZbX0QhEl4uChpir_t","acticleUrl":"http://www.365yg.com/i6582455224663802371/#mid=6358858341","praiseNumber":"55"},{"communityId":18,"title":"《拍照技巧》iPhone 回忆门的个性化窍门","readingNum":"55","publishTime":"2018-01-27T15:43:18.000+08:00","showUrl":"http://qiniu.rrenji.com/FuB-IRc_eTPaaJvkXTnlviYou37-","acticleUrl":"http://kuaibao.qq.com/s/20180117V0Y2UC00","praiseNumber":"5"},{"communityId":17,"title":"《APP推荐》终于找到这款神器，视频来电显示","readingNum":"50","publishTime":"2017-12-13T18:49:00.000+08:00","showUrl":"http://qiniu.rrenji.com/FiSKbAxrOfaYYEtOrnl16b-YHB4r","acticleUrl":"http://mp.weixin.qq.com/s/qqE-EgYgJKn9C8pPdKRTxg","praiseNumber":"3"},{"communityId":16,"title":"《耳机技巧》iPhone7原装耳机的高级使用技巧","readingNum":"45","publishTime":"2017-12-13T18:39:23.000+08:00","showUrl":"http://qiniu.rrenji.com/FhrAD-pUB5IwSIb-sCtbj0_S72mB","acticleUrl":"http://m.toutiaocdn.net/group/6387350539424530689/?iid=12388102270&app=news_article&tt_from=copy_link&utm_source=copy_link&utm_medium=toutiao_ios&utm_campaign=client_share","praiseNumber":"11"},{"communityId":15,"title":"《拍照技巧》IPhone7/7p手机，4个拍照技巧，拍出单反相机的效果","readingNum":"74","publishTime":"2017-12-13T18:32:40.000+08:00","showUrl":"http://qiniu.rrenji.com/Foy437G1LkLaKFKlNZPEPXBZKzJy","acticleUrl":"http://m.toutiaocdn.cn/group/6442537787123040782/?iid=12388102270&app=news_article&tt_from=copy_link&utm_source=copy_link&utm_medium=toutiao_ios&utm_campaign=client_share","praiseNumber":"8"}]
         */

        private PageInfoBean pageInfo;
        private List<CommunityListBean> communityList;

        public PageInfoBean getPageInfo() {
            return pageInfo;
        }

        public void setPageInfo(PageInfoBean pageInfo) {
            this.pageInfo = pageInfo;
        }

        public List<CommunityListBean> getCommunityList() {
            return communityList;
        }

        public void setCommunityList(List<CommunityListBean> communityList) {
            this.communityList = communityList;
        }

        public static class PageInfoBean {
            /**
             * currentPage : 1
             * totalPage : 1
             * pageSize : 10
             * totalRow : 10
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

        public static class CommunityListBean {
            /**
             * communityId : 33
             * title : 魅族16详细评测 实力圈粉 王者归来
             * readingNum : 302
             * publishTime : 2018-08-31T12:22:24.000+08:00
             * showUrl : http://qiniu.rrenji.com/FvuPckaAGEtbXb95E21U54EHcNvk
             * acticleUrl : http://www.365yg.com/i6594769930971775495/#mid=6358858341
             * praiseNumber : 83
             */

            private int communityId;
            private String title;
            private String readingNum;
            private String publishTime;
            private String showUrl;
            private String acticleUrl;
            private String praiseNumber;

            public int getCommunityId() {
                return communityId;
            }

            public void setCommunityId(int communityId) {
                this.communityId = communityId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getReadingNum() {
                return readingNum;
            }

            public void setReadingNum(String readingNum) {
                this.readingNum = readingNum;
            }

            public String getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(String publishTime) {
                this.publishTime = publishTime;
            }

            public String getShowUrl() {
                return showUrl;
            }

            public void setShowUrl(String showUrl) {
                this.showUrl = showUrl;
            }

            public String getActicleUrl() {
                return acticleUrl;
            }

            public void setActicleUrl(String acticleUrl) {
                this.acticleUrl = acticleUrl;
            }

            public String getPraiseNumber() {
                return praiseNumber;
            }

            public void setPraiseNumber(String praiseNumber) {
                this.praiseNumber = praiseNumber;
            }
        }
    }
}
