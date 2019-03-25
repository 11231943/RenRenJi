package com.trade.rrenji.biz.upload.model;

import android.content.Context;

import com.trade.rrenji.net.ServiceHelper;
import com.trade.rrenji.net.XUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class UploadModelImpl implements UploadModel {

    private Context mContext;

    public UploadModelImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public void upload(Context mContext, String mPath, XUtils.ResultListener resultListener) {
        String url = ServiceHelper.buildUrl("api.v2.upload");
        long timeStamp = System.currentTimeMillis();
        url = url + timeStamp;
        ServiceHelper.ParamBuilder paramBuilder = new ServiceHelper.ParamBuilder(mContext);
        paramBuilder.add("file", "file");
        Map<String, String> params = paramBuilder.build();
        File file = new File(mPath);
        Map<String, File> mfile = new HashMap<>();
        mfile.put("file", file);
        XUtils.getInstance().upLoadFile(url, params, mfile, resultListener);

    }
}
