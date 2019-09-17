package com.trade.rrenji.biz.invite.ui.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.trade.rrenji.R;
import com.trade.rrenji.bean.invite.NetInviteBean;
import com.trade.rrenji.biz.base.BaseActivity;
import com.trade.rrenji.biz.invite.persenter.InviteActivityPresenter;
import com.trade.rrenji.biz.invite.persenter.InviteActivityPresenterImpl;
import com.trade.rrenji.biz.invite.ui.view.InviteActivityView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 邀请好友
 */
@ContentView(R.layout.user_invite_main_layout)
public class UserInvitePageActivity extends BaseActivity implements InviteActivityView {

    InviteActivityPresenter mPresenter;
    @Bind(R.id.invite_bg)
    ImageView inviteBg;
    @Bind(R.id.tip_1)
    TextView tip1;
    @Bind(R.id.invite_code)
    TextView invite_code;
    @Bind(R.id.invite_layout)
    RelativeLayout inviteLayout;

    private ClipboardManager mClipboard = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_invite_main_layout);
        ButterKnife.bind(this);
        setActionBarTitle("邀请好友");
        invite_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyFromEditText1();
            }
        });
    }

    private void copyFromEditText1() {

        // Gets a handle to the clipboard service.
        if (null == mClipboard) {
            mClipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

        }

        // Creates a new text clip to put on the clipboard
        ClipData clip = ClipData.newPlainText("simple text",
                invite_code.getText());

        // Set the clipboard's primary clip.
        mClipboard.setPrimaryClip(clip);
        Toast.makeText(this, "复制成功！", Toast.LENGTH_SHORT).show();
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
