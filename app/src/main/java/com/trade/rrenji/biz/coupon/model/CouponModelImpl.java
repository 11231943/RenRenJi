package com.trade.rrenji.biz.coupon.model;

import android.content.Context;

import com.trade.rrenji.net.ServiceHelper;
import com.trade.rrenji.net.XUtils;
import com.trade.rrenji.utils.Contetns;
import com.trade.rrenji.utils.SettingUtils;

import java.util.Map;

/**
 * Created by monster on 8/4/18.
 */

public class CouponModelImpl implements CouponModel {

    private Context mContext;

    public CouponModelImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public void getCouponList(Context mContext, int pageNum, XUtils.ResultListener resultListener) {
        String url = ServiceHelper.buildUrl("api.v2.user.coupon");
        url = url + SettingUtils.getInstance().getSessionkeyString();
        ServiceHelper.ParamBuilder paramBuilder = new ServiceHelper.ParamBuilder(mContext);
        paramBuilder.add("page", pageNum);
        paramBuilder.add("rows", 20);
        Map<String, String> params = paramBuilder.build();
        XUtils.getInstance().get(url, params, resultListener);
    }

}
