package com.trade.rrenji.bean.home;


import java.util.List;

public class HomeBean {
    private int type;
    private List<NetHomeBean.DataBean.ThousandOptimizationBean> thousandOptimization;
    private List<NetHomeBean.DataBean.EveryoneCommunityListBean> everyoneCommunityList;
    private List<NetHomeBean.DataBean.CategoryListBean> categoryList;
    private List<NetHomeBean.DataBean.MiddleBannerListBean> middleBannerList;
    private List<NetHomeBean.DataBean.HotAndroidBean> hotAndroid;
    private List<NetHomeBean.DataBean.HotActivityInfoListBean> hotActivityInfoList;
    private List<NetHomeBean.DataBean.TopBannerListBean> topBannerList;
    private List<NetHomeBean.DataBean.HotIphoneBean> hotIphone;
    private NetHomeBean.DataBean.EveryoneHomeBean everyoneHome;

    public NetHomeBean.DataBean.EveryoneHomeBean getEveryoneHome() {
        return everyoneHome;
    }

    public void setEveryoneHome(NetHomeBean.DataBean.EveryoneHomeBean everyoneHome) {
        this.everyoneHome = everyoneHome;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<NetHomeBean.DataBean.ThousandOptimizationBean> getThousandOptimization() {
        return thousandOptimization;
    }

    public void setThousandOptimization(List<NetHomeBean.DataBean.ThousandOptimizationBean> thousandOptimization) {
        this.thousandOptimization = thousandOptimization;
    }

    public List<NetHomeBean.DataBean.EveryoneCommunityListBean> getEveryoneCommunityList() {
        return everyoneCommunityList;
    }

    public void setEveryoneCommunityList(List<NetHomeBean.DataBean.EveryoneCommunityListBean> everyoneCommunityList) {
        this.everyoneCommunityList = everyoneCommunityList;
    }

    public List<NetHomeBean.DataBean.CategoryListBean> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<NetHomeBean.DataBean.CategoryListBean> categoryList) {
        this.categoryList = categoryList;
    }

    public List<NetHomeBean.DataBean.MiddleBannerListBean> getMiddleBannerList() {
        return middleBannerList;
    }

    public void setMiddleBannerList(List<NetHomeBean.DataBean.MiddleBannerListBean> middleBannerList) {
        this.middleBannerList = middleBannerList;
    }

    public List<NetHomeBean.DataBean.HotAndroidBean> getHotAndroid() {
        return hotAndroid;
    }

    public void setHotAndroid(List<NetHomeBean.DataBean.HotAndroidBean> hotAndroid) {
        this.hotAndroid = hotAndroid;
    }

    public List<NetHomeBean.DataBean.HotActivityInfoListBean> getHotActivityInfoList() {
        return hotActivityInfoList;
    }

    public void setHotActivityInfoList(List<NetHomeBean.DataBean.HotActivityInfoListBean> hotActivityInfoList) {
        this.hotActivityInfoList = hotActivityInfoList;
    }

    public List<NetHomeBean.DataBean.TopBannerListBean> getTopBannerList() {
        return topBannerList;
    }

    public void setTopBannerList(List<NetHomeBean.DataBean.TopBannerListBean> topBannerList) {
        this.topBannerList = topBannerList;
    }

    public List<NetHomeBean.DataBean.HotIphoneBean> getHotIphone() {
        return hotIphone;
    }

    public void setHotIphone(List<NetHomeBean.DataBean.HotIphoneBean> hotIphone) {
        this.hotIphone = hotIphone;
    }
}
