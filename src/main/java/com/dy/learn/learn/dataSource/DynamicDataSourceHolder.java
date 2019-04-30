package com.dy.learn.learn.dataSource;

import com.dy.learn.learn.enums.EDataSourceType;

public class DynamicDataSourceHolder {

    private static final ThreadLocal<EDataSourceType> holder = new ThreadLocal<EDataSourceType>();

    private DynamicDataSourceHolder(){

    }

    public static void setDataSourcesType(EDataSourceType dataSourcesType){
        holder.set(dataSourcesType);
    }

    public static EDataSourceType getDataSourcesType(){
        return holder.get();
    }

}
