package com.trade.rrenji.bean.order;

import java.io.Serializable;

public class RouteBean implements Serializable {
    /**
     * acceptTime : 2019-04-02 19:51:01
     * remark : 顺丰速运 已收取快件
     * accept_address : 深圳市
     * opcode : 54
     */

    private String acceptTime;
    private String remark;
    private String accept_address;
    private String opcode;

    public String getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(String acceptTime) {
        this.acceptTime = acceptTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAccept_address() {
        return accept_address;
    }

    public void setAccept_address(String accept_address) {
        this.accept_address = accept_address;
    }

    public String getOpcode() {
        return opcode;
    }

    public void setOpcode(String opcode) {
        this.opcode = opcode;
    }
}
