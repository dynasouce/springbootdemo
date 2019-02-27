package com.dy.learn.learn.mq;

import org.apache.activemq.command.ActiveMQQueue;

public class LoginQueue extends ActiveMQQueue {
    public LoginQueue() {
    }

    public LoginQueue(String name) {
        super(name);
    }
}
