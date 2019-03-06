package com.trade.rrenji.biz.address.model;

import android.content.Context;

import com.trade.rrenji.bean.address.UserAddressCurd;
import com.trade.rrenji.biz.base.PlusBaseService;
import com.trade.rrenji.net.XUtils.ResultListener;

public interface AddressModel extends PlusBaseService {

    void getAddressList(Context mContext, int pageNum, ResultListener resultListener);

    void updateAddress(Context mContext,int type, UserAddressCurd addressCurd, ResultListener resultListener);

    void delAddress(Context mContext,String addressId, ResultListener resultListener);

    void isUpAddress(Context mContext,String addressId, ResultListener resultListener);

}
