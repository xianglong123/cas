package com.cas.config;

import com.cas.BaseTest;
import com.cas.configs.ScanConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 20:17 2020-02-11
 * @version: V1.0
 * @review:
 */
@Slf4j
public class ScanConfigTest extends BaseTest {

    @Autowired
    private ScanConfig scanConfig;

    @Test
    public void get() {
        try {
            scanConfig.get();
        } catch (ClassNotFoundException e) {
            log.error("ClassNotFoundException", e);
        }
    }

    @Test
    public void test1() throws Exception{
        Class<?> aClass = Class.forName("com.cas.configs.ScanConfig");
        System.out.println(aClass);
    }

}
