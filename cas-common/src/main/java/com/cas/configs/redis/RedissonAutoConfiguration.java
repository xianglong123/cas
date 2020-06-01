package com.cas.configs.redis;

import com.cas.utils.RedissLockUtil;
import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: xianglong[xiang_long@suixingpay.com]
 * @date: 13:32 2020-05-11
 * @version: V1.0
 * @review:
 */
//@Configuration
//@ConditionalOnClass(Config.class)
//@EnableConfigurationProperties(RedissonProperties.class)
public class RedissonAutoConfiguration {

    @Autowired
    private RedissonProperties redissonProperties;

    @Bean
    @ConditionalOnProperty(name = "redisson.address")
    RedissonClient redissonSingle() {
        Config config = new Config();
        // 2秒一次寻访
        config.setLockWatchdogTimeout(45000);
        /*
        *
        * String[] strings = redisModel.getSentinel().getNodes().split(",");
        List<String> addressList = new ArrayList<>();
        Arrays.asList(strings).forEach(address -> addressList.add("redis://" + address));
        * */
        SingleServerConfig serverConfig = config.useSingleServer()

                .setAddress(redissonProperties.getAddress())
                .setTimeout(redissonProperties.getTimeout())
                .setConnectionPoolSize(redissonProperties.getConnectionPoolSize())
                .setConnectionMinimumIdleSize(redissonProperties.getConnectionMinimumIdleSize());

        if (StringUtils.isNotBlank(redissonProperties.getPassword())) {
            serverConfig.setPassword(redissonProperties.getPassword());
        }
        return Redisson.create(config);
    }

    /**
     * 装配locker类，并将实例注入redissLockUtil中
     * @param redissonClient
     * @return
     */
    @Bean
    DistributedLocker distributedLocker(RedissonClient redissonClient) {
        RedissonDistributedLocker locker = new RedissonDistributedLocker();
        locker.setRedissonClient(redissonClient);
        RedissLockUtil.setLocker(locker);
        return locker;
    }


}
