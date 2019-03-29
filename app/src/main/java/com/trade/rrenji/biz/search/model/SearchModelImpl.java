package com.trade.rrenji.biz.search.model;

import android.content.Context;

import com.trade.rrenji.net.ServiceHelper;
import com.trade.rrenji.net.XUtils;
import com.trade.rrenji.utils.SettingUtils;

import java.util.Map;

/**
 * Created by monster on 8/4/18.
 */

public class SearchModelImpl implements SearchModel {

    private Context mContext;

    public SearchModelImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public void getCollectionList(Context mContext, int pageNum, XUtils.ResultListener resultListener) {
        String url = ServiceHelper.buildUrl("api.v2.address.list");
        long timeStamp = System.currentTimeMillis();
        String token = "1";
        url = url + SettingUtils.getInstance().getSessionkeyString() + "/" + timeStamp + "/" + pageNum + "/" + token;
        ServiceHelper.ParamBuilder paramBuilder = new ServiceHelper.ParamBuilder(mContext);
//        paramBuilder.add("sessionKey", "1");
//        paramBuilder.add("timeStamp", System.currentTimeMillis());
//        paramBuilder.add("pageNum", pageNum);
//        paramBuilder.add("token", "1");
        Map<String, String> params = paramBuilder.build();
        XUtils.getInstance().get(url, params, resultListener);
    }

}
