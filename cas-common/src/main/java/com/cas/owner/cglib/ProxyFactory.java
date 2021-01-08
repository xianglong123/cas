package com.cas.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

public class ProxyFactory {
    // 维护目标对象
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    public ProxyFactory() {
    }

    // 给目标对象创建代理对象
    public Object getProxyInstance() {
        //1. 工具类
        Enhancer en = new Enhancer();
        //2. 设置父类
        en.setSuperclass(target.getClass());
        //3. 设置回调函数
        en.setCallback((MethodInterceptor) (o, method, args, methodProxy) -> {
            System.out.println("开始事务");
            //Object obj = method.invoke(target, args);
            Object obj = methodProxy.invokeSuper(o, args);
            System.out.println("提交事务");
            return obj;
        });
        //4. 创建子类(代理对象)
        return en.create();
    }
}
