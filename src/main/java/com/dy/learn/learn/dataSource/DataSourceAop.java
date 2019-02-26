package com.dy.learn.learn.dataSource;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(-1)// 保证该AOP在@Transactional之前执行
@Component
@Configuration
public class DataSourceAop {






    private static Logger logger = LoggerFactory.getLogger(DataSourceAop.class);
    @Before("execution(* com.dy.learn.learn.service.*.select*(..)) || execution(* com.dy.learn.learn.service.*.get*(..))|| execution(* com.dy.learn.learn.service.*.query*(..))")
    public void setReadDataSourceType() {

//        logger.info("dataSource 切换到：Read");
    }

    @Before("execution(* com.dy.learn.learn.service.*.insert*(..)) || execution(* com.dy.learn.learn.service.*.update*(..))")
    public void setWriteDataSourceType() {

//        logger.info("dataSource 切换到：Write");
    }

}
