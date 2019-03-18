package com.trade.rrenji.biz.category.ui.apater;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.trade.rrenji.R;
import com.trade.rrenji.bean.category.NetCategoryListBean.DataBean.ResultListBean;
import com.trade.rrenji.biz.goods.ui.activity.GoodsDetailActivity;
import com.trade.rrenji.fragment.RecyclerListAdapter;
import com.trade.rrenji.utils.GlideUtils;

import java.util.List;

public class CategoryListAdapter extends RecyclerListAdapter<ResultListBean> {

    Context mContext;
    List<ResultListBean> mCategoryList;

    public CategoryListAdapter(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new CategoryListViewHolder(inflater.inflate(R.layout.home_hot_android_item, parent, false));
    }

    public class CategoryListViewHolder extends ViewHolder {
        private TextView hot_iphone_text;
        private TextView iphone_original_price;
        private TextView iphone_save_price;
        private TextView iphone_price;
        private TextView iphone_net;
        private TextView iphone_color;
        private TextView phone_size;
        private ImageView hot_iphone_image;
        private ImageView hot_data_image;
        RelativeLayout main_layout;

        public CategoryListViewHolder(View itemView) {
            super(itemView);
            hot_iphone_text = (TextView) itemView.findViewById(R.id.hot_iphone_text);
            iphone_original_price = (TextView) itemView.findViewById(R.id.iphone_original_price);
            iphone_save_price = (TextView) itemView.findViewById(R.id.iphone_save_price);
            iphone_price = (TextView) itemView.findViewById(R.id.iphone_price);
            iphone_net = (TextView) itemView.findViewById(R.id.iphone_net);
            iphone_color = (TextView) itemView.findViewById(R.id.iphone_color);
            phone_size = (TextView) itemView.findViewById(R.id.phone_size);
            hot_iphone_image = (ImageView) itemView.findViewById(R.id.hot_iphone_image);
            main_layout = (RelativeLayout) itemView.findViewById(R.id.main_layout);
            hot_data_image = (ImageView) itemView.findViewById(R.id.hot_data_image);
        }

        @Override
        public void bindData(final ResultListBean data, int position) {
            super.bindData(data, position);
            GlideUtils.getInstance().loadImageUrl(mContext, data.getDiscoverImg(), R.drawable.ic_launcher, hot_data_image);
            hot_iphone_text.setText(data.getGoodsName());
            iphone_original_price.setText("￥" + data.getOriginalPrice());
            iphone_save_price.setText("" + (data.getOriginalPrice() - data.getGoodsPrice()));
            iphone_price.setText("￥" + data.getGoodsPrice() + "");
            if (TextUtils.isEmpty(data.getVersion())) {
                iphone_net.setVisibility(View.GONE);
            } else {
                iphone_net.setVisibility(View.VISIBLE);
                iphone_net.setText(data.getVersion());
            }
            iphone_color.setText(data.getColor());
            phone_size.setText(data.getMemory());
            main_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GoodsDetailActivity.navToMainActivity(mContext, data.getGoodsCode());
                }
            });

        }
    }

}




