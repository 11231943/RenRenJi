package com.trade.rrenji.bean.drying;

import java.io.Serializable;
import java.util.List;

public class DryingOrdersBean implements Serializable {

    public String userName;
    public String shareTime;
    public String phoneDesc;
    public String location;
    public String comment;
    public String reply;
    public List<SharePicturesBean> sharePictures;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getShareTime() {
        return shareTime;
    }

    public void setShareTime(String shareTime) {
        this.shareTime = shareTime;
    }

    public String getPhoneDesc() {
        return phoneDesc;
    }

    public void setPhoneDesc(String phoneDesc) {
        this.phoneDesc = phoneDesc;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public List<SharePicturesBean> getSharePictures() {
        return sharePictures;
    }

    public void setSharePictures(List<SharePicturesBean> sharePictures) {
        this.sharePictures = sharePictures;
    }

    @Override
    public String toString() {
        return "DryingOrdersBean{" +
                "userName='" + userName + '\'' +
                ", shareTime='" + shareTime + '\'' +
                ", phoneDesc='" + phoneDesc + '\'' +
                ", location='" + location + '\'' +
                ", comment='" + comment + '\'' +
                ", reply='" + reply + '\'' +
                ", sharePictures=" + sharePictures +
                '}';
    }
}
