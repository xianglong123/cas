package com.cas.service.aspect;

import com.cas.components.tx.AfterCommitExecutor;
import com.cas.service.threadPoolService.ThreadService;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 22:20 2020-02-14
 * @version: V1.0
 * @review: 自定义AOP切点
 */
@Aspect
@Component
public class MyAspect {

    @Autowired
    private AfterCommitExecutor afterCommitExecutor;

    @Autowired
    private ThreadService threadService;

    @Pointcut("execution(* com.cas.service.accountService..*(..))")
    public void pointCut() {}

//    @Before("pointCut()")
//    public void before() {
//        System.out.println("before ......");
//    }
//
//    @Around("pointCut()")
//    public void around(ProceedingJoinPoint jp) throws Throwable {//plain old java object
//        System.out.println("around before ......");
//        // proceed() [调用原来的方法
//        jp.proceed();
//        System.out.println("around after ......");
//    }
//
//    @After("pointCut()")
//    public void after() {
//        System.out.println("after ......");
//    }

    @AfterReturning("pointCut()")
    public void afterReturning() {
        System.out.println("afterReturning ......");
        afterCommitExecutor.execute(() -> {
            try {
                System.out.println("执行线程号-:" + Thread.currentThread().getName());
                threadService.execute();
                Thread.sleep(4000);
            } catch (InterruptedException e) {

            }
            System.out.println("说个屁，事务执行完之后执行的代码");
        });
    }

//    @AfterThrowing("pointCut()")
//    public void afterThrowing() {
//        System.out.println("afterThrow ......");
//    }

}
