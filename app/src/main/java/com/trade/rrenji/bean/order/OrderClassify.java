package com.trade.rrenji.bean.order;

public class OrderClassify {

    private String name;
    private int type;

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

    @Override
    public String toString() {
        return "OrderClassify{" +
                "name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
