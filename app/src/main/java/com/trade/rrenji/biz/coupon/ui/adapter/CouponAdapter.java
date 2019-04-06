package com.trade.rrenji.biz.coupon.ui.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.trade.rrenji.R;
import com.trade.rrenji.bean.coupon.NetCouponBean.ResultBean.CouponListBean;
import com.trade.rrenji.fragment.RecyclerListAdapter;

import java.util.List;


/**
 * Created by wheat on 16/1/14.
 */
public class CouponAdapter extends RecyclerListAdapter<CouponListBean> {

    private Activity mContext;

    private OnClickSetDefaultListener onClickSetDefaultListener;

    private OnClickDelListener onClickDelListener;

    private onClickListener onClickListener;

    public void setOnClickListener(CouponAdapter.onClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setOnClickDelListener(OnClickDelListener onClickDelListener) {
        this.onClickDelListener = onClickDelListener;
    }

    public void setOnClickSetDefaultListener(OnClickSetDefaultListener onClickSetDefaultListener) {
        this.onClickSetDefaultListener = onClickSetDefaultListener;
    }

    public void removeAddressAdmin(int couponId) {
        List<CouponListBean> beans = getDataSet();
        for (CouponListBean bean : beans) {
            if (bean.getCouponId() == couponId) {
                remove(bean);
                break;
            }
        }
    }

    public CouponAdapter(Activity context) {
        super(context);
        mContext = context;
    }

    public class CouponViewHolder extends ViewHolder {

        TextView coupon_value;
        TextView limit_pay_value;
        TextView limit_pay_fw;
        TextView limit_pay_time;
        RelativeLayout main_layout;

        public CouponViewHolder(View itemView) {
            super(itemView);
            coupon_value = (TextView) itemView.findViewById(R.id.coupon_value);
            limit_pay_value = (TextView) itemView.findViewById(R.id.limit_pay_value);
            limit_pay_fw = (TextView) itemView.findViewById(R.id.limit_pay_fw);
            limit_pay_time = (TextView) itemView.findViewById(R.id.limit_pay_time);
            main_layout = (RelativeLayout) itemView.findViewById(R.id.main_layout);
        }

        @Override
        public void bindData(final CouponListBean data, int position) {
            super.bindData(data, position);
            coupon_value.setText("¥" + data.getCouponValue());
            limit_pay_value.setText("满" + data.getLimitPayValue() + "使用");
            limit_pay_fw.setText("适用范围：" + data.getCouponRuleList().get(0));
            limit_pay_time.setText("有效期至：" + data.getCouponRuleList().get(2).substring(data.getCouponRuleList().get(2).indexOf("至") + 1));
            main_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickListener != null) {
                        onClickListener.onClick(data);
                    }
                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new CouponViewHolder(inflater.inflate(R.layout.coupon_admin_item, parent, false));
    }


    public interface onClickListener {
        void onClick(CouponListBean data);
    }

    public interface OnClickSetDefaultListener {
        void onClick(String addressId);
    }

    public interface OnClickDelListener {
        void onClick(String addressId);
    }

}
