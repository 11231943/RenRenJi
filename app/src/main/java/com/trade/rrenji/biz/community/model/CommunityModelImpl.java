package com.trade.rrenji.biz.community.model;

import android.content.Context;

import com.trade.rrenji.bean.category.ScreenBean;
import com.trade.rrenji.net.ServiceHelper;
import com.trade.rrenji.net.XUtils;
import com.trade.rrenji.net.XUtils.ResultListener;
import com.trade.rrenji.utils.GsonUtils;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Map;

public class CommunityModelImpl implements CommunityModel {

    private Context mContext;

    public CommunityModelImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public void getEveryoneCommunityList(Context mContext, int page, int rows, ResultListener resultListener) {
        String url = ServiceHelper.buildUrl("api.v2.home.getEveryoneCommunityList");
        ServiceHelper.ParamBuilder paramBuilder = new ServiceHelper.ParamBuilder(mContext);
        paramBuilder.add("page", page);
        paramBuilder.add("rows", rows);
        Map<String, String> params = paramBuilder.build();
        XUtils.getInstance().get(url, params, resultListener);
    }
}
