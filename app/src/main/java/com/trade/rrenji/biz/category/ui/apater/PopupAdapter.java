package com.trade.rrenji.biz.category.ui.apater;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gelitenight.superrecyclerview.LinearSpacingDecoration;
import com.trade.rrenji.R;
import com.trade.rrenji.bean.category.BaseBean;
import com.trade.rrenji.bean.category.PopupCategoryBean;
import com.trade.rrenji.bean.order.NetOrderBean;
import com.trade.rrenji.fragment.RecyclerListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PopupAdapter extends RecyclerListAdapter<PopupCategoryBean> {

    private Context mContext;

    //广告ITEM
    public final static int BANNER_ITEM = 0;
    //
    public final static int DATE_ITEM = 1;

    BaseDataViewHolder.CategoryDataAdapter mCategoryAdapter;

    onClickListener mOnClickListener;

    private List<List<BaseBean>> mAllList = new ArrayList<List<BaseBean>>();

    public void resetColor() {
        if (mAllList != null) {
            for (int i = 0; i < mAllList.size(); i++) {
                for (int j = 0; j < mAllList.get(i).size(); j++) {
                    mAllList.get(i).get(j).setSelected(false);
                }
            }
        }
        mCategoryAdapter.setCurrent(-1);
        notifyDataSetChanged();
    }

    public void setOnClickListener(onClickListener mOnClickListener) {
        this.mOnClickListener = mOnClickListener;
    }

    public PopupAdapter(Context context) {
        super(context);
        mContext = context;
    }


    @Override
    public int getItemViewType(int position) {
        return getItem(position).getType();
    }

    public void addAllData(List<PopupCategoryBean> beans) {
        clear();
        addAll(beans);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        Log.e("onCreateViewHolder", "viewType = " + viewType);
        View convertView = inflater.inflate(createViewByType(viewType), parent, false);
        ViewHolder holder = null;
        switch (viewType) {
            case BANNER_ITEM:
                holder = new BannerViewHolder(convertView);
                break;
            case DATE_ITEM:
                holder = new BaseDataViewHolder(convertView);
                break;
        }
        return holder;
    }

    protected int createViewByType(int type) {
        int resId;
        switch (type) {
            case BANNER_ITEM:
                resId = R.layout.screen_banner_data;
                break;
            case DATE_ITEM:
                resId = R.layout.screen_category_data;
                break;
            default:
                resId = -1;
                return resId;
        }
        return resId;
    }

    public class BannerViewHolder extends ViewHolder {

        TextView type_name;

        public BannerViewHolder(View itemView) {
            super(itemView);
            type_name = itemView.findViewById(R.id.type_name);
        }

        @Override
        public void bindData(PopupCategoryBean data, int position) {
            super.bindData(data, position);
            type_name.setText(data.getTypeName());
        }
    }

    public class BaseDataViewHolder extends ViewHolder {

        RecyclerView right_base_recycler_view;

        public BaseDataViewHolder(View itemView) {
            super(itemView);
            right_base_recycler_view = itemView.findViewById(R.id.screen_recycler);
        }

        @Override
        public void bindData(PopupCategoryBean data, int position) {
            super.bindData(data, position);
            mCategoryAdapter = new CategoryDataAdapter(mContext);
            right_base_recycler_view.setAdapter(mCategoryAdapter);
            right_base_recycler_view.addItemDecoration(new LinearSpacingDecoration(0, 0));
            right_base_recycler_view.setLayoutManager(new GridLayoutManager(mContext, 3));
            mCategoryAdapter.addAll(data.getBaseBeans());
        }


        private class CategoryDataAdapter extends RecyclerView.Adapter<CategoryDataViewItemHolder> {


            Context mContext;
            List<BaseBean> mCategoryList;

            private int mCurrent = -1;

            public void setCurrent(int mCurrent) {
                this.mCurrent = mCurrent;
            }

            public CategoryDataAdapter(Context context) {
                mContext = context;
            }

            public void addAll(List<BaseBean> categoryList) {
                if (mCategoryList == null) {
                    mCategoryList = new ArrayList<BaseBean>();
                } else {
                    mCategoryList.clear();
                }
                mCategoryList.addAll(categoryList);
                mAllList.add(mCategoryList);
                notifyDataSetChanged();
            }

            @Override
            public int getItemCount() {
                return mCategoryList.size();
            }

            @Override
            public CategoryDataViewItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(mContext).inflate(R.layout.screen_list_item, parent, false);
                return new CategoryDataViewItemHolder(view);

            }

            @Override
            public void onBindViewHolder(CategoryDataViewItemHolder holder, final int position) {
                final BaseBean bean = mCategoryList.get(position);
                if (bean.isSelected()) {
                    holder.item_text.setBackgroundResource(R.drawable.category_item_bg_p);
                } else {
                    holder.item_text.setBackgroundResource(R.drawable.category_item_bg);
                }
                holder.item_text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mCurrent != position) {
                            mCategoryList.get(position).setSelected(true);
                            if (mCurrent != -1) {
                                mCategoryList.get(mCurrent).setSelected(false);
                            }
                            if (mOnClickListener != null) {
                                mOnClickListener.onClick(bean);
                            }
                            mCurrent = position;
                            notifyDataSetChanged();
                        } else {
                            mCurrent = -1;
                            mCategoryList.get(position).setSelected(false);
                            if (mOnClickListener != null) {
                                mOnClickListener.onCancelClick(bean);
                            }
                            notifyDataSetChanged();
                        }
                    }
                });
                holder.item_text.setText(bean.getName().trim());
            }
        }

        public class CategoryDataViewItemHolder extends RecyclerView.ViewHolder {

            private TextView item_text;

            public CategoryDataViewItemHolder(View itemView) {
                super(itemView);
                item_text = (TextView) itemView.findViewById(R.id.item_text);
            }
        }
    }

    public interface onClickListener {
        void onClick(BaseBean data);

        void onCancelClick(BaseBean data);
    }


}
