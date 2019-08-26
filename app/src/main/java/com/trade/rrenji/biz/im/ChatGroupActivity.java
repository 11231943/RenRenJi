package com.trade.rrenji.biz.im;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.gelitenight.superrecyclerview.DividerItemDecoration;
import com.gelitenight.superrecyclerview.SuperRecyclerView;
import com.trade.rrenji.R;
import com.trade.rrenji.biz.base.BaseActivity;
import com.trade.rrenji.biz.im.adapter.ChatAdapter;
import com.trade.rrenji.biz.im.adapter.ChatGorupAdapter;
import com.trade.rrenji.event.MessageServiceEvent;
import com.trade.rrenji.utils.Contetns;
import com.trade.rrenji.utils.SettingUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.api.BasicCallback;

public class ChatGroupActivity extends BaseActivity {

    SuperRecyclerView mSuperRecyclerView;
    EditText mMessage;
    TextView mSend;
    ChatGorupAdapter mChatAdapter;
    Conversation mConversation;
    private String mUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_chat_group_main_layout);
        setActionBarTitle("私信");
        EventBus.getDefault().register(this);
        mSuperRecyclerView = findViewById(R.id.chat_recycler_view);

        if (!SettingUtils.getInstance().getAccountIsAdmin()) {
            //不是管理，就直接与管理号建立连接
            mConversation = JMessageClient.getSingleConversation(Contetns.ACCOUNT_ADMIN, "");
        }
        init();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageServiceEvent event) {
        ref();
    }

    private void ref() {
        List<Conversation> list = JMessageClient.getConversationList();
        mChatAdapter.clear();
        mChatAdapter.addAll(list);
        mSuperRecyclerView.finishRefreshing();
    }

    private void init() {
        mChatAdapter = new ChatGorupAdapter(this);
        mSuperRecyclerView.setAdapter(mChatAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mSuperRecyclerView.setLayoutManager(manager);
        mChatAdapter.addAll(JMessageClient.getConversationList());
        mChatAdapter.setOnMessageClickListener(new ChatGorupAdapter.onMessageClickListener() {
            @Override
            public void onMessage(Conversation data) {
                data.setUnReadMessageCnt(0);
                Intent intent = new Intent(ChatGroupActivity.this, ChatActivity.class);
                intent.putExtra("username", data.getTargetId());
                intent.putExtra("from", "1");
                intent.putExtra("title", data.getTitle());
                startActivity(intent);

            }
        });
        mSuperRecyclerView.setOnLoadDataListener(new SuperRecyclerView.OnLoadDataListener() {
            @Override
            public void onRefresh() {
                ref();

            }

            @Override
            public void onMore() {

            }
        });
    }


    @Override
    protected void attachPresenter() {

    }

    @Override
    protected void detachPresenter() {

    }
}
