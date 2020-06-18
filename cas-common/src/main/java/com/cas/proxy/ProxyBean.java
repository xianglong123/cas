package com.cas.proxy;

import com.cas.domain.invoke.Invocation;
import com.cas.intercept.Interceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 21:05 2020-02-14
 * @version: V1.0
 * @review: 动态代理bean的实现
 */
public class ProxyBean implements InvocationHandler {

    private Object target = null;

    private Interceptor interceptor = null;


    /**
     * 绑定代理对象
     * @param target 被代理对象
     * @param interceptor 拦截器
     * @return 代理对象
     */
    public static Object getProxyBean(Object target, Interceptor interceptor) {
        ProxyBean proxyBean = new ProxyBean();
        // 保存被代理对象
        proxyBean.target = target;
        // 保存拦截器
        proxyBean.interceptor = interceptor;
        // 生成代理对象
        //newProxyInstance()这个方法的第三个参数是核心，他将这个动态代理类和原来的对象进行了绑定，这样代理类执行方法的时候会映射到下面的invoke()方法，也就是偷换实现方法
        //下面这句话的作用就是将代理类的方法逻辑 跳转到 invoke() 方法
        Object proxy = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), proxyBean);
        // 返回代理对象
        return proxy;
    }

    /**
     * 处理代理对象方法逻辑
     * @param proxy  这个对象虽然有，但是实际上使用的是代理对象
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //异常标识
        boolean exceptionFlag = false;
        Invocation invocation = new Invocation(target, method, args);
        Object retObj = null;
        try {
            if (this.interceptor.useAround()) {
                retObj = this.interceptor.around(invocation);
            } else {
                //反射调用方法
                retObj = method.invoke(target, args);
            }
        } catch (Exception e) {
            // 产生异常
            exceptionFlag = true;
        }
        this.interceptor.after();

        if(exceptionFlag) {
            this.interceptor.afterThrowing();
        } else {
            this.interceptor.afterReturning();
            return retObj;
        }
        return null;
    }
}
