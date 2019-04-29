package com.dy.learn.learn.dataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.dy.learn.learn.enums.DataSourceType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.HashMap;
import java.util.Map;


@Configuration
@Component
public class DynamicDataSourceConfig {

    @Bean(name = "masterDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public javax.sql.DataSource masterDataSource(){
        return DataSourceBuilder.create().type(DruidDataSource.class).build();
    }

    @Bean(name = "slaveDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public javax.sql.DataSource slaveDataSource(){
        return DataSourceBuilder.create().type(DruidDataSource.class).build();
    }

    @Bean(name="dataSource")
    public DynamicDataSource getDynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> dataSourceMap = new HashMap<Object, Object>();
        dataSourceMap.put(DataSourceType.master, masterDataSource());
        dataSourceMap.put(DataSourceType.slave, slaveDataSource());
        dynamicDataSource.setDefaultTargetDataSource(masterDataSource());
        dynamicDataSource.setTargetDataSources(dataSourceMap);
        return dynamicDataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DynamicDataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * 自动为DataSource添加事物管理
     * @param dataSource
     * @return
     */
    @Bean
    public PlatformTransactionManager platformTransactionManager(@Qualifier("dataSource") DynamicDataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


}
