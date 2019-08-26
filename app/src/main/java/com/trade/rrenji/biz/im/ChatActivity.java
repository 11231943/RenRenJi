package com.trade.rrenji.biz.im;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gelitenight.superrecyclerview.LinearSpacingDecoration;
import com.gelitenight.superrecyclerview.SuperRecyclerView;
import com.trade.rrenji.R;
import com.trade.rrenji.biz.base.BaseActivity;
import com.trade.rrenji.biz.home.ui.adapter.HomeAdapter;
import com.trade.rrenji.biz.im.adapter.ChatAdapter;
import com.trade.rrenji.event.MessageServiceEvent;
import com.trade.rrenji.utils.Contetns;
import com.trade.rrenji.utils.SettingUtils;
import com.trade.rrenji.utils.SystemUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.ContentView;

import java.util.List;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.api.BasicCallback;

public class ChatActivity extends BaseActivity {

    //        private String admin1 = "00000000-18d1-5b7a-ffff-ffff97caa169";
    SuperRecyclerView mSuperRecyclerView;
    EditText mMessage;
    TextView mSend;
    ChatAdapter mChatAdapter;
    Conversation mConversation;
    private String mUserName;
    private String mFrom;
    private String mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_chat_main_layout);
        EventBus.getDefault().register(this);
        mSuperRecyclerView = findViewById(R.id.chat_recycler_view);
        mMessage = findViewById(R.id.message);
        mSend = findViewById(R.id.send);
        mFrom = getIntent().getStringExtra("from");
        if (TextUtils.equals(mFrom, "1")) {
            mTitle = getIntent().getStringExtra("title");
            mConversation = JMessageClient.getSingleConversation(mUserName, "");
            setActionBarTitle(mTitle);
        } else if (TextUtils.equals(mFrom, "0")) {
            mUserName = getIntent().getStringExtra("username");
            mUserName = Contetns.ACCOUNT_ADMIN;
            mConversation = JMessageClient.getSingleConversation(mUserName, "");
            setActionBarTitle("客服");
        }
        init();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageServiceEvent event) {
        ref();
    }

    private void ref() {
        if (mConversation == null) {
            mConversation = JMessageClient.getSingleConversation(mUserName, "");
        }
        mChatAdapter.add(mConversation.getLatestMessage());
        mSuperRecyclerView.scrollToPosition(mChatAdapter.getItemCount() - 1);
    }

    private void init() {
        List<Message> listMessage = null;
        if (mConversation == null) {
            mConversation = Conversation.createSingleConversation(mUserName, "");
            listMessage = mConversation.getAllMessage();
        } else {
            listMessage = mConversation.getAllMessage();
        }

        mChatAdapter = new ChatAdapter(this);
        mSuperRecyclerView.setAdapter(mChatAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setStackFromEnd(true);
        mSuperRecyclerView.setLayoutManager(manager);
        mChatAdapter.addAll(listMessage);
        mSuperRecyclerView.scrollToPosition(mChatAdapter.getItemCount() - 1);
        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMessage.getText().toString().length() > 0) {
                    senMessage();
                }
            }
        });
    }

    private void senMessage() {
        TextContent textContent = new TextContent(mMessage.getText().toString());
        Message message = mConversation.createSendMessage(textContent);
        JMessageClient.sendMessage(message);
        mMessage.setText("");
        ref();
        message.setOnSendCompleteCallback(new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {
                if (i == 0) {
//                    Toast.makeText(ChatActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
                } else if (i == 1) {
//                    Toast.makeText(ChatActivity.this, "发送失败", Toast.LENGTH_SHORT).show();
                }
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
