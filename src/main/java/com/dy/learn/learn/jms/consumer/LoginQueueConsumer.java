package com.dy.learn.learn.jms.consumer;

import com.dy.learn.learn.jms.MQConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.TextMessage;

@Service("LoginQueue")
public class LoginQueueConsumer implements MQConsumer {

    Logger LOGGER = LoggerFactory.getLogger(LoginQueueConsumer.class);

    @Override
    public void doConsumer(TextMessage message) {
        try {
            LOGGER.info("doConsumer begin:{}",message.getText());
            Thread.currentThread().sleep(5000);
            LOGGER.info("doConsumer end:{}",message.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
