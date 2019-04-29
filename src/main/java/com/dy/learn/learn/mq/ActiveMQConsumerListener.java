package com.dy.learn.learn.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

@Component
public class ActiveMQConsumerListener {

    Logger LOGGER = LoggerFactory.getLogger(ActiveMQConsumerListener.class);

    @JmsListener(destination = "PayQueue")
    public void receivePayQueue(Message message){
        TextMessage text = (TextMessage) message;
        String jmsValue = null;
        try {
            jmsValue = text.getText();
            LOGGER.info("接收消息PayQueue:{}",jmsValue);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    @JmsListener(destination = "LoginQueue")
    public void receiveLoginQueue(Message message){
        TextMessage text = (TextMessage) message;
        String jmsValue = null;
        try {
            jmsValue = text.getText();
            LOGGER.info("接收消息LoginQueue:{}",jmsValue);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
