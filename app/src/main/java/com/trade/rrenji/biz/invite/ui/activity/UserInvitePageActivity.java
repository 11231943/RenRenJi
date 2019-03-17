package com.trade.rrenji.biz.invite.ui.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.trade.rrenji.R;
import com.trade.rrenji.bean.invite.NetInviteBean;
import com.trade.rrenji.biz.base.BaseActivity;
import com.trade.rrenji.biz.invite.persenter.InviteActivityPresenter;
import com.trade.rrenji.biz.invite.persenter.InviteActivityPresenterImpl;
import com.trade.rrenji.biz.invite.ui.view.InviteActivityView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * 邀请好友
 */
@ContentView(R.layout.user_invite_main_layout)
public class UserInvitePageActivity extends BaseActivity implements InviteActivityView {

    InviteActivityPresenter mPresenter;

    @ViewInject(R.id.invite_code)
    public TextView invite_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarTitle("邀请好友");
    }

    private void init() {
        mPresenter.getInviteCode(this);
    }

    @Override
    protected void attachPresenter() {
        mPresenter = new InviteActivityPresenterImpl(this);
        mPresenter.attachView(this);
        init();
    }

    @Override
    protected void detachPresenter() {
        mPresenter.detachView();
        mPresenter = null;
    }

    @Override
    public void getInviteCodeSuccess(NetInviteBean netInviteBean) {
        invite_code.setText(netInviteBean.getResult().getCode());
    }

    @Override
    public void getInviteCodeError(int code, String msg) {

    }
}
