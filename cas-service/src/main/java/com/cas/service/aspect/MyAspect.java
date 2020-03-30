package com.cas.service.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author: xianglong[xiang_long@suixingpay.com]
 * @date: 22:20 2020-02-14
 * @version: V1.0
 * @review: 自定义AOP切点
 */
@Aspect
@Component
public class MyAspect {

    @Pointcut("execution(* com.cas.service.testService..*(..))")
    public void pointCut() {}

    @Before("pointCut()")
    public void before() {
        System.out.println("before ......");
    }

    @Around("pointCut()")
    public void around(ProceedingJoinPoint jp) throws Throwable {//plain old java object
        System.out.println("around before ......");
        // proceed() [调用原来的方法
        jp.proceed();
        System.out.println("around after ......");
    }

    @After("pointCut()")
    public void after() {
        System.out.println("after ......");
    }

    @AfterReturning("pointCut()")
    public void afterReturning() {
        System.out.println("afterReturning ......");
    }

    @AfterThrowing("pointCut()")
    public void afterThrowing() {
        System.out.println("afterThrow ......");
    }

}
