package com.trade.rrenji.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gelitenight.superrecyclerview.SuperRecyclerView;
import com.trade.rrenji.R;
import com.trade.rrenji.biz.account.ui.activity.AccountActivity;
import com.trade.rrenji.biz.account.ui.activity.LoginActivity;
import com.trade.rrenji.biz.address.ui.activity.AddressAdminActivity;
import com.trade.rrenji.biz.auth.ui.AuthActivity;
import com.trade.rrenji.biz.collection.ui.activity.CollectionActivity;
import com.trade.rrenji.biz.coupon.ui.activity.CouponActivity;
import com.trade.rrenji.biz.order.ui.activity.DryingActivity;
import com.trade.rrenji.biz.order.ui.activity.OrderActivity;
import com.trade.rrenji.biz.order.ui.activity.OrderAllActivity;
import com.trade.rrenji.biz.setting.ui.SettingActivity;
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
        init();
    }

    private void init() {
        if(TextUtils.isEmpty(SettingUtils.getInstance().getCurrentUid())){
            login_not.setVisibility(View.VISIBLE);
            edit_account.setVisibility(View.GONE);
            user_name.setVisibility(View.GONE);
            user_phone.setVisibility(View.GONE);
        }else{
            edit_account.setVisibility(View.VISIBLE);
            user_name.setVisibility(View.VISIBLE);
            user_phone.setVisibility(View.VISIBLE);
            login_not.setVisibility(View.GONE);
            user_name.setText(SettingUtils.getInstance().getUsername());
            user_phone.setText(SettingUtils.getInstance().getPhone());
        }
    }

    @Event(value = {R.id.address_layout, R.id.collection_layout, R.id.user_setting, R.id.user_info_layout, R.id.auth_layout
            , R.id.coupon_layout,  R.id.order_detail_layout, R.id.pre_order_layout, R.id.dry_layout})
    private void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.address_layout:
                intent = new Intent(getActivity(), AddressAdminActivity.class);
                startActivity(intent);
                break;
            case R.id.user_setting:
                intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.collection_layout:
                intent = new Intent(getActivity(), CollectionActivity.class);
                startActivity(intent);
                break;
            case R.id.user_info_layout:
                if(TextUtils.isEmpty(SettingUtils.getInstance().getCurrentUid())){
                    intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }else{
                    intent = new Intent(getActivity(), AccountActivity.class);
                    startActivity(intent);
                }

                break;
            case R.id.auth_layout:
                intent = new Intent(getActivity(), AuthActivity.class);
                startActivity(intent);
                break;
            case R.id.coupon_layout:
                intent = new Intent(getActivity(), CouponActivity.class);
                startActivity(intent);
                break;
            case R.id.order_detail_layout:
                intent = new Intent(getActivity(), OrderAllActivity.class);
                startActivity(intent);
                break;
            case R.id.pre_order_layout:
                intent = new Intent(getActivity(), OrderActivity.class);
                startActivity(intent);
                break;
            case R.id.dry_layout:
                intent = new Intent(getActivity(), DryingActivity.class);
                startActivity(intent);
                break;
        }
    }
}
