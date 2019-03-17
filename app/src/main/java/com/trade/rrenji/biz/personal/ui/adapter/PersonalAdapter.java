package com.trade.rrenji.biz.personal.ui.adapter;

import android.app.Activity;
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
import com.trade.rrenji.bean.personal.NetPersonalBean.DataBean.UserShareOrderListBean;
import com.trade.rrenji.biz.photo.ShowPhotosActivity;
import com.trade.rrenji.fragment.RecyclerListAdapter;
import com.trade.rrenji.utils.GlideUtils;

import java.util.List;


/**
 * Created by wheat on 16/1/14.
 */
public class PersonalAdapter extends RecyclerListAdapter<UserShareOrderListBean> {

    private static final int ACTION_DEFAULT = 0;

    private static final int ACTION_DEL = 1;

    private Activity mContext;

    public PersonalAdapter(Activity context) {
        super(context);
        mContext = context;
    }

    public class PersonalViewHolder extends ViewHolder {

        ImageView user_avatar;
        TextView user_name;
        TextView device_tag;
        TextView dry_address;
        TextView dry_content;
        TextView dry_time;
        RecyclerView dry_pic_recycler_view;

        public PersonalViewHolder(View itemView) {
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
        public void bindData(final UserShareOrderListBean data, int position) {
            super.bindData(data, position);
            GlideUtils.getInstance().loadCircleIcon(mContext, data.getEvaluateDesc(), R.drawable.icon_round, user_avatar);
            user_name.setText(data.getUserName());
            device_tag.setText(data.getGoodsName());
            dry_address.setText(data.getLocationCity());
            dry_content.setText(data.getEvaluateDesc());
            dry_time.setText(data.getCreateTime());

            CategoryAdapter categoryAdapter = new CategoryAdapter(data.getPicsList());
            dry_pic_recycler_view.setAdapter(categoryAdapter);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View convertView = inflater.inflate(R.layout.main_fragment_order_list_item, parent, false);
        ViewHolder holder = new PersonalViewHolder(convertView);
        return holder;
    }

    class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        List<UserShareOrderListBean.PicsListBean> mCategoryBeans;

        private String[] mPhoto = null;

        public CategoryAdapter(List<UserShareOrderListBean.PicsListBean> maleBeans) {
            mCategoryBeans = maleBeans;
            initPhotos(mCategoryBeans);
        }

        private void initPhotos(List<UserShareOrderListBean.PicsListBean> maleBeans) {
            mPhoto = new String[maleBeans.size()];
            for (int i = 0; i < maleBeans.size(); i++) {
                mPhoto[i] = maleBeans.get(i).getMaxPic();
            }
        }

        public void setCategoryBeans(List<UserShareOrderListBean.PicsListBean> categoryBeans) {
            mCategoryBeans = categoryBeans;
            notifyDataSetChanged();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.order_adapter_grid_receiver_item, parent, false);
//            View view = LayoutInflater.from(mContext).inflate(R.layout.order_adapter_grid_receiver_item, parent, false);
            return new CategoryAdapter.ItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            CategoryAdapter.ItemViewHolder viewHolder = (CategoryAdapter.ItemViewHolder) holder;
            final UserShareOrderListBean.PicsListBean maleBean = mCategoryBeans.get(position);
            GlideUtils.getInstance().loadRFeIamge(mContext, maleBean.getMaxPic(), R.drawable.ic_launcher, viewHolder.mImageView);
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
