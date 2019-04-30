package com.dy.learn.learn.jms;

import com.dy.learn.learn.enums.EQueueEnum;
import com.dy.learn.learn.helper.Constans;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.command.ActiveMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service("activeMQProducerService")
public class ActiveMQProducerService implements MQProducerService {

    Logger LOGGER = LoggerFactory.getLogger(ActiveMQProducerService.class);

    static ExecutorService executorService = Executors.newFixedThreadPool(Constans.MQ_PRODUCER_THREAD_POOL_SIZE);

    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    public void sendQueueMessage(final EQueueEnum queueType, final String message){

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                LOGGER.info(">>>>>>>>>>>>>>:send queue message:{},message :{}",queueType.toString(),message);

                ActiveMQDestination t = ActiveMQQueue.createDestination(
                        queueType.toString(), ActiveMQQueue.QUEUE_TYPE);
                t.setPhysicalName(queueType.toString());
                jmsTemplate.send(t, new MessageCreator() {

                    public javax.jms.Message createMessage(Session session)
                            throws JMSException {
                        return session.createTextMessage(message);
                    }
                });
            }
        });
    }

    @Override
    public void sendTopicMessage(EQueueEnum queueType, String message) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                LOGGER.info(">>>>>>>>>>>>>>:send topic:{},message :{}",queueType.toString(),message);

                ActiveMQDestination t = ActiveMQQueue.createDestination(
                        queueType.toString(), ActiveMQQueue.TOPIC_TYPE);
                t.setPhysicalName(queueType.toString());

                jmsTemplate.send(t, new MessageCreator() {
                    public Message createMessage(Session session) throws JMSException {
                        session.createTopic(queueType.toString());
                        TextMessage msg = session.createTextMessage(message);
                        return msg;
                    }
                });
            }
        });
    }
}
