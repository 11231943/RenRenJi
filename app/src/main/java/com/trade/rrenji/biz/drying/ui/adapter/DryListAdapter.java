package com.trade.rrenji.biz.drying.ui.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gelitenight.superrecyclerview.GridSpacingDecoration;
import com.trade.rrenji.R;
import com.trade.rrenji.bean.drying.DryingOrdersBean;
import com.trade.rrenji.bean.drying.NetShareBean;
import com.trade.rrenji.bean.drying.SharePicturesBean;
import com.trade.rrenji.fragment.RecyclerListAdapter;
import com.trade.rrenji.utils.GlideUtils;

import java.util.List;

public class DryListAdapter extends RecyclerListAdapter<NetShareBean.ResultBean.ShareOrdersBean> {

    private Context mContext;

    public DryListAdapter(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View convertView = inflater.inflate(R.layout.main_fragment_order_list_item, parent, false);
        ViewHolder holder = new DryViewHolder(convertView);
        return holder;
    }


    class DryViewHolder extends ViewHolder {

        ImageView user_avatar;
        TextView user_name;
        TextView device_tag;
        TextView dry_address;
        TextView dry_content;
        TextView dry_time;
        RecyclerView dry_pic_recycler_view;

        int spanCount = 3; // 3 columns
        int spacing = 30; // 30px
        boolean includeEdge = false;

        public DryViewHolder(View itemView) {
            super(itemView);
            user_avatar = (ImageView) itemView.findViewById(R.id.user_avatar);
            user_name = (TextView) itemView.findViewById(R.id.user_name);
            device_tag = (TextView) itemView.findViewById(R.id.device_tag);
            dry_address = (TextView) itemView.findViewById(R.id.dry_address);
            dry_content = (TextView) itemView.findViewById(R.id.dry_content);
            dry_time = (TextView) itemView.findViewById(R.id.dry_time);
            dry_pic_recycler_view = (RecyclerView) itemView.findViewById(R.id.dry_pic_recycler_view);
            dry_pic_recycler_view.setHasFixedSize(true);
            dry_pic_recycler_view.setLayoutManager(new GridLayoutManager(mContext,3));
            dry_pic_recycler_view.addItemDecoration(new GridSpacingDecoration(20, 20));
        }

        @Override
        public void bindData(NetShareBean.ResultBean.ShareOrdersBean data, int position) {
            super.bindData(data, position);
            GlideUtils.getInstance().loadCircleIcon(mContext, data.getComment(), R.drawable.ic_launcher, user_avatar);
            user_name.setText(data.getUserName());
            device_tag.setText(data.getPhoneDesc());
            dry_address.setText(data.getLocation());
            dry_content.setText(data.getComment());
            dry_time.setText(data.getShareTime());

            CategoryAdapter categoryAdapter =new CategoryAdapter(data.getSharePictures());
            dry_pic_recycler_view.setAdapter(categoryAdapter);
        }
    }

    class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        List<NetShareBean.ResultBean.ShareOrdersBean.SharePicturesBean> mCategoryBeans;

        public CategoryAdapter(List<NetShareBean.ResultBean.ShareOrdersBean.SharePicturesBean> maleBeans) {
            mCategoryBeans = maleBeans;
        }

        public void setCategoryBeans(List<NetShareBean.ResultBean.ShareOrdersBean.SharePicturesBean> categoryBeans) {
            mCategoryBeans = categoryBeans;
            notifyDataSetChanged();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.order_adapter_grid_receiver_item, parent, false);
//            View view = LayoutInflater.from(mContext).inflate(R.layout.order_adapter_grid_receiver_item, parent, false);
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ItemViewHolder viewHolder = (ItemViewHolder) holder;
            final NetShareBean.ResultBean.ShareOrdersBean.SharePicturesBean maleBean = mCategoryBeans.get(position);
            GlideUtils.getInstance().loadRFeIamge(mContext, maleBean.getLargePic(), R.drawable.ic_launcher, viewHolder.mImageView);
        }

        @Override
        public int getItemCount() {
            return mCategoryBeans.size();
        }

        class ItemViewHolder extends RecyclerView.ViewHolder {
            ImageView mImageView;

            public ItemViewHolder(View itemView) {
                super(itemView);
                mImageView = (ImageView) itemView.findViewById(R.id.order_image);
            }
        }
    }

}
