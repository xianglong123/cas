package com.cas.intercept;


import com.cas.domain.invoke.Invocation;

import java.lang.reflect.InvocationTargetException;

/**
 * @author: xianglong[xiang_long@suixingpay.com]
 * @date: 16:13 2020-01-28
 * @version: V1.0
 * @review:
 */
public interface Interceptor {


    //事前方法
    boolean before();

    //事后方法
    void after();

    /**
     * 取代原有事物方法
     * @param invocation -- 回调函数， 可以通过它的proceed方法，回调原有事件
     * @return 原有事件返回对象
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    Object around(Invocation invocation) throws Throwable;

    //事后返回方法，事件没有发生异常执行
    void afterReturning();

    //事后异常方法，当事没有发生异常执行
    void afterThrowing();

    //是否使用around方法取代原有方法
    boolean useAround();

}
