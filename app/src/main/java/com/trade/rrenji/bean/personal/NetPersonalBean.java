package com.trade.rrenji.bean.personal;

import java.util.List;

public class NetPersonalBean {


    /**
     * code : 0
     * msg : null
     * data : {"userShareOrderList":[{"id":"37","userId":"270","goodsName":"95新 iPhone7  plus 128G 银色","userName":"158****0821","locationCity":"湖南省","evaluateDesc":"手机不错~非常新，姐妹介绍这个平台给我的，用了一个星期没什么问题，价格比其他平台便宜，客服服务非常好，非常细心，感谢！","createTime":"2018-05-11 18:55:20","evaluateReply":null,"picsList":[{"minPic":"http://qiniu.rrenji.com/FjfQW4Al2a-iQ7PmBA45nTSnrch1?imageMogr2/thumbnail/!300x300r","maxPic":"http://qiniu.rrenji.com/FjfQW4Al2a-iQ7PmBA45nTSnrch1"},{"minPic":"http://qiniu.rrenji.com/Fp-R18Z3KiyMloSoiP-mCEowEb2m?imageMogr2/thumbnail/!300x300r","maxPic":"http://qiniu.rrenji.com/Fp-R18Z3KiyMloSoiP-mCEowEb2m"},{"minPic":"http://qiniu.rrenji.com/Fm_Q1ux-ff3mirOiC-_2igm9pcbP?imageMogr2/thumbnail/!300x300r","maxPic":"http://qiniu.rrenji.com/Fm_Q1ux-ff3mirOiC-_2igm9pcbP"}]}],"shareCount":2,"userImg":"http://qiniu.rrenji.com/Flq26P2E6lHdUg_50Q4QLn13ilRU","waitGetCount":1,"weChatAuthState":1,"phone":"15889380821","waitShareCount":1,"waitSendCount":5,"userName":"哈哈哈哈哈哈哈哈就","message":"已加入人人机34天","userId":"270","aliPayAuthState":1}
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
         * userShareOrderList : [{"id":"37","userId":"270","goodsName":"95新 iPhone7  plus 128G 银色","userName":"158****0821","locationCity":"湖南省","evaluateDesc":"手机不错~非常新，姐妹介绍这个平台给我的，用了一个星期没什么问题，价格比其他平台便宜，客服服务非常好，非常细心，感谢！","createTime":"2018-05-11 18:55:20","evaluateReply":null,"picsList":[{"minPic":"http://qiniu.rrenji.com/FjfQW4Al2a-iQ7PmBA45nTSnrch1?imageMogr2/thumbnail/!300x300r","maxPic":"http://qiniu.rrenji.com/FjfQW4Al2a-iQ7PmBA45nTSnrch1"},{"minPic":"http://qiniu.rrenji.com/Fp-R18Z3KiyMloSoiP-mCEowEb2m?imageMogr2/thumbnail/!300x300r","maxPic":"http://qiniu.rrenji.com/Fp-R18Z3KiyMloSoiP-mCEowEb2m"},{"minPic":"http://qiniu.rrenji.com/Fm_Q1ux-ff3mirOiC-_2igm9pcbP?imageMogr2/thumbnail/!300x300r","maxPic":"http://qiniu.rrenji.com/Fm_Q1ux-ff3mirOiC-_2igm9pcbP"}]}]
         * shareCount : 2
         * userImg : http://qiniu.rrenji.com/Flq26P2E6lHdUg_50Q4QLn13ilRU
         * waitGetCount : 1
         * weChatAuthState : 1
         * phone : 15889380821
         * waitShareCount : 1
         * waitSendCount : 5
         * userName : 哈哈哈哈哈哈哈哈就
         * message : 已加入人人机34天
         * userId : 270
         * aliPayAuthState : 1
         */

        private int shareCount;
        private String userImg;
        private int waitGetCount;
        private int weChatAuthState;
        private String phone;
        private int waitShareCount;
        private int waitSendCount;
        private String userName;
        private String message;
        private String userId;
        private int aliPayAuthState;
        private List<UserShareOrderListBean> userShareOrderList;

        public int getShareCount() {
            return shareCount;
        }

        public void setShareCount(int shareCount) {
            this.shareCount = shareCount;
        }

        public String getUserImg() {
            return userImg;
        }

        public void setUserImg(String userImg) {
            this.userImg = userImg;
        }

        public int getWaitGetCount() {
            return waitGetCount;
        }

        public void setWaitGetCount(int waitGetCount) {
            this.waitGetCount = waitGetCount;
        }

        public int getWeChatAuthState() {
            return weChatAuthState;
        }

        public void setWeChatAuthState(int weChatAuthState) {
            this.weChatAuthState = weChatAuthState;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getWaitShareCount() {
            return waitShareCount;
        }

        public void setWaitShareCount(int waitShareCount) {
            this.waitShareCount = waitShareCount;
        }

        public int getWaitSendCount() {
            return waitSendCount;
        }

        public void setWaitSendCount(int waitSendCount) {
            this.waitSendCount = waitSendCount;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public int getAliPayAuthState() {
            return aliPayAuthState;
        }

        public void setAliPayAuthState(int aliPayAuthState) {
            this.aliPayAuthState = aliPayAuthState;
        }

        public List<UserShareOrderListBean> getUserShareOrderList() {
            return userShareOrderList;
        }

        public void setUserShareOrderList(List<UserShareOrderListBean> userShareOrderList) {
            this.userShareOrderList = userShareOrderList;
        }

        public static class UserShareOrderListBean {
            /**
             * id : 37
             * userId : 270
             * goodsName : 95新 iPhone7  plus 128G 银色
             * userName : 158****0821
             * locationCity : 湖南省
             * evaluateDesc : 手机不错~非常新，姐妹介绍这个平台给我的，用了一个星期没什么问题，价格比其他平台便宜，客服服务非常好，非常细心，感谢！
             * createTime : 2018-05-11 18:55:20
             * evaluateReply : null
             * picsList : [{"minPic":"http://qiniu.rrenji.com/FjfQW4Al2a-iQ7PmBA45nTSnrch1?imageMogr2/thumbnail/!300x300r","maxPic":"http://qiniu.rrenji.com/FjfQW4Al2a-iQ7PmBA45nTSnrch1"},{"minPic":"http://qiniu.rrenji.com/Fp-R18Z3KiyMloSoiP-mCEowEb2m?imageMogr2/thumbnail/!300x300r","maxPic":"http://qiniu.rrenji.com/Fp-R18Z3KiyMloSoiP-mCEowEb2m"},{"minPic":"http://qiniu.rrenji.com/Fm_Q1ux-ff3mirOiC-_2igm9pcbP?imageMogr2/thumbnail/!300x300r","maxPic":"http://qiniu.rrenji.com/Fm_Q1ux-ff3mirOiC-_2igm9pcbP"}]
             */

            private String id;
            private String userId;
            private String goodsName;
            private String userName;
            private String locationCity;
            private String evaluateDesc;
            private String createTime;
            private Object evaluateReply;
            private List<PicsListBean> picsList;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getGoodsName() {
                return goodsName;
            }

            public void setGoodsName(String goodsName) {
                this.goodsName = goodsName;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getLocationCity() {
                return locationCity;
            }

            public void setLocationCity(String locationCity) {
                this.locationCity = locationCity;
            }

            public String getEvaluateDesc() {
                return evaluateDesc;
            }

            public void setEvaluateDesc(String evaluateDesc) {
                this.evaluateDesc = evaluateDesc;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public Object getEvaluateReply() {
                return evaluateReply;
            }

            public void setEvaluateReply(Object evaluateReply) {
                this.evaluateReply = evaluateReply;
            }

            public List<PicsListBean> getPicsList() {
                return picsList;
            }

            public void setPicsList(List<PicsListBean> picsList) {
                this.picsList = picsList;
            }

            public static class PicsListBean {
                /**
                 * minPic : http://qiniu.rrenji.com/FjfQW4Al2a-iQ7PmBA45nTSnrch1?imageMogr2/thumbnail/!300x300r
                 * maxPic : http://qiniu.rrenji.com/FjfQW4Al2a-iQ7PmBA45nTSnrch1
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
