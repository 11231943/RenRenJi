package com.trade.rrenji.biz.category.ui.apater;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.trade.rrenji.R;

import java.util.ArrayList;
import java.util.List;

public class LeftCategoryAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<String> mDatas;
    public OnCheckedTypeChangeListener onCheckedChangeListener;

    private int mPosition = 0;

    public void setPosition(int position) {
        this.mPosition = position;
        notifyDataSetChanged();
    }

    public void setOnCheckedChangeListener(OnCheckedTypeChangeListener onCheckedChangeListener) {
        this.onCheckedChangeListener = onCheckedChangeListener;
    }

    //MyAdapter需要一个Context，通过Context获得Layout.inflater，然后通过inflater加载item的布局
    public LeftCategoryAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        if (mDatas == null) {
            mDatas = new ArrayList<String>();
        }
    }

    public void addData(List<String> data) {
        mDatas.clear();
        mDatas.addAll(data);
        notifyDataSetChanged();
    }

    //返回数据集的长度
    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //这个方法才是重点，我们要为它编写一个ViewHolder
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.category_left_item, parent, false); //加载布局
            holder = new ViewHolder();
            holder.titleTv = (TextView) convertView.findViewById(R.id.category_title);
            convertView.setTag(holder);
        } else {   //else里面说明，convertView已经被复用了，说明convertView中已经设置过tag了，即holder
            holder = (ViewHolder) convertView.getTag();
        }
        final String typeName = mDatas.get(position);
        if (mPosition == position) {
            holder.titleTv.setBackgroundColor(Color.WHITE);
        } else {
            holder.titleTv.setBackgroundColor(Color.parseColor("#f2f2f2"));
        }
        holder.titleTv.setText(typeName);
        holder.titleTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onCheckedChangeListener != null) {
                    onCheckedChangeListener.onCheckedChangeType(typeName, position);
                    mPosition = position;
                }
            }
        });
        return convertView;
    }

    public interface OnCheckedTypeChangeListener {
        void onCheckedChangeType(String name, int position);
    }

    private class ViewHolder {
        TextView titleTv;
    }

}
