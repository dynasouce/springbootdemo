package com.dy.learn.learn.dataSource;


import com.dy.learn.learn.enums.DataSourceType;

import java.lang.annotation.*;

/**
 * 在service层加入此注解，会动态切入数据源，并且会自动注入事物
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSourceSelection {

    DataSourceType type() default DataSourceType.master;

}
