package com.trade.rrenji.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.trade.rrenji.R;
import com.trade.rrenji.bean.personal.NetMineBean;
import com.trade.rrenji.bean.personal.NetSystemDataBean;
import com.trade.rrenji.biz.account.ui.activity.AccountActivity;
import com.trade.rrenji.biz.account.ui.activity.LoginActivity;
import com.trade.rrenji.biz.ad.AdActivity;
import com.trade.rrenji.biz.address.ui.activity.AddressAdminActivity;
import com.trade.rrenji.biz.auth.ui.AuthActivity;
import com.trade.rrenji.biz.collection.ui.activity.CollectionActivity;
import com.trade.rrenji.biz.coupon.ui.activity.CouponActivity;
import com.trade.rrenji.biz.invite.ui.activity.UserInvitePageActivity;
import com.trade.rrenji.biz.order.ui.activity.DryingActivity;
import com.trade.rrenji.biz.order.ui.activity.OrderActivity;
import com.trade.rrenji.biz.order.ui.activity.OrderAllActivity;
import com.trade.rrenji.biz.order.ui.activity.OrderListActivity;
import com.trade.rrenji.biz.order.ui.activity.ServiceActivity;
import com.trade.rrenji.biz.personal.model.PersonalModel;
import com.trade.rrenji.biz.personal.model.PersonalModelImpl;
import com.trade.rrenji.biz.personal.ui.activity.PersonalActivity;
import com.trade.rrenji.biz.setting.ui.SettingActivity;
import com.trade.rrenji.net.XUtils;
import com.trade.rrenji.utils.Contetns;
import com.trade.rrenji.utils.GlideUtils;
import com.trade.rrenji.utils.GsonUtils;
import com.trade.rrenji.utils.SettingUtils;
import com.trade.rrenji.utils.StatusBarUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by monster on 23/3/18.
 */

@ContentView(R.layout.fragment_mine)
public class MineFragment extends Fragment {

    private static String TAG = MineFragment.class.getSimpleName();
    public static final String CACHE_KEY = "mine";


    @ViewInject(R.id.user_avatar)
    public ImageView user_avatar;

    @ViewInject(R.id.user_name)
    public TextView user_name;

    @ViewInject(R.id.user_phone)
    public TextView user_phone;


    @ViewInject(R.id.login_not)
    public TextView login_not;

    @ViewInject(R.id.edit_account)
    public TextView edit_account;
    PersonalModel mPersonalModel;

    //是否是第一次开启网络加载
    public boolean isLogin = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = x.view().inject(this, inflater, container);
        StatusBarUtils.setWindowStatusBarColor(getActivity(), R.color.mine_red);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPersonalModel = new PersonalModelImpl(getActivity());
        init();
        getUserAdviceInfo();
        getSystemData();
    }

    private void getSystemData() {
        mPersonalModel.getSystemData(getActivity(), new XUtils.ResultListener() {
            @Override
            public void onResponse(String result) {
                try {
                    Gson gson = GsonUtils.getGson();
                    NetSystemDataBean netMineBean = gson.fromJson(result, NetSystemDataBean.class);
                    if (netMineBean.getCode().equals(Contetns.RESPONSE_OK)) {
                        isLogin = true;
                        NetSystemDataBean.DataBean dataBean = netMineBean.getData();
                        SettingUtils.getInstance().setWechat(dataBean.getKefu_wechat());
                        SettingUtils.getInstance().setJiaoLiuQun(dataBean.getRrj_jiaoliuqun());
                        SettingUtils.getInstance().setPingTaiGuiZe(dataBean.getRrj_pingtaiguize());
                        SettingUtils.getInstance().setQQ(dataBean.getKefu_QQ());
                        SettingUtils.getInstance().setRenRenZhaoShang(dataBean.getRrj_zhaoshang());
                        SettingUtils.getInstance().setSystemEmail(dataBean.getKefu_email());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable error) {

            }
        });
    }

    private void init() {
        if (TextUtils.isEmpty(SettingUtils.getInstance().getCurrentUid())) {
            login_not.setVisibility(View.VISIBLE);
            edit_account.setVisibility(View.GONE);
            user_name.setVisibility(View.GONE);
            user_phone.setVisibility(View.GONE);
            GlideUtils.getInstance().loadIcon(getActivity(), R.drawable.user_default_icon, R.drawable.user_default_icon, user_avatar);
        } else {
            edit_account.setVisibility(View.VISIBLE);
            user_name.setVisibility(View.VISIBLE);
            user_phone.setVisibility(View.VISIBLE);
            login_not.setVisibility(View.GONE);
            user_name.setText(SettingUtils.getInstance().getUsername());
            Log.e("Mine", SettingUtils.getInstance().getPhone());
            user_phone.setText(SettingUtils.getInstance().getPhone());
            GlideUtils.getInstance().loadCircleIcon(getActivity(), SettingUtils.getInstance().getUserImg(), R.drawable.user_default_icon, user_avatar);
        }

    }

    private void getUserAdviceInfo() {

        mPersonalModel.getUserAdviceInfo(getActivity(), new XUtils.ResultListener() {
            @Override
            public void onResponse(String result) {
                try {
                    Gson gson = GsonUtils.getGson();
                    NetMineBean netMineBean = gson.fromJson(result, NetMineBean.class);
                    if (netMineBean.getCode().equals(Contetns.RESPONSE_OK)) {
                        isLogin = true;
                        NetMineBean.DataBean bean = netMineBean.getData();
                        SettingUtils.getInstance().setCurrentUid(bean.getUserId());
                        SettingUtils.getInstance().setUsername(bean.getUserName());
                        SettingUtils.getInstance().setUserImg(bean.getUserImg());
                        SettingUtils.getInstance().setUserSex(bean.getSex());
                        SettingUtils.getInstance().setUserAddress(bean.getAddress());
                        user_name.setText(SettingUtils.getInstance().getUsername());
                        user_phone.setText(SettingUtils.getInstance().getPhone());
                        if (SettingUtils.getInstance().getPhone().equals("15986800825")) {
                            SettingUtils.getInstance().setAccountIsAdmin(true);
                        } else {
                            SettingUtils.getInstance().setAccountIsAdmin(false);
                        }
                        GlideUtils.getInstance().loadCircleIcon(getActivity(), SettingUtils.getInstance().getUserImg(), R.drawable.user_default_icon, user_avatar);
                    } else if (netMineBean.getCode().equals("666")) {
                        isLogin = false;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable error) {
                init();
            }
        });
    }

    @Event(value = {R.id.user_avatar, R.id.address_layout, R.id.collection_layout, R.id.user_setting, R.id.user_info_layout, R.id.auth_layout
            , R.id.invite_layout, R.id.coupon_layout, R.id.order_detail_layout, R.id.pre_order_layout, R.id.dry_layout, R.id.invite_layout
            , R.id.user_chat, R.id.user_server, R.id.user_rule, R.id.renren_zhaoshang, R.id.after_sale})
    private void onViewClicked(View view) {
        Intent intent = null;
        Log.e(TAG, "CurrentUid : " + SettingUtils.getInstance().getCurrentUid());
        switch (view.getId()) {
            case R.id.invite_layout:
                if (TextUtils.isEmpty(SettingUtils.getInstance().getCurrentUid())) {
                    intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    intent = new Intent(getActivity(), UserInvitePageActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.user_avatar:
                if (TextUtils.isEmpty(SettingUtils.getInstance().getCurrentUid())) {
                    intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    intent = new Intent(getActivity(), PersonalActivity.class);
                    intent.putExtra("type", 1);
                    intent.putExtra("id", SettingUtils.getInstance().getCurrentUid());
                    startActivity(intent);
                }
                break;
            case R.id.address_layout:
                if (TextUtils.isEmpty(SettingUtils.getInstance().getCurrentUid())) {
                    intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    intent = new Intent(getActivity(), AddressAdminActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.user_setting:
                intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.collection_layout:
                if (TextUtils.isEmpty(SettingUtils.getInstance().getCurrentUid())) {
                    intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    intent = new Intent(getActivity(), CollectionActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.user_info_layout:
                if (TextUtils.isEmpty(SettingUtils.getInstance().getCurrentUid())) {
                    intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    intent = new Intent(getActivity(), AccountActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.after_sale:
                if (TextUtils.isEmpty(SettingUtils.getInstance().getCurrentUid())) {
                    intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    intent = new Intent(getActivity(), ServiceActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.auth_layout:
                if (TextUtils.isEmpty(SettingUtils.getInstance().getCurrentUid())) {
                    intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    intent = new Intent(getActivity(), AuthActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.coupon_layout:
                if (TextUtils.isEmpty(SettingUtils.getInstance().getCurrentUid())) {
                    intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    intent = new Intent(getActivity(), CouponActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.order_detail_layout:
                if (TextUtils.isEmpty(SettingUtils.getInstance().getCurrentUid())) {
                    intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    intent = new Intent(getActivity(), OrderAllActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.pre_order_layout:
                if (TextUtils.isEmpty(SettingUtils.getInstance().getCurrentUid())) {
                    intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    intent = new Intent(getActivity(), OrderActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.dry_layout:
                if (TextUtils.isEmpty(SettingUtils.getInstance().getCurrentUid())) {
                    intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    intent = new Intent(getActivity(), OrderListActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.user_chat:
                AdActivity.start(getActivity(), SettingUtils.getInstance().getJiaoLiuQun());
                break;
            case R.id.user_server:
                AdActivity.start(getActivity(), SettingUtils.getInstance().getWechat());
                break;
            case R.id.user_rule:
                AdActivity.start(getActivity(), SettingUtils.getInstance().getPingTaiGuiZe());
                break;
            case R.id.renren_zhaoshang:
                AdActivity.start(getActivity(), SettingUtils.getInstance().getRenRenZhaoShang());
                break;
        }
    }
}
