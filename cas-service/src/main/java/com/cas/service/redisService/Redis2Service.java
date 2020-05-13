package com.cas.service.redisService;

import com.cas.utils.RedissLockUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author: xianglong[xiang_long@suixingpay.com]
 * @date: 14:01 2020-05-11
 * @version: V1.0
 * @review: redis分布式锁
 */
@Slf4j
@Service
public class Redis2Service {
    private static final String REDIS_LOCK_CAS_TEST = "REDIS_LOCK_CAS_TEST";

    public String getOk() {
        if (RedissLockUtil.tryLock(REDIS_LOCK_CAS_TEST, TimeUnit.SECONDS, 0)) {
            try {
                Thread.sleep(10000);
                log.info("获得锁，线程[{}], 业务执行", Thread.currentThread().getName());
            } catch (InterruptedException e) {
                log.error("出错啦！！！");
            } finally {
                RedissLockUtil.unlock(REDIS_LOCK_CAS_TEST);
            }
            return "线程已被释放";
        } else {
            return "线程被其他线程持有";
        }

    }
}
