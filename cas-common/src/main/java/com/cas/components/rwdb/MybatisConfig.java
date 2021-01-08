package com.cas.components.rwdb;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 14:35 2020-07-07
 * @version: V1.0
 * @review:
 */
@Configuration
@MapperScan(basePackages = {"com.cas.dao.mapper"})
public class MybatisConfig {

    @Bean(name = "writeDataSource")
    @ConfigurationProperties("spring.datasource.druid.write")
    public DataSource writeDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "readDataSource")
    @ConfigurationProperties("spring.datasource.druid.query")
    public DataSource readDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

}
