package com.trade.rrenji.biz.goods.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gelitenight.superrecyclerview.GridSpacingDecoration;
import com.trade.rrenji.R;
import com.trade.rrenji.bean.goods.RecyCommentBean;
import com.trade.rrenji.biz.photo.ShowPhotosActivity;
import com.trade.rrenji.fragment.RecyclerListAdapter;
import com.trade.rrenji.utils.GlideUtils;

import java.util.ArrayList;
import java.util.List;

public class ReplyCommentAdapter extends RecyclerListAdapter<RecyCommentBean.ResultBean.EvaluatelistBean> {

    private Context mContext;

    public ReplyCommentAdapter(Context context) {
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
            dry_pic_recycler_view.setLayoutManager(new GridLayoutManager(mContext, 3));
            dry_pic_recycler_view.addItemDecoration(new GridSpacingDecoration(20, 20));
        }

        @Override
        public void bindData(RecyCommentBean.ResultBean.EvaluatelistBean data, int position) {
            super.bindData(data, position);
            GlideUtils.getInstance().loadCircleIcon(mContext, data.getUserName(), R.drawable.icon_round, user_avatar);
            user_name.setText(data.getUserName());
            device_tag.setText(data.getUserName());
            dry_address.setText("");
            dry_content.setText(data.getEvaluateDesc());
            dry_time.setText(data.getShareTime());

            CategoryAdapter categoryAdapter = new CategoryAdapter(data.getSharePictures());
            dry_pic_recycler_view.setAdapter(categoryAdapter);
        }
    }

    class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        List<RecyCommentBean.ResultBean.EvaluatelistBean.SharePicturesBean> mCategoryBeans;

        private String[] mPhoto = null;

        public CategoryAdapter(List<RecyCommentBean.ResultBean.EvaluatelistBean.SharePicturesBean> maleBeans) {
            mCategoryBeans = maleBeans;
            initPhotos(mCategoryBeans);
        }

        private void initPhotos(List<RecyCommentBean.ResultBean.EvaluatelistBean.SharePicturesBean> maleBeans) {
            if (maleBeans != null && maleBeans.size() > 0) {
                mPhoto = new String[maleBeans.size()];
                for (int i = 0; i < maleBeans.size(); i++) {
                    mPhoto[i] = maleBeans.get(i).getMaxPic();
                }
            }
        }

        public void setCategoryBeans(List<RecyCommentBean.ResultBean.EvaluatelistBean.SharePicturesBean> categoryBeans) {
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
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            ItemViewHolder viewHolder = (ItemViewHolder) holder;
            final RecyCommentBean.ResultBean.EvaluatelistBean.SharePicturesBean maleBean = mCategoryBeans.get(position);
            GlideUtils.getInstance().loadRFeIamge(mContext, maleBean.getMinPic(), R.drawable.ic_launcher, viewHolder.mImageView);
            viewHolder.mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ShowPhotosActivity.class);
                    intent.putExtra("photo", mPhoto);
                    intent.putExtra("mPostion", position);
                    mContext.startActivity(intent);
                }
            });

        }

        @Override
        public int getItemCount() {
            return mCategoryBeans == null ? 0 : mCategoryBeans.size();
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
