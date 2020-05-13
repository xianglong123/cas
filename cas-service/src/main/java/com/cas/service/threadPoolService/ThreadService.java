package com.cas.service.threadPoolService;

import com.cas.components.AfterCommitExecutor;
import com.cas.utils.ThreadPoolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: xianglong[xiang_long@suixingpay.com]
 * @date: 16:37 2020-05-11
 * @version: V1.0
 * @review: 线程池复用
 */
@Service
public class ThreadService {

    @Autowired
    private AfterCommitExecutor afterCommitExecutor;

    public String execute() {
        System.out.println("主线程：" + Thread.currentThread().getName());
        ThreadPoolUtil.execute(() -> System.out.println("当前线程:" + Thread.currentThread().getName()));

        afterCommitExecutor.execute(() -> {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {

            }
            System.out.println("说个屁，事务执行完之后执行的代码");
        });
        return "ok";
    }


}
