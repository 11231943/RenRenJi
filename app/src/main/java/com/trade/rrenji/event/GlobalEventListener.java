package com.trade.rrenji.event;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.trade.rrenji.biz.im.ChatActivity;
import com.trade.rrenji.utils.Contetns;

import org.greenrobot.eventbus.EventBus;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.event.NotificationClickEvent;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.model.UserInfo;

/**
 * 在demo中对于通知栏点击事件和在线消息接收事件，我们都直接在全局监听
 */
public class GlobalEventListener {
    private Context appContext;

    public GlobalEventListener(Context context) {
        appContext = context;
        JMessageClient.registerEventReceiver(this);
    }

    public void onEvent(NotificationClickEvent event) {
        jumpToActivity(event.getMessage());
    }

    public void onEvent(MessageEvent event) {
        EventBus.getDefault().post(new MessageServiceEvent(event.getMessage()));
//        jumpToActivity(event.getMessage());
    }

    private void jumpToActivity(Message msg) {
        UserInfo fromUser = msg.getFromUser();
        Log.e("Message","msg = "+msg.toJson());
        String userName = "";
        if (TextUtils.equals(fromUser.getUserName(), Contetns.ACCOUNT_ADMIN)) {
            //管理员
            userName = Contetns.ACCOUNT_ADMIN;
        } else {
            //普通用户登录
            userName = fromUser.getUserName();
        }
        final Intent notificationIntent = new Intent(appContext, ChatActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        notificationIntent.putExtra("from", "0");
        notificationIntent.putExtra("username", userName);
        appContext.startActivity(notificationIntent);
    }
}
