package com.cas.configs.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: xianglong[xiang_long@suixingpay.com]
 * @date: 13:27 2020-05-11
 * @version: V1.0
 * @review:
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "redisson")
public class RedissonProperties {

    private int timeout = 3000;

    private String address;

    private String password;

    private int database = 0;

    private int connectionPoolSize = 64;

    private int connectionMinimumIdleSize = 10;

    private int slaveConnectionPoolSize = 250;

    private int masterConnectionPoolSize = 250;

    private String[] sentinelAddresses;

    private String masterName;

}
