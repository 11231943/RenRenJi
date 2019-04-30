package com.trade.rrenji.biz.order.ui.adapter;

import android.app.Activity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.trade.rrenji.R;
import com.trade.rrenji.bean.order.LocalOrderInfoBean;
import com.trade.rrenji.bean.order.NetOrderBean.DataBean.ResultListBean;
import com.trade.rrenji.fragment.RecyclerListAdapter;
import com.trade.rrenji.utils.GlideUtils;

import java.util.List;


/**
 * Created by wheat on 16/1/14.
 */
public class PayOrderAdminAdapter extends RecyclerListAdapter<LocalOrderInfoBean> {

    private static final int ACTION_DEFAULT = 0;

    private static final int ACTION_DEL = 1;

    private Activity mContext;

    private onClickListener onClickListener;

    public void setOnClickListener(PayOrderAdminAdapter.onClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void removeOrder(String packId) {
        List<LocalOrderInfoBean> beans = getDataSet();
        for (LocalOrderInfoBean bean : beans) {
            if (TextUtils.equals(bean.getOrderId(), packId)) {
                remove(bean);
                break;
            }
        }
    }

    public PayOrderAdminAdapter(Activity context) {
        super(context);
        mContext = context;
    }

    public class OrderViewHolder extends ViewHolder {

        TextView order_name;
        ImageView order_image;
        TextView order_price;

        public OrderViewHolder(View itemView) {
            super(itemView);
            order_image = (ImageView) itemView.findViewById(R.id.order_image);
            order_name = (TextView) itemView.findViewById(R.id.order_name);
            order_price = (TextView) itemView.findViewById(R.id.order_price);
        }

        @Override
        public void bindData(final LocalOrderInfoBean data, int position) {
            super.bindData(data, position);
            GlideUtils.getInstance().loadImageUrl(mContext, data.getImg(), R.drawable.ic_launcher, order_image);
            order_name.setText(data.getGoodsName());
            order_price.setText("￥" + data.getGoodsPrice());
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new OrderViewHolder(inflater.inflate(R.layout.order_list_item, parent, false));
    }

    public interface onClickListener {
        void onClick(ResultListBean data);
    }
}
