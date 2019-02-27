package com.dy.learn.learn.mq;

import org.apache.activemq.command.ActiveMQQueue;

public class PayQueue extends ActiveMQQueue {
    public PayQueue() {
    }

    public PayQueue(String name) {
        super(name);
    }
}
