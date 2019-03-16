package com.trade.rrenji.biz.goods.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.trade.rrenji.R;
import com.trade.rrenji.bean.goods.GoodsDetailBean;
import com.trade.rrenji.fragment.RecyclerListAdapter;
import com.trade.rrenji.utils.GlideUtils;

public class RecyclerImageAdapter extends RecyclerListAdapter<GoodsDetailBean.GoodsPicsBean> {

    private Context mContext;

    public RecyclerImageAdapter(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View convertView = inflater.inflate(R.layout.goods_detail_recycler_list_item, parent, false);
        ViewHolder holder = new DryViewHolder(convertView);
        return holder;
    }


    class DryViewHolder extends ViewHolder {

        ImageView tect_list_image;


        public DryViewHolder(View itemView) {
            super(itemView);
            tect_list_image = (ImageView) itemView.findViewById(R.id.goods_detail_image);
        }

        @Override
        public void bindData(GoodsDetailBean.GoodsPicsBean data, int position) {
            super.bindData(data, position);
            GlideUtils.getInstance().loadImageUrl(getContext(), data.getMaxPic(), R.drawable.main_recommed_today, tect_list_image);
        }
    }
}
