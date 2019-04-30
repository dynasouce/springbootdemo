package com.dy.learn.learn.dataSource;

import com.dy.learn.learn.enums.EDataSourceType;
import org.aspectj.lang.JoinPoint;
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

    @Pointcut("@annotation(com.dy.learn.learn.dataSource.DataSourceSelection)")
    public void doSelect() {

    }

    @Before(value = "doSelect()&&@annotation(ds)")
    public void salve(JoinPoint jp,DataSourceSelection ds) {
        if (ds!=null){
            DynamicDataSourceHolder.setDataSourcesType(ds.type());
        } else {
            DynamicDataSourceHolder.setDataSourcesType(EDataSourceType.master);
        }
    }
}
