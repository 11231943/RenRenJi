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

import cn.jpush.im.android.api.content.MessageContent;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;

public class ChatGorupAdapter extends RecyclerListAdapter<Conversation> {

    private Context mContext;

    public ChatGorupAdapter(Activity context) {
        super(context);
        mContext = context;
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        Log.e("onCreateViewHolder", "viewType = " + viewType);
        View convertView = inflater.inflate(R.layout.chat_base_group_item, parent, false);
        return new ContentViewHolder(convertView);
    }

    public class ContentViewHolder extends ViewHolder {
        TextView mUserAccount;
        TextView mContent;
        TextView mTime;

        public ContentViewHolder(View itemView) {
            super(itemView);
            mUserAccount = itemView.findViewById(R.id.user_account);
            mContent = itemView.findViewById(R.id.content);
            mTime = itemView.findViewById(R.id.time);
        }

        @Override
        public void bindData(Conversation data, int position) {
            super.bindData(data, position);
            mUserAccount.setText(data.getTitle());
//            TextContent stringExtra = (TextContent) data.getContent();
            MessageContent content = (MessageContent) data.getLatestMessage().getContent();
            Message message = data.getLatestMessage();
            TextContent stringExtra = (TextContent) message.getContent();
            mContent.setText(stringExtra.getText());
            mTime.setText(DateFormatUtils.getTimeStringAutoShort2(new Date(data.getLatestMessage().getCreateTime()), true));
        }
    }
}
