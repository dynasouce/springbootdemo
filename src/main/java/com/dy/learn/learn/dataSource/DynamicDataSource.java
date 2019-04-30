package com.dy.learn.learn.dataSource;

import com.dy.learn.learn.enums.EDataSourceType;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        EDataSourceType dataSourceType = DynamicDataSourceHolder.getDataSourcesType();
        if (dataSourceType != null ) {
            return dataSourceType;
        }
        return EDataSourceType.master;
    }
}