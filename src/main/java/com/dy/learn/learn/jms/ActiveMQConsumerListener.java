package com.dy.learn.learn.jms;

import com.dy.learn.learn.enums.EQueueEnum;
import com.dy.learn.learn.helper.ApplicationContextManager;
import com.dy.learn.learn.helper.Constans;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.TextMessage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * MQ消息监听器转发中心
 */
@Component
public class ActiveMQConsumerListener {
    Logger LOGGER = LoggerFactory.getLogger(ActiveMQConsumerListener.class);

    static ExecutorService executorService = Executors.newFixedThreadPool(Constans.MQ_CONSUMER_THREAD_POOL_SIZE);

    @JmsListener(destination = "PayQueue")
    public void receivePayQueue(Message message){
        routeMessage(message, EQueueEnum.PayQueue);
    }

    @JmsListener(destination = "LoginQueue")
    public void receiveLoginQueue(Message message){
        routeMessage(message, EQueueEnum.LoginQueue);
    }

    /**
     * 路由Message，根据队列进入不同的处理类
     * @param message
     * @param queue
     */
    public void routeMessage(Message message,EQueueEnum queue){
        TextMessage textMessage = (TextMessage) message;
        LOGGER.info("doConsumerListener begin:{},{}",queue.getCode(),textMessage.toString());
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                MQConsumer consumer = ApplicationContextManager.getApplicationContext().getBean(queue.getCode(),MQConsumer.class);
                consumer.doConsumer(textMessage);
            }
        });
        LOGGER.info("doConsumerListener end:{},{}",queue.getCode(),textMessage.toString());
    }

}
