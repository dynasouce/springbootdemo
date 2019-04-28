package com.dy.learn.learn.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class QueueProvider {

/*    @Autowired
    private PayQueue payQueue;

    @Autowired
    private LoginQueue loginQueue;*/


    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void send(QueueEnum queueType, String msg){
        /*System.out.println("sendType:" + queueType + ",msg:" + msg);
        if (queueType.equals(QueueEnum.LoginQueue)){
            this.jmsMessagingTemplate.convertAndSend(loginQueue,msg);
        } else if (queueType.equals(QueueEnum.PayQueue)){
            this.jmsMessagingTemplate.convertAndSend(payQueue,msg);
        }*/

        System.out.println("sendType:" + queueType + ",msg:" + msg);
        this.jmsMessagingTemplate.convertAndSend(queueType.toString(),msg);
    }

    //@JmsListener(destination = "PayQueue")
    public void receivePayQueue(String text){
        System.out.println("接收消息learn.queue.pay：" + text);
    }

    //@JmsListener(destination = "LoginQueue")
    public void receiveLoginQueue(String text){
        System.out.println("接收消息learn.queue.login：" + text);
    }

}
