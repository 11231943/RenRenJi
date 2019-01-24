package com.trade.rrenji.bean.drying;

public class SharePicturesBean {

    public String minPic;
    public String largePic;

    public String getMinPic() {
        return minPic;
    }

    public void setMinPic(String minPic) {
        this.minPic = minPic;
    }

    public String getLargePic() {
        return largePic;
    }

    public void setLargePic(String largePic) {
        this.largePic = largePic;
    }

    @Override
    public String toString() {
        return "SharePicturesBean{" +
                "minPic='" + minPic + '\'' +
                ", largePic='" + largePic + '\'' +
                '}';
    }
}
