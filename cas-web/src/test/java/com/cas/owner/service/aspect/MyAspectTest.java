package com.cas.owner.service.aspect;

import com.cas.BaseTest;
import com.cas.owner.service.testService.HelloService;
import com.cas.owner.service.testService.HelloServiceImpl;
import org.junit.Test;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 22:34 2020-02-14
 * @version: V1.0
 * @review:
 */
public class MyAspectTest extends BaseTest {

    /**
     * 测试Aop切点，自定义切点MyAspect  这样测试结果 --> 失败
     */
    @Test
    public void test() {
        HelloService helloService = new HelloServiceImpl();
        helloService.sayHello("xl");
    }

}
