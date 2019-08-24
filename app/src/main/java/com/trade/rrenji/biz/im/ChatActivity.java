package com.trade.rrenji.biz.im;

import android.os.Bundle;
import android.util.Log;

import com.trade.rrenji.R;
import com.trade.rrenji.biz.base.BaseActivity;
import com.trade.rrenji.event.MessageServiceEvent;
import com.trade.rrenji.event.address.GoAddressActivityEvent;
import com.trade.rrenji.utils.SystemUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.ContentView;

import java.util.List;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.enums.MessageDirect;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;

@ContentView(R.layout.base_chat_main_layout)
public class ChatActivity extends BaseActivity {

    private String admin = "00000000-18d1-5b7a-ffff-ffff97caa169";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        init();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageServiceEvent event) {
        List<Conversation> list = JMessageClient.getConversationList();
//        list.get(0).getLatestMessage().getDirect()== MessageDirect.send;
        Log.e("message", list.get(0).toString());
    }

    private void init() {
        Conversation.createSingleConversation(admin, "");
        JMessageClient.getSingleConversation(admin, "");
        List<Conversation> list = JMessageClient.getConversationList();
    }

    @Override
    protected void attachPresenter() {

    }

    @Override
    protected void detachPresenter() {

    }
}
