package com.trade.rrenji.biz.im.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.trade.rrenji.R;
import com.trade.rrenji.fragment.RecyclerListAdapter;
import com.trade.rrenji.utils.DateFormatUtils;

import java.util.Date;

import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.enums.MessageDirect;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.model.UserInfo;

public class ChatAdapter extends RecyclerListAdapter<Message> {

    private Context mContext;

    //广告ITEM
    public final static int LEFT_ITEM = 0;
    //类别部分
    public final static int RIGHT_ITEM = 1;


    public ChatAdapter(Activity context) {
        super(context);
        mContext = context;
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (getItem(position).getDirect().equals(MessageDirect.send)) {
            return RIGHT_ITEM;
        } else if (getItem(position).getDirect().equals(MessageDirect.receive)) {
            return LEFT_ITEM;
        }
        return -1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        Log.e("onCreateViewHolder", "viewType = " + viewType);
        View convertView = inflater.inflate(createViewByType(viewType), parent, false);
        ViewHolder holder = null;
        switch (viewType) {
            case LEFT_ITEM:
                holder = new LeftViewHolder(convertView);
                break;
            case RIGHT_ITEM:
                holder = new RightViewHolder(convertView);
                break;
        }
        return holder;
    }

    protected int createViewByType(int type) {
        int resId;
        switch (type) {
            case LEFT_ITEM:
                resId = R.layout.chat_letf_bg;
                break;
            case RIGHT_ITEM:
                resId = R.layout.chat_right_bg;
                break;
            default:
                resId = -1;
                return resId;
        }
        return resId;
    }

    public class RightViewHolder extends ViewHolder {
        TextView mUserAccount;
        TextView mContent;

        public RightViewHolder(View itemView) {
            super(itemView);
            mUserAccount = itemView.findViewById(R.id.user_account);
            mContent = itemView.findViewById(R.id.content);
        }

        @Override
        public void bindData(Message data, int position) {
            super.bindData(data, position);
            mUserAccount.setText(data.getFromUser().getNickname().length() > 0 ? data.getFromUser().getNickname() : data.getFromUser().getUserName());
            TextContent stringExtra = (TextContent) data.getContent();
            mContent.setText(stringExtra.getText());
        }
    }


    public class LeftViewHolder extends ViewHolder {
        TextView mUserAccount;
        TextView mContent;
        TextView mTime;

        public LeftViewHolder(View itemView) {
            super(itemView);
            mUserAccount = itemView.findViewById(R.id.user_account);
            mContent = itemView.findViewById(R.id.content);
            mTime = itemView.findViewById(R.id.time);
        }

        @Override
        public void bindData(Message data, int position) {
            super.bindData(data, position);
            mUserAccount.setText(data.getFromUser().getNickname().length() > 0 ? data.getFromUser().getNickname() : data.getFromUser().getUserName());
            TextContent stringExtra = (TextContent) data.getContent();
            mContent.setText(stringExtra.getText());
            mTime.setText(DateFormatUtils.getTimeStringAutoShort2(new Date(data.getCreateTime()), true));
        }
    }
}
