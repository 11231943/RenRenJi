package com.trade.rrenji.bean.category;

import java.util.List;

public class PopupCategoryBean {

    private int type;
    private String typeName;
    private int size ;
    private List<BaseBean> baseBeans;

    public List<BaseBean> getBaseBeans() {
        return baseBeans;
    }

    public void setBaseBeans(List<BaseBean> baseBeans) {
        this.baseBeans = baseBeans;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
