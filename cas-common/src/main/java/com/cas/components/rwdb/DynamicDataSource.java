package com.cas.components.rwdb;

import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 10:19 2020-07-07
 * @version: V1.0
 * @review:
 */
@Primary
@Component
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Resource(name = "writeDataSource")
    private DataSource writeDataSource;

    @Resource(name = "readDataSource")
    private DataSource readDataSource;


    @Override
    protected Object determineCurrentLookupKey() {
        DynamicDataSourceGlobal dynamicDataSourceGlobal = DynamicDataSourceHolder.getDataSource();

        if (dynamicDataSourceGlobal == null || dynamicDataSourceGlobal == DynamicDataSourceGlobal.WRITE) {
            return DynamicDataSourceGlobal.WRITE.name();
        }
        return DynamicDataSourceGlobal.READ.name();
    }

    @Override
    public void afterPropertiesSet() {
        if (this.writeDataSource == null) {
            throw new IllegalArgumentException("Property 'writeDataSource' is required");
        }
        // 覆盖默认数据源
        setDefaultTargetDataSource(writeDataSource);
        HashMap<Object, Object> targetDataSource = new HashMap<>();
        targetDataSource.put(DynamicDataSourceGlobal.WRITE.name(), writeDataSource);
        if (readDataSource != null) {
            targetDataSource.put(DynamicDataSourceGlobal.READ.name(), readDataSource);
        }
        // 设置目标数据源为map中压入的数据源
        setTargetDataSources(targetDataSource);
        super.afterPropertiesSet();
    }

    public DataSource getWriteDataSource() {
        return writeDataSource;
    }

    public void setWriteDataSource(DataSource writeDataSource) {
        this.writeDataSource = writeDataSource;
    }

    public DataSource getReadDataSource() {
        return readDataSource;
    }

    public void setReadDataSource(DataSource readDataSource) {
        this.readDataSource = readDataSource;
    }
}
