package com.dy.learn.learn.jms;

import javax.jms.TextMessage;

public interface MQConsumer {

    public void doConsumer(TextMessage message);

}
