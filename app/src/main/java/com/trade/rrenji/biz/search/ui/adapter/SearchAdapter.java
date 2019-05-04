package com.trade.rrenji.biz.search.ui.adapter;

import android.app.Activity;
import android.content.Context;
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
import com.trade.rrenji.bean.address.NetAddressBean.ResultBean.AddressListBean;
import com.trade.rrenji.bean.search.NetSearchBean;
import com.trade.rrenji.fragment.RecyclerListAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Created by wheat on 16/1/14.
 */
public class SearchAdapter extends RecyclerListAdapter<String> {

    private onClickListener onClickListener;

    private Context mContext;

    private List<String> mHotSearchList = null;

    public void setOnClickListener(SearchAdapter.onClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }


    public SearchAdapter(Activity context) {
        super(context);
        mContext = context;
    }

    public void addAllString(List<String> hotSearchList) {
        if (mHotSearchList == null) {
            mHotSearchList = new ArrayList<String>();
        } else {
            mHotSearchList.clear();
        }
        mHotSearchList.addAll(hotSearchList);
    }


    public class AddressViewHolder extends ViewHolder {

        TextView txt_value;

        public AddressViewHolder(View itemView) {
            super(itemView);
            txt_value = (TextView) itemView.findViewById(R.id.txt_value);
        }

        @Override
        public void bindData(final String data, int position) {
            super.bindData(data, position);
            txt_value.setText(data);
            txt_value.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickListener != null) {
                        onClickListener.onClick(data);
                    }
                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new AddressViewHolder(inflater.inflate(R.layout.serch_moren_item, parent, false));
    }

    public interface onClickListener {
        void onClick(String data);
    }
}
