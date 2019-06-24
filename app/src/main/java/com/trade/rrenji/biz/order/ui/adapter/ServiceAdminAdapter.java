package com.trade.rrenji.biz.order.ui.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.trade.rrenji.R;
import com.trade.rrenji.fragment.RecyclerListAdapter;
import com.trade.rrenji.utils.GlideUtils;
import com.trade.rrenji.bean.order.NetServiceBean.DataBean.ReListBean;


/**
 * Created by wheat on 16/1/14.
 */
public class ServiceAdminAdapter extends RecyclerListAdapter<ReListBean> {


    private Activity mContext;

    public ServiceAdminAdapter(Activity context) {
        super(context);
        mContext = context;
    }

    public class ServiceViewHolder extends ViewHolder {

        TextView order_name;
        TextView order_id;
        TextView order_time;
        TextView collection_tip;
        ImageView order_image;
        TextView cancel_collection;

        public ServiceViewHolder(View itemView) {
            super(itemView);
            order_image = (ImageView) itemView.findViewById(R.id.order_image);
            order_name = (TextView) itemView.findViewById(R.id.order_name);
            order_time = (TextView) itemView.findViewById(R.id.order_time);
            collection_tip = (TextView) itemView.findViewById(R.id.collection_tip);
            order_id = (TextView) itemView.findViewById(R.id.order_id);
            cancel_collection = (TextView) itemView.findViewById(R.id.cancel_collection);
        }

        @Override
        public void bindData(final ReListBean data, int position) {
            super.bindData(data, position);
            GlideUtils.getInstance().loadImageUrl(mContext, data.getCoverImg(), R.drawable.ic_launcher, order_image);
            order_name.setText(data.getGoodsName());
            order_id.setText("订单号: " + data.getGoodsCode());
            order_time.setText("创建时间: " + data.getCreateTime());
            collection_tip.setText("保修还剩：365天");

        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ServiceViewHolder(inflater.inflate(R.layout.service_list_item_laytout, parent, false));
    }

}
