package com.trade.rrenji.bean.goods;

import java.io.Serializable;

public class NetGoodsDetailBean implements Serializable {

    /**
     * code : 0
     * msg : SUCCESS
     * result : {"goodsPics":[{"minPic":"http://qiniu.rrenji.com/Fmp0x1fAsBVvuBwzAciWPyYCWTAA?imageMogr2/thumbnail/!30x30r","maxPic":"http://qiniu.rrenji.com/Fmp0x1fAsBVvuBwzAciWPyYCWTAA"},{"minPic":"http://qiniu.rrenji.com/FjakF-V6aQdK0bQCXIlINsyCMAdh?imageMogr2/thumbnail/!30x30r","maxPic":"http://qiniu.rrenji.com/FjakF-V6aQdK0bQCXIlINsyCMAdh"},{"minPic":"http://qiniu.rrenji.com/FnHomy7HdwjBn2YF_HN5AQ7o0U_Z?imageMogr2/thumbnail/!30x30r","maxPic":"http://qiniu.rrenji.com/FnHomy7HdwjBn2YF_HN5AQ7o0U_Z"},{"minPic":"http://qiniu.rrenji.com/FmzaM6Rk2Akv3bSXP3EG5XC_kA8_?imageMogr2/thumbnail/!30x30r","maxPic":"http://qiniu.rrenji.com/FmzaM6Rk2Akv3bSXP3EG5XC_kA8_"},{"minPic":"http://qiniu.rrenji.com/FqjiMjvkWDcHSSwC0wodYUstH6UC?imageMogr2/thumbnail/!30x30r","maxPic":"http://qiniu.rrenji.com/FqjiMjvkWDcHSSwC0wodYUstH6UC"}],"originalPrice":2999,"commonQuestion":"http://112.124.98.145:8080/api/html/common_question.html","goodsId":310,"isCollection":false,"title":"全新 小米8 6+128G 黑白金蓝","facadeTest":"http://owptno2wx.bkt.clouddn.com/Fm3-gsK-4J7uxamSgvA8_jevvnwy","finenessStandard":"http://owptno2wx.bkt.clouddn.com/Fvn4Qcb6uA2hdg7prKU2vVttFgLA","conditionId":1,"damagePoint":"","price":2599,"qualityEngineer":{"headUrl":"http://qiniu.rrenji.com/FuSfKpKCJz7w4T2j9HxGmR2lvivZ","name":"邓东标","title":"质检工程师","desc":"手机维修经验 7年 "},"testVideoList":[],"addressEntity":{},"goodsDesc":"该部机为全新未拆封，支持官方全国联保。","simpleSpecifications":["移动4G联通4G电信4G","国行"],"serviceTags":[],"functionTest":"http://owptno2wx.bkt.clouddn.com/Fv03OHspXKiOjD2fowGJptiY9YOf","afterSalePolicy":"http://112.124.98.145:8080/api/html/after_sale.html","evaluateList":[{"evaluateDesc":"手机很好，又是新的我感觉很好，我很喜欢","sharePicList":[{"minPic":"http://qiniu.rrenji.com/d98c6b85-e2d9-4895-a1bf-7e95492a204a_jpeg?imageMogr2/thumbnail/!300x300r","maxPic":"http://qiniu.rrenji.com/d98c6b85-e2d9-4895-a1bf-7e95492a204a_jpeg"},{"minPic":"http://qiniu.rrenji.com/b8e7c043-fd32-4f84-aa95-5444b08cb0ea_jpeg?imageMogr2/thumbnail/!300x300r","maxPic":"http://qiniu.rrenji.com/b8e7c043-fd32-4f84-aa95-5444b08cb0ea_jpeg"},{"minPic":"http://qiniu.rrenji.com/4f8af06c-aab2-4542-a189-ff5e04c707ba_jpeg?imageMogr2/thumbnail/!300x300r","maxPic":"http://qiniu.rrenji.com/4f8af06c-aab2-4542-a189-ff5e04c707ba_jpeg"}],"userName":"155****7909","goodsDesc":"全新小米8、未开封6+128G"},{"evaluateDesc":"准备开始耍","sharePicList":[{"minPic":"http://qiniu.rrenji.com/FsAVbj5QRjo4GryBd5-W0j9G1VRZ?imageMogr2/thumbnail/!300x300r","maxPic":"http://qiniu.rrenji.com/FsAVbj5QRjo4GryBd5-W0j9G1VRZ"},{"minPic":"http://qiniu.rrenji.com/FhYZSEPJQBb-Lxw1m31vojbIc4KS?imageMogr2/thumbnail/!300x300r","maxPic":"http://qiniu.rrenji.com/FhYZSEPJQBb-Lxw1m31vojbIc4KS"},{"minPic":"http://qiniu.rrenji.com/FkuIdEDNduQCzpqNR_F2jH7Ai7Xl?imageMogr2/thumbnail/!300x300r","maxPic":"http://qiniu.rrenji.com/FkuIdEDNduQCzpqNR_F2jH7Ai7Xl"}],"userName":"150****4092","goodsDesc":"小米Max3新机，仅拍过测试视频"}],"specification":{"hardware":{"cpu":"","frequency":""},"camera":{"front":"","post":""},"facade":{"phoneSize":"","simSize":""},"screen":{"size":"6","resolvingPower":"1080*2160"},"NetBaseResultBean":{"model":"小米8","memory":"128G","color":"黑色","network":"移动4G联通4G电信4G","version":"国行","newLog":""},"nativeDesc":{"repairRecord":"","edition":"","batteryLife":"","card":"","inlet":"","version":"国行"}},"packaging":"http://owptno2wx.bkt.clouddn.com/FqBxRQmpO6KMXkcZqXkxxZau-_GK","titleTag":"","goodsCoverImg":"http://qiniu.rrenji.com/FpsoB0pOPBgJ91kvMql4gOJ1TG1M?imageMogr2/thumbnail/!230x230r","goodsCode":"1536723211135","customerServiceStaff":"supper man"}
     */

    private String code;
    private String msg;
    private GoodsDetailBean result;

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

    public GoodsDetailBean getResult() {
        return result;
    }

    public void setResult(GoodsDetailBean result) {
        this.result = result;
    }


}
