package com.trade.rrenji.biz.collection.ui.adapter;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.trade.rrenji.R;
import com.trade.rrenji.bean.address.NetAddressBean;
import com.trade.rrenji.bean.address.NetAddressBean.ResultBean.AddressListBean;
import com.trade.rrenji.bean.collection.NetCollectionListBean;
import com.trade.rrenji.fragment.RecyclerListAdapter;
import com.trade.rrenji.utils.GlideUtils;

import java.util.List;


/**
 * Created by wheat on 16/1/14.
 */
public class CollectionAdapter extends RecyclerListAdapter<NetCollectionListBean.ResultBean> {

    private static final int ACTION_DEFAULT = 0;

    private static final int ACTION_DEL = 1;

    private Activity mContext;

    private OnClickSetDefaultListener onClickSetDefaultListener;

    private OnClickDelListener onClickDelListener;

    private onClickListener onClickListener;

    public void setOnClickListener(CollectionAdapter.onClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setOnClickDelListener(OnClickDelListener onClickDelListener) {
        this.onClickDelListener = onClickDelListener;
    }

    public void setOnClickSetDefaultListener(OnClickSetDefaultListener onClickSetDefaultListener) {
        this.onClickSetDefaultListener = onClickSetDefaultListener;
    }

    public void removeAddressAdmin(String packId) {
        List<NetCollectionListBean.ResultBean> beans = getDataSet();
        for (NetCollectionListBean.ResultBean bean : beans) {
            if (TextUtils.equals(bean.getId(), packId)) {
                remove(bean);
                break;
            }
        }
    }

    public CollectionAdapter(Activity context) {
        super(context);
        mContext = context;
    }

    public class AddressViewHolder extends ViewHolder {

        TextView order_name;
        TextView order_id;
        TextView order_time;
        ImageView order_image;
        TextView order_price;
        TextView order_mun;
        TextView cancel_collection;

        public AddressViewHolder(View itemView) {
            super(itemView);
            order_image = (ImageView) itemView.findViewById(R.id.order_image);
            order_name = (TextView) itemView.findViewById(R.id.order_name);
            order_id = (TextView) itemView.findViewById(R.id.order_id);
            order_time = (TextView) itemView.findViewById(R.id.order_time);
            order_price = (TextView) itemView.findViewById(R.id.order_price);
            order_mun = (TextView) itemView.findViewById(R.id.order_mun);
            cancel_collection = (TextView) itemView.findViewById(R.id.cancel_collection);
        }

        @Override
        public void bindData(final NetCollectionListBean.ResultBean data, int position) {
            super.bindData(data, position);
            GlideUtils.getInstance().loadIcon(mContext, data.getGoodsImg(), R.drawable.ic_launcher, order_image);
            order_name.setText(data.getGoodsName());
            order_id.setText("订单号" + data.getGoodsCode());
            order_price.setText(data.getGoodsPrice() + "");
            order_mun.setText(mContext.getString(R.string.order_mun, 1));
            cancel_collection.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setAddressTip(data.getId());
                }
            });
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new AddressViewHolder(inflater.inflate(R.layout.collection_list_item_laytout, parent, false));
    }

    private void setAddressTip(final String addressId) {
        new AlertDialog.Builder(mContext)
                .setMessage("是否取消收藏")
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onClickDelListener.onClick(addressId);
                    }
                })
                .setNegativeButton(R.string.cancel, null)
                .show();

    }

    public interface onClickListener {
        void onClick(AddressListBean data);
    }

    public interface OnClickSetDefaultListener {
        void onClick(String addressId);
    }

    public interface OnClickDelListener {
        void onClick(String addressId);
    }

}
