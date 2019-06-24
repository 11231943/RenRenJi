package com.trade.rrenji.bean.order;

import java.io.Serializable;
import java.util.List;

public class NetLogisticsBean implements Serializable {

    /**
     * code : 0
     * msg : SUCCESS
     * result : {"head":"OK","expressName":"顺丰快递","route":[{"acceptTime":"2019-04-02 19:51:01","remark":"顺丰速运 已收取快件","accept_address":"深圳市","opcode":"54"},{"acceptTime":"2019-04-02 20:43:43","remark":"顺丰速运 已收取快件","accept_address":"深圳市","opcode":"50"},{"acceptTime":"2019-04-02 21:29:24","remark":"快件在【深圳福田上步大厦营业部】已装车,准备发往 【深圳五和集散中心】","accept_address":"深圳市","opcode":"30"},{"acceptTime":"2019-04-02 21:29:50","remark":"快件已发车","accept_address":"深圳市","opcode":"36"},{"acceptTime":"2019-04-02 21:56:55","remark":"快件到达 【深圳五和集散中心】","accept_address":"深圳市","opcode":"31"},{"acceptTime":"2019-04-02 23:22:40","remark":"快件在【深圳五和集散中心】已装车,准备发往 【东莞沙田集散中心】","accept_address":"深圳市","opcode":"30"},{"acceptTime":"2019-04-02 23:24:00","remark":"快件已发车","accept_address":"深圳市","opcode":"36"},{"acceptTime":"2019-04-03 00:54:54","remark":"快件到达 【东莞沙田集散中心】","accept_address":"东莞市","opcode":"31"},{"acceptTime":"2019-04-03 01:24:19","remark":"快件在【东莞沙田集散中心】已装车,准备发往 【哈尔滨哈平集散中心】","accept_address":"东莞市","opcode":"30"},{"acceptTime":"2019-04-03 01:27:48","remark":"快件已发车","accept_address":"东莞市","opcode":"36"},{"acceptTime":"2019-04-05 00:40:23","remark":"快件到达 【哈尔滨哈平集散中心】","accept_address":"哈尔滨市","opcode":"31"},{"acceptTime":"2019-04-05 02:41:59","remark":"快件在【哈尔滨哈平集散中心】已装车,准备发往下一站","accept_address":"哈尔滨市","opcode":"30"},{"acceptTime":"2019-04-05 05:45:05","remark":"快件已发车","accept_address":"哈尔滨市","opcode":"36"},{"acceptTime":"2019-04-05 07:43:16","remark":"快件到达 【哈尔滨市呼兰区裕田小区营业点】","accept_address":"哈尔滨市","opcode":"31"},{"acceptTime":"2019-04-05 07:46:24","remark":"正在派送途中,请您准备签收(派件人:吕国平，电话:13945654460)","accept_address":"哈尔滨市","opcode":"44"},{"acceptTime":"2019-04-05 07:58:24","remark":"快件交给吕国平，正在派送途中（联系电话：13945654460）","accept_address":"哈尔滨市","opcode":"204"},{"acceptTime":"2019-04-05 08:57:19","remark":"已签收,感谢使用顺丰,期待再次为您服务","accept_address":"哈尔滨市","opcode":"80"},{"acceptTime":"2019-04-05 08:57:22","remark":"在官网\"运单资料&签收图\",可查看签收人信息","accept_address":"哈尔滨市","opcode":"8000"}],"wayBillNo":"802498488925"}
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

    public static class ResultBean implements Serializable {
        /**
         * head : OK
         * expressName : 顺丰快递
         * route : [{"acceptTime":"2019-04-02 19:51:01","remark":"顺丰速运 已收取快件","accept_address":"深圳市","opcode":"54"},{"acceptTime":"2019-04-02 20:43:43","remark":"顺丰速运 已收取快件","accept_address":"深圳市","opcode":"50"},{"acceptTime":"2019-04-02 21:29:24","remark":"快件在【深圳福田上步大厦营业部】已装车,准备发往 【深圳五和集散中心】","accept_address":"深圳市","opcode":"30"},{"acceptTime":"2019-04-02 21:29:50","remark":"快件已发车","accept_address":"深圳市","opcode":"36"},{"acceptTime":"2019-04-02 21:56:55","remark":"快件到达 【深圳五和集散中心】","accept_address":"深圳市","opcode":"31"},{"acceptTime":"2019-04-02 23:22:40","remark":"快件在【深圳五和集散中心】已装车,准备发往 【东莞沙田集散中心】","accept_address":"深圳市","opcode":"30"},{"acceptTime":"2019-04-02 23:24:00","remark":"快件已发车","accept_address":"深圳市","opcode":"36"},{"acceptTime":"2019-04-03 00:54:54","remark":"快件到达 【东莞沙田集散中心】","accept_address":"东莞市","opcode":"31"},{"acceptTime":"2019-04-03 01:24:19","remark":"快件在【东莞沙田集散中心】已装车,准备发往 【哈尔滨哈平集散中心】","accept_address":"东莞市","opcode":"30"},{"acceptTime":"2019-04-03 01:27:48","remark":"快件已发车","accept_address":"东莞市","opcode":"36"},{"acceptTime":"2019-04-05 00:40:23","remark":"快件到达 【哈尔滨哈平集散中心】","accept_address":"哈尔滨市","opcode":"31"},{"acceptTime":"2019-04-05 02:41:59","remark":"快件在【哈尔滨哈平集散中心】已装车,准备发往下一站","accept_address":"哈尔滨市","opcode":"30"},{"acceptTime":"2019-04-05 05:45:05","remark":"快件已发车","accept_address":"哈尔滨市","opcode":"36"},{"acceptTime":"2019-04-05 07:43:16","remark":"快件到达 【哈尔滨市呼兰区裕田小区营业点】","accept_address":"哈尔滨市","opcode":"31"},{"acceptTime":"2019-04-05 07:46:24","remark":"正在派送途中,请您准备签收(派件人:吕国平，电话:13945654460)","accept_address":"哈尔滨市","opcode":"44"},{"acceptTime":"2019-04-05 07:58:24","remark":"快件交给吕国平，正在派送途中（联系电话：13945654460）","accept_address":"哈尔滨市","opcode":"204"},{"acceptTime":"2019-04-05 08:57:19","remark":"已签收,感谢使用顺丰,期待再次为您服务","accept_address":"哈尔滨市","opcode":"80"},{"acceptTime":"2019-04-05 08:57:22","remark":"在官网\"运单资料&签收图\",可查看签收人信息","accept_address":"哈尔滨市","opcode":"8000"}]
         * wayBillNo : 802498488925
         */

        private String head;
        private String expressName;
        private String wayBillNo;
        private List<RouteBean> route;

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }

        public String getExpressName() {
            return expressName;
        }

        public void setExpressName(String expressName) {
            this.expressName = expressName;
        }

        public String getWayBillNo() {
            return wayBillNo;
        }

        public void setWayBillNo(String wayBillNo) {
            this.wayBillNo = wayBillNo;
        }

        public List<RouteBean> getRoute() {
            return route;
        }

        public void setRoute(List<RouteBean> route) {
            this.route = route;
        }

     }
}
