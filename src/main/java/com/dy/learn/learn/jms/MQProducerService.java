package com.dy.learn.learn.jms;

import com.dy.learn.learn.enums.EQueueEnum;

public interface MQProducerService {

    /**
     * 发送消息
     * @param queueType 队列类型
     * @param message 消息内容
     */
    void sendQueueMessage(EQueueEnum queueType, String message);

    /**
     * 发送Topic消息
     * @param queueType 队列类型
     * @param message 消息内容
     */
    void sendTopicMessage(EQueueEnum queueType, String message);

}
