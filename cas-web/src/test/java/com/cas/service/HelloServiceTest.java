package com.cas.service;

import com.cas.BaseTest;
import com.cas.service.testService.HelloService;
import com.cas.service.testService.HelloServiceImpl;
import org.junit.Test;

/**
 * @author: xianglong[xiang_long@suixingpay.com]
 * @date: 17:27 2020-01-28
 * @version: V1.0
 * @review:
 */
public class HelloServiceTest extends BaseTest {

    @Test
    public void test() {
        HelloService helloService = new HelloServiceImpl();
        helloService.sayHello("xl");
    }

}
