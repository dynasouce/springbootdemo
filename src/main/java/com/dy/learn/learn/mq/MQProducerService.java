package com.dy.learn.learn.mq;

public interface MQProducerService {

    /**
     * 发送消息
     * @param queueType 队列类型
     * @param message 消息内容
     */
    void sendQueueMessage(QueueEnum queueType, String message);

    /**
     * 发送Topic消息
     * @param queueType 队列类型
     * @param message 消息内容
     */
    void sendTopicMessage(QueueEnum queueType, String message);

}
