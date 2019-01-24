package com.trade.rrenji.biz.address.model;

import android.content.Context;

import com.trade.rrenji.biz.base.PlusBaseService;
import com.trade.rrenji.net.XUtils.ResultListener;

public interface AddressModel extends PlusBaseService {

    void getAddressList(Context mContext, int pageNum, ResultListener resultListener);

}
