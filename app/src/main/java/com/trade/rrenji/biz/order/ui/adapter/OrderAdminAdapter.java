package com.trade.rrenji.biz.order.ui.adapter;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.trade.rrenji.R;
import com.trade.rrenji.bean.order.NetOrderBean;
import com.trade.rrenji.bean.order.NetOrderBean.ResultBean.MyOrderListBean;
import com.trade.rrenji.fragment.RecyclerListAdapter;
import com.trade.rrenji.utils.GlideUtils;

import java.util.List;


/**
 * Created by wheat on 16/1/14.
 */
public class OrderAdminAdapter extends RecyclerListAdapter<NetOrderBean.ResultBean.MyOrderListBean> {

    private static final int ACTION_DEFAULT = 0;

    private static final int ACTION_DEL = 1;

    private Activity mContext;

    private OnClickSetDefaultListener onClickSetDefaultListener;

    private OnClickDelListener onClickDelListener;

    private onClickListener onClickListener;

    public void setOnClickListener(OrderAdminAdapter.onClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setOnClickDelListener(OnClickDelListener onClickDelListener) {
        this.onClickDelListener = onClickDelListener;
    }

    public void setOnClickSetDefaultListener(OnClickSetDefaultListener onClickSetDefaultListener) {
        this.onClickSetDefaultListener = onClickSetDefaultListener;
    }

    public void removeOrder(String packId) {
        List<MyOrderListBean> beans = getDataSet();
        for (MyOrderListBean bean : beans) {
            if (TextUtils.equals(bean.getOrderId(), packId)) {
                remove(bean);
                break;
            }
        }
    }

    public OrderAdminAdapter(Activity context) {
        super(context);
        mContext = context;
    }

    public class OrderViewHolder extends ViewHolder {

        TextView order_name;
        TextView order_id;
        TextView order_time;
        ImageView order_image;
        TextView order_price;
        TextView order_mun;
        TextView dry_btn;

        public OrderViewHolder(View itemView) {
            super(itemView);
            order_image = (ImageView) itemView.findViewById(R.id.order_image);
            order_name = (TextView) itemView.findViewById(R.id.order_name);
            order_id = (TextView) itemView.findViewById(R.id.order_id);
            order_time = (TextView) itemView.findViewById(R.id.order_time);
            order_price = (TextView) itemView.findViewById(R.id.order_price);
            order_mun = (TextView) itemView.findViewById(R.id.order_mun);
            dry_btn = (TextView) itemView.findViewById(R.id.dry_btn);

        }

        @Override
        public void bindData(final MyOrderListBean data, int position) {
            super.bindData(data, position);
            GlideUtils.getInstance().loadIcon(mContext, data.getGoodImg(), R.drawable.ic_launcher, order_image);
            order_name.setText(data.getGoodName());
            order_id.setText("订单号" + data.getGoodId());
            order_time.setText(data.getCreateOrderTime());
            order_price.setText(data.getGoodPrice());
            order_mun.setText(mContext.getString(R.string.order_mun, 1));
            dry_btn.setText("去支付");

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new OrderViewHolder(inflater.inflate(R.layout.order_list_item_laytout, parent, false));
    }

    public interface onClickListener {
        void onClick(MyOrderListBean data);
    }

    public interface OnClickSetDefaultListener {
        void onClick(String addressId);
    }

    public interface OnClickDelListener {
        void onClick(String addressId);
    }

}
