package com.cas.service;

import com.cas.intercept.MyInterceptor;
import com.cas.service.testService.HelloService;
import com.cas.service.testService.HelloServiceImpl;
import com.cas.utils.ProxyBean;

/**
 * @author: xianglong[xiang_long@suixingpay.com]
 * @date: 17:27 2020-01-28
 * @version: V1.0
 * @review:
 */
public class HelloServiceTest {

    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        HelloService proxy = (HelloService) ProxyBean.getProxyBean(helloService, new MyInterceptor());
        proxy.sayHello("xl");
        System.out.println("\n############# is null !!! ##############\n");
        proxy.sayHello(null);
    }

}
