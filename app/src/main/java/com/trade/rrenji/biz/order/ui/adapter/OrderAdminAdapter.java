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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gelitenight.superrecyclerview.LinearSpacingDecoration;
import com.trade.rrenji.R;
import com.trade.rrenji.bean.order.LocalOrderInfoBean;
import com.trade.rrenji.bean.order.NetOrderBean;
import com.trade.rrenji.bean.order.NetOrderBean.DataBean.ResultListBean;
import com.trade.rrenji.biz.order.ui.activity.DryingActivity;
import com.trade.rrenji.biz.order.ui.activity.LogisticsActivity;
import com.trade.rrenji.biz.order.ui.activity.PayConfirmOrderActivity2;
import com.trade.rrenji.fragment.RecyclerListAdapter;
import com.trade.rrenji.utils.GlideUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by wheat on 16/1/14.
 */
public class OrderAdminAdapter extends RecyclerListAdapter<NetOrderBean.DataBean.ResultListBean> {

    private static final int ACTION_DEFAULT = 0;

    private static final int ACTION_DEL = 1;

    private Activity mContext;

    private onClickListener onClickListener;

    private OnClickConfirmOrderListener onClickConfirmOrderListener;

    private onClickDelListener onClickDelListener;

    public void setOnClickDelListener(OrderAdminAdapter.onClickDelListener onClickDelListener) {
        this.onClickDelListener = onClickDelListener;
    }

    public void setOnClickConfirmOrderListener(OnClickConfirmOrderListener onClickConfirmOrderListener) {
        this.onClickConfirmOrderListener = onClickConfirmOrderListener;
    }

    private int mType = -1;

    public void setType(int mType) {
        this.mType = mType;
    }

    public void setOnClickListener(OrderAdminAdapter.onClickListener onClickListener) {
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

    public OrderAdminAdapter(Activity context) {
        super(context);
        mContext = context;
    }

    public class OrderViewHolder extends ViewHolder {

        RecyclerView recycler_view;
        RelativeLayout main_layout;
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
            main_layout = (RelativeLayout) itemView.findViewById(R.id.main_layout);
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
            if (data.getPayStatus().equals("1")) {
                del_btn.setVisibility(View.VISIBLE);
                dry_btn.setText("去支付");
                order_states.setText("待支付");
            } else if (data.getPayStatus().equals("2")) {
                del_btn.setVisibility(View.GONE);
                dry_btn.setText("查看订单");
                order_states.setText("待发货");
            } else if (data.getPayStatus().equals("3")) {
                del_btn.setVisibility(View.GONE);
                dry_btn.setText("确定收货");
                order_states.setText("已发货");
            } else if (data.getPayStatus().equals("4")) {
                del_btn.setVisibility(View.GONE);
                dry_btn.setText("晒单");
                order_states.setText("已收货");
            }
            del_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickDelListener != null) {
                        onClickDelListener.onClick(data);
                    }
                }
            });
            main_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OrderViewHolder.this.onClick(data);
                }
            });
//            dry_btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (onClickConfirmOrderListener != null) {
//                        onClickConfirmOrderListener.onClick(data.getOrderId());
//                    }
//                }
//            });
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
            itemAdapter.setOnClickItemListener(new OnClickItemListener() {
                @Override
                public void onClick() {
                    OrderViewHolder.this.onClick(data);
                }
            });
        }

        private void onClick(final ResultListBean data) {
            if (data.getPayStatus().equals("1")) {
                int mSumCount = 0;
                mSumCount = 1 + data.getAccessoryList().size();
                Intent intent = new Intent(mContext, PayConfirmOrderActivity2.class);
                intent.putExtra("GoodsDetailBean", (Serializable) data);
                intent.putExtra("mSumPrice", data.getOrderSum());
                intent.putExtra("mSumCount", mSumCount);
                mContext.startActivity(intent);
            } else if (data.getPayStatus().equals("2") || data.getPayStatus().equals("3")) {
                Intent intent = new Intent(mContext, LogisticsActivity.class);
                intent.putExtra("data", (Serializable) data);
                intent.putExtra("mType", data.getPayStatus());
                mContext.startActivity(intent);
            } else if (data.getPayStatus().equals("4")) {
                Intent intent = new Intent(mContext, DryingActivity.class);
                intent.putExtra("orderId", data.getOrderId());
                intent.putExtra("goodsImg", data.getGoodsImg());
                mContext.startActivity(intent);
            }
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
        OnClickItemListener onClickItemListener;

        public void setOnClickItemListener(OnClickItemListener onClickItemListener) {
            this.onClickItemListener = onClickItemListener;
        }

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
            GlideUtils.getInstance().loadImageUrl(mContext, data.getImg(), R.drawable.ic_launcher, holder.order_image);
            holder.order_name.setText(data.getGoodsName());
            holder.order_price.setText("￥" + data.getPayPrice());
            holder.item_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickItemListener != null) {
                        onClickItemListener.onClick();
                    }
                }
            });
        }
    }

    public interface OnClickItemListener {
        void onClick();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView order_name;
        ImageView order_image;
        TextView order_price;
        RelativeLayout item_layout;

        public ItemViewHolder(View itemView) {
            super(itemView);
            item_layout = (RelativeLayout) itemView.findViewById(R.id.item_layout);
            order_image = (ImageView) itemView.findViewById(R.id.order_image);
            order_name = (TextView) itemView.findViewById(R.id.order_name);
            order_price = (TextView) itemView.findViewById(R.id.order_price);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new OrderViewHolder(inflater.inflate(R.layout.order_list_item_laytout, parent, false));
    }

    public interface onClickListener {
        void onClick(ResultListBean data);
    }

    public interface onClickDelListener {
        void onClick(ResultListBean data);
    }

    public interface OnClickConfirmOrderListener {
        void onClick(String orderId);
    }
}
