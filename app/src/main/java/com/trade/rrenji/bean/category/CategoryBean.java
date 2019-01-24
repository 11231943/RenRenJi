package com.trade.rrenji.bean.category;

import java.util.List;

public class CategoryBean {

    private String name;
    private int type;
    private List<NetCategoryBean.DataBean.ResultListBean.HotProductListBean> hotProductList;
    private List<NetCategoryBean.DataBean.ResultListBean.CategoryBrandList> categoryBrandList;
    private List<NetCategoryBean.DataBean.ResultListBean.GoodsModelList> goodsModelList;
    private List<NetCategoryBean.DataBean.ResultListBean.AdvertisementList> advertisementList;

    public List<NetCategoryBean.DataBean.ResultListBean.AdvertisementList> getAdvertisementList() {
        return advertisementList;
    }

    public void setAdvertisementList(List<NetCategoryBean.DataBean.ResultListBean.AdvertisementList> advertisementList) {
        this.advertisementList = advertisementList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<NetCategoryBean.DataBean.ResultListBean.HotProductListBean> getHotProductList() {
        return hotProductList;
    }

    public void setHotProductList(List<NetCategoryBean.DataBean.ResultListBean.HotProductListBean> hotProductList) {
        this.hotProductList = hotProductList;
    }

    public List<NetCategoryBean.DataBean.ResultListBean.CategoryBrandList> getCategoryBrandList() {
        return categoryBrandList;
    }

    public void setCategoryBrandList(List<NetCategoryBean.DataBean.ResultListBean.CategoryBrandList> categoryBrandList) {
        this.categoryBrandList = categoryBrandList;
    }

    public List<NetCategoryBean.DataBean.ResultListBean.GoodsModelList> getGoodsModelList() {
        return goodsModelList;
    }

    public void setGoodsModelList(List<NetCategoryBean.DataBean.ResultListBean.GoodsModelList> goodsModelList) {
        this.goodsModelList = goodsModelList;
    }
}
