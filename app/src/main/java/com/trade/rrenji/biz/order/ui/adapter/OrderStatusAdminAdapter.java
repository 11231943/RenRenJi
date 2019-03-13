package com.trade.rrenji.biz.order.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gelitenight.superrecyclerview.LinearSpacingDecoration;
import com.trade.rrenji.R;
import com.trade.rrenji.bean.order.LocalOrderInfoBean;
import com.trade.rrenji.bean.order.NetOrderBean.DataBean.ResultListBean;
import com.trade.rrenji.fragment.RecyclerListAdapter;
import com.trade.rrenji.utils.GlideUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by wheat on 16/1/14.
 */
public class OrderStatusAdminAdapter extends RecyclerListAdapter<ResultListBean> {

    private static final int ACTION_DEFAULT = 0;

    private static final int ACTION_DEL = 1;

    private Activity mContext;

    private onClickListener onClickListener;

    public void setOnClickListener(OrderStatusAdminAdapter.onClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void removeOrder(String packId) {
        List<ResultListBean> beans = getDataSet();
        for (ResultListBean bean : beans) {
            if (TextUtils.equals(bean.getOrderId(), packId)) {
                remove(bean);
                break;
            }
        }
    }

    public OrderStatusAdminAdapter(Activity context) {
        super(context);
        mContext = context;
    }

    public class OrderViewHolder extends ViewHolder {

        RecyclerView recycler_view;
        TextView order_total;
        TextView dry_btn;
        TextView order_id;
        TextView order_time;
        TextView order_sum;
        TextView del_btn;
        TextView order_states;

        public OrderViewHolder(View itemView) {
            super(itemView);
            order_total = (TextView) itemView.findViewById(R.id.order_total);
            recycler_view = (RecyclerView) itemView.findViewById(R.id.recycler_view);
            dry_btn = (TextView) itemView.findViewById(R.id.dry_btn);
            order_id = (TextView) itemView.findViewById(R.id.order_id);
            order_time = (TextView) itemView.findViewById(R.id.order_time);
            order_sum = (TextView) itemView.findViewById(R.id.order_sum);
            del_btn = (TextView) itemView.findViewById(R.id.del_btn);
            order_states = (TextView) itemView.findViewById(R.id.order_states);
        }

        @Override
        public void bindData(final ResultListBean data, int position) {
            super.bindData(data, position);
            ItemAdapter itemAdapter = new ItemAdapter(mContext);
            order_total.setText("￥" + data.getOrderSum());
            order_id.setText("订单号: " + data.getOrderId());
            order_time.setText("创建订单时间: " + data.getCreateTime());
            order_sum.setText(mContext.getString(R.string.order_mun, (1 + data.getAccessoryList().size())));
            recycler_view.addItemDecoration(new LinearSpacingDecoration(20, 0));
            recycler_view.setAdapter(itemAdapter);
            LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
            recycler_view.setLayoutManager(layoutManager);
            itemAdapter.addAll(buildData(data));
        }
    }

    private List<LocalOrderInfoBean> buildData(ResultListBean data) {
        List<LocalOrderInfoBean> mList = new ArrayList<LocalOrderInfoBean>();
        LocalOrderInfoBean localOrderInfoBean = new LocalOrderInfoBean();
        localOrderInfoBean.setOrderId(data.getOrderId());
        localOrderInfoBean.setCreateTime(data.getCreateTime());
        localOrderInfoBean.setGoodsName(data.getGoodsName());
        localOrderInfoBean.setImg(data.getGoodsImg());
        localOrderInfoBean.setPayPrice(data.getGoodsPrice());
        mList.add(localOrderInfoBean);
        for (ResultListBean.AccessoryListBean bean : data.getAccessoryList()) {
            LocalOrderInfoBean orderInfoBean = new LocalOrderInfoBean();
            orderInfoBean.setOrderId(bean.getAccessoryId());
            orderInfoBean.setCreateTime(data.getCreateTime());
            orderInfoBean.setGoodsName(bean.getAccessoryName());
            orderInfoBean.setImg(bean.getImageUrl());
            orderInfoBean.setPayPrice(Double.valueOf(bean.getPayPrice()));
            mList.add(orderInfoBean);
        }
        return mList;
    }

    private class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {

        Context mContext;
        List<LocalOrderInfoBean> mCategoryList;

        public ItemAdapter(Context context) {
            mContext = context;
        }

        public void addAll(List<LocalOrderInfoBean> categoryList) {

            if (mCategoryList == null) {
                mCategoryList = new ArrayList<LocalOrderInfoBean>();
            } else {
                mCategoryList.clear();
            }
            mCategoryList.addAll(categoryList);
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return mCategoryList.size();
        }

        @Override
        public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.order_list_item, parent, false);
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ItemViewHolder holder, int position) {
            LocalOrderInfoBean data = mCategoryList.get(position);
            GlideUtils.getInstance().loadIcon(mContext, data.getImg(), R.drawable.ic_launcher, holder.order_image);
            holder.order_name.setText(data.getGoodsName());
            holder.order_price.setText("￥" + data.getPayPrice());
        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView order_name;

        ImageView order_image;
        TextView order_price;

        public ItemViewHolder(View itemView) {
            super(itemView);
            order_image = (ImageView) itemView.findViewById(R.id.order_image);
            order_name = (TextView) itemView.findViewById(R.id.order_name);
            order_price = (TextView) itemView.findViewById(R.id.order_price);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new OrderViewHolder(inflater.inflate(R.layout.order_status_item, parent, false));
    }

    public interface onClickListener {
        void onClick(ResultListBean data);
    }

    public interface OnClickSetDefaultListener {
        void onClick(String addressId);
    }

    public interface OnClickDelListener {
        void onClick(String addressId);
    }

}
