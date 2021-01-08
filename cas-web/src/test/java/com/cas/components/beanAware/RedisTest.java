package com.cas.components.beanAware;

import com.cas.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author: xianglong
 * @date: 2019/12/6 15:21
 * @version: V1.0
 * @review: 1391086179@qq.com
 */
@Slf4j
public class RedisTest extends BaseTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test1() {
        redisTemplate.opsForValue().set("aa","ss");
        log.info("========" + redisTemplate.opsForValue().get("aa"));
        log.info("0000");
    }

}
