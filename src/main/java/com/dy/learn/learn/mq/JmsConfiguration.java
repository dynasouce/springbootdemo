package com.dy.learn.learn.mq;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class JmsConfiguration {

    @Bean
    public PayQueue payQueue(){
        return new PayQueue("learn.queue.pay");
    }

    @Bean
    public LoginQueue loginQueue(){
        return new LoginQueue("learn.queue.login");
    }

}
