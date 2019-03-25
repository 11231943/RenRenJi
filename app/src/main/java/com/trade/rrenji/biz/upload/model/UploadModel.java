package com.trade.rrenji.biz.upload.model;

import android.content.Context;

import com.trade.rrenji.biz.base.PlusBaseService;
import com.trade.rrenji.net.XUtils;

public interface UploadModel extends PlusBaseService {

    void upload(Context mContext, String mPath, XUtils.ResultListener resultListener);
}
