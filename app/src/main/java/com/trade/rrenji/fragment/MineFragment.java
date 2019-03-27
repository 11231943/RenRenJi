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
import com.trade.rrenji.biz.account.ui.activity.AccountActivity;
import com.trade.rrenji.biz.account.ui.activity.LoginActivity;
import com.trade.rrenji.biz.address.ui.activity.AddressAdminActivity;
import com.trade.rrenji.biz.auth.ui.AuthActivity;
import com.trade.rrenji.biz.collection.ui.activity.CollectionActivity;
import com.trade.rrenji.biz.coupon.ui.activity.CouponActivity;
import com.trade.rrenji.biz.invite.ui.activity.UserInvitePageActivity;
import com.trade.rrenji.biz.order.ui.activity.DryingActivity;
import com.trade.rrenji.biz.order.ui.activity.OrderActivity;
import com.trade.rrenji.biz.order.ui.activity.OrderAllActivity;
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
        getUserAdviceInfo();
        init();
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
                    Log.e("Mine", netMineBean.getData().toString());
                    if (netMineBean.getCode().equals(Contetns.RESPONSE_OK)) {
                        NetMineBean.DataBean bean = netMineBean.getData();
                        SettingUtils.getInstance().setCurrentUid(bean.getUserId());
                        SettingUtils.getInstance().setUsername(bean.getUserName());
                        SettingUtils.getInstance().setUserImg(bean.getUserImg());
                        user_name.setText(SettingUtils.getInstance().getUsername());
                        user_phone.setText(SettingUtils.getInstance().getPhone());
                        GlideUtils.getInstance().loadCircleIcon(getActivity(), SettingUtils.getInstance().getUserImg(), R.drawable.user_default_icon, user_avatar);
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

    @Event(value = {R.id.user_avatar, R.id.address_layout, R.id.collection_layout, R.id.user_setting, R.id.user_info_layout, R.id.auth_layout
            , R.id.invite_layout, R.id.coupon_layout, R.id.order_detail_layout, R.id.pre_order_layout, R.id.dry_layout, R.id.invite_layout})
    private void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.invite_layout:
                intent = new Intent(getActivity(), UserInvitePageActivity.class);
                startActivity(intent);
                break;
            case R.id.user_avatar:
                if (TextUtils.isEmpty(SettingUtils.getInstance().getCurrentUid())) {
                    intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    intent = new Intent(getActivity(), PersonalActivity.class);
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
                    intent = new Intent(getActivity(), DryingActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }
}
