package com.dy.learn.learn.mq;

import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.command.ActiveMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class ActiveMQProducerServiceImpl implements MQProducerService{

    Logger LOGGER = LoggerFactory.getLogger(ActiveMQProducerServiceImpl.class);

    static ExecutorService executorService = Executors.newFixedThreadPool(16);

    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    public void sendQueueMessage(final QueueEnum queueType, final String message){

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                LOGGER.info(">>>>>>>>>>>>>>:send queue:{},message :{}",queueType.toString(),message);

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
    public void sendTopicMessage(QueueEnum queueType, String message) {
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
