package com.dy.learn.learn.dataSource;

import com.dy.learn.learn.enums.DataSourceType;

public class DynamicDataSourceHolder {

    private static final ThreadLocal<DataSourceType> holder = new ThreadLocal<DataSourceType>();

    private DynamicDataSourceHolder(){

    }

    public static void setDataSourcesType(DataSourceType dataSourcesType){
        holder.set(dataSourcesType);
    }

    public static DataSourceType getDataSourcesType(){
        return holder.get();
    }

}
