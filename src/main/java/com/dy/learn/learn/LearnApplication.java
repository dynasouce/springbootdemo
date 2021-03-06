package com.dy.learn.learn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJms
@SpringBootApplication
@EnableAutoConfiguration
@EnableTransactionManagement
@MapperScan("com.dy.learn.learn.dao.mapper")
@EnableScheduling
//@ImportResource({"classpath:applicationContext.xml"})
//@PropertySource({"classpath:/conf/zookeeper.properties"})
public class LearnApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnApplication.class, args);
	}

}

