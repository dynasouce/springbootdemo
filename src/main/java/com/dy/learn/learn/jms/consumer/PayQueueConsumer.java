package com.dy.learn.learn.jms.consumer;

import com.dy.learn.learn.jms.MQConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.TextMessage;

@Service("PayQueue")
public class PayQueueConsumer implements MQConsumer {

    Logger LOGGER = LoggerFactory.getLogger(PayQueueConsumer.class);

    @Override
    public void doConsumer(TextMessage message) {
        try {
            LOGGER.info("doConsumer:{}",message.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
