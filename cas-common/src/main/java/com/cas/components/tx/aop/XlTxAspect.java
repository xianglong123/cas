package com.cas.components.tx.aop;

import com.cas.components.tx.XlTranscation;
import com.cas.components.tx.XlTranscationManage;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 10:58 2020-07-09
 * @version: V1.0
 * @review:
 */
@Aspect
@Component
public class XlTxAspect {

    @Autowired
    private XlTranscationManage xlTranscationManage;

    @Around("@annotation(xlTranscation)")
    public Object doTranscation(ProceedingJoinPoint proceedingJoinPoint, XlTranscation xlTranscation) throws SQLException {
        Connection con = null;
        Object proceed = null;
        try {
            con = xlTranscationManage.getCon();
            con.setAutoCommit(false);
            System.out.println("开启事务");

            // 业务代码执行
            proceed = proceedingJoinPoint.proceed();

            System.out.println("事务提交");
            con.commit();
        } catch (Throwable e) {
            System.out.println("事务回滚");
            con.rollback();
        }
        return proceed;
    }



}
