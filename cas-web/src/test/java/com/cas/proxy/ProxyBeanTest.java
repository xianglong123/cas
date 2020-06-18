package com.cas.proxy;

import com.cas.BaseTest;
import com.cas.intercept.MyInterceptor;
import com.cas.service.testService.HelloService;
import com.cas.service.testService.HelloServiceImpl;
import org.junit.Test;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 21:21 2020-02-14
 * @version: V1.0
 * @review:
 */
public class ProxyBeanTest extends BaseTest {

    /**
     * 测试动态代理对象 -> 约定模拟AOP
     * @param
     */
    @Test
    public void test() {
        HelloService helloService = new HelloServiceImpl();
        // 按约定获取proxy
        HelloService proxy = (HelloService) ProxyBean.getProxyBean(helloService, new MyInterceptor());
        proxy.sayHello("张三");
        System.out.println("\n############## name is null!!! ###########\n");
        proxy.sayHello(null);
    }

}
