package com.trade.rrenji.biz.personal.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gelitenight.superrecyclerview.LinearSpacingDecoration;
import com.trade.rrenji.R;
import com.trade.rrenji.bean.personal.NetPersonalBean;
import com.trade.rrenji.biz.base.BaseActivity;
import com.trade.rrenji.biz.personal.presenter.PersonalActivityPresenter;
import com.trade.rrenji.biz.personal.presenter.PersonalActivityPresenterImpl;
import com.trade.rrenji.biz.personal.ui.adapter.PersonalAdapter;
import com.trade.rrenji.biz.personal.ui.view.PersonalActivityView;
import com.trade.rrenji.fragment.DryingTabFragment;
import com.trade.rrenji.utils.Contetns;
import com.trade.rrenji.utils.GlideUtils;
import com.trade.rrenji.utils.SettingUtils;
import com.trade.rrenji.utils.StatusBarUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import butterknife.Bind;
import butterknife.ButterKnife;


public class PersonalActivity extends BaseActivity implements PersonalActivityView {

    private static String TAG = DryingTabFragment.class.getSimpleName();
    PersonalActivityPresenter mPresenter;
    PersonalAdapter mPersonalAdapter = null;
    @Bind(R.id.back_icon)
    ImageView back_icon;
    @Bind(R.id.user_avatar)
    ImageView user_avatar;
    @Bind(R.id.user_name)
    TextView user_name;
    @Bind(R.id.user_phone)
    TextView user_phone;
    @Bind(R.id.user_info_layout)
    RelativeLayout user_info_layout;
    @Bind(R.id.join_day)
    TextView join_day;
    @Bind(R.id.wx_icon)
    ImageView wx_icon;
    @Bind(R.id.wx_rz_txt)
    TextView wx_rz_txt;
    @Bind(R.id.zfb_icon)
    ImageView zfb_icon;
    @Bind(R.id.personal_layout)
    LinearLayout personal_layout;
    @Bind(R.id.smrz_layout)
    RelativeLayout smrz_layout;
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private int mIndexPage = 1;
    private int mType = 0;
    private String mId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_main_layout);
        ButterKnife.bind(this);
        StatusBarUtils.setWindowStatusBarColor(this, R.color.mine_red);
        mType = getIntent().getIntExtra("type", -1);
        mId = getIntent().getStringExtra("id");
        init();
    }

    private void init() {
        mPersonalAdapter = new PersonalAdapter(this);
        mRecyclerView.addItemDecoration(new LinearSpacingDecoration(5, 5));
        mRecyclerView.setAdapter(mPersonalAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadData();
        back_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void loadData() {
//        if(mType==1){
//            mPresenter.getUserPersonalInfo(this, "270", mIndexPage, 20);
//        }
        GlideUtils.getInstance().loadCircleIcon(this, SettingUtils.getInstance().getUserImg(), R.drawable.user_default_icon, user_avatar);

        mPresenter.getUserPersonalInfo(this, mId, mIndexPage, 20);
    }

    @Override
    protected void attachPresenter() {
        mPresenter = new PersonalActivityPresenterImpl(this);
        mPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        mPresenter.detachView();
        mPresenter = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void getUserPersonalInfo(NetPersonalBean netShareBean) {
        if (netShareBean.getCode().equals(Contetns.RESPONSE_OK)) {
            NetPersonalBean.DataBean dataBean = netShareBean.getData();
            join_day.setText(dataBean.getMessage());
            user_name.setText(dataBean.getUserName());
            user_phone.setText(dataBean.getPhone());
            mPersonalAdapter.addAll(dataBean.getUserShareOrderList());
        }
    }

    @Override
    public void getUserPersonalInfoError(int code, String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
