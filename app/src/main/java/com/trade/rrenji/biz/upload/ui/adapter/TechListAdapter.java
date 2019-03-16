package com.trade.rrenji.biz.upload.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.trade.rrenji.R;
import com.trade.rrenji.bean.drying.NetShareBean;
import com.trade.rrenji.bean.tech.NetTechBean;
import com.trade.rrenji.biz.ad.AdActivity;
import com.trade.rrenji.fragment.RecyclerListAdapter;
import com.trade.rrenji.utils.GlideUtils;

import java.util.List;

public class TechListAdapter extends RecyclerListAdapter<NetTechBean.ResultBean.CommunityListBean> {

    private Context mContext;

    public TechListAdapter(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View convertView = inflater.inflate(R.layout.main_fragment_tech_list_item, parent, false);
        ViewHolder holder = new DryViewHolder(convertView);
        return holder;
    }


    class DryViewHolder extends ViewHolder {

        ImageView tect_list_image;
        TextView tect_list_name;
        TextView tect_list_content1;
        TextView tect_list_content2;
        TextView readingNum;
        TextView praiseNumber;
        RelativeLayout zan_layout;
        RelativeLayout read_layout;
        RelativeLayout main_layout;

        public DryViewHolder(View itemView) {
            super(itemView);
            zan_layout = (RelativeLayout) itemView.findViewById(R.id.zan_layout);
            read_layout = (RelativeLayout) itemView.findViewById(R.id.read_layout);
            main_layout = (RelativeLayout) itemView.findViewById(R.id.main_layout);
            tect_list_image = (ImageView) itemView.findViewById(R.id.tect_list_image);
            tect_list_name = (TextView) itemView.findViewById(R.id.tect_list_name);
            tect_list_content1 = (TextView) itemView.findViewById(R.id.tect_list_content1);
            tect_list_content2 = (TextView) itemView.findViewById(R.id.tect_list_content2);
            praiseNumber = (TextView) itemView.findViewById(R.id.praiseNumber);
            readingNum = (TextView) itemView.findViewById(R.id.readingNum);

        }

        @Override
        public void bindData(final NetTechBean.ResultBean.CommunityListBean data, int position) {
            super.bindData(data, position);
            GlideUtils.getInstance().loadImageUrl(getContext(), data.getShowUrl(), R.drawable.main_recommed_today, tect_list_image);
            tect_list_name.setText(data.getTitle());
            tect_list_content1.setText(data.getReadingNum() + "");
//            String time = DateFormatUtils.getStringToDate(data.getPublishTime());
            //DateFormatUtils.translateUTCToDate( data.publishTime,DateFormatUtils.DateConstants.FORMAT_YEAR_MONTH_DATE_HOUR_MINUTE);
            praiseNumber.setText(data.getPraiseNumber());
            readingNum.setText(data.getReadingNum());
            main_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!TextUtils.isEmpty(data.getActicleUrl())) {
                        AdActivity.start(mContext, data.getActicleUrl());
                    } else {
                        Toast.makeText(mContext, "无效的url,请检查网址有效", Toast.LENGTH_SHORT).show();
                    }

                }
            });
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
