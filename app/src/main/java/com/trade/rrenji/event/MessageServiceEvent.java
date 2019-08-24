package com.trade.rrenji.event;

import cn.jpush.im.android.api.model.Message;

public class MessageServiceEvent {

    private Message message;

    public MessageServiceEvent(Message message) {
        this.message = message;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
