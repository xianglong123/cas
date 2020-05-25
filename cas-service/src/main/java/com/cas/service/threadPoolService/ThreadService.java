package com.cas.service.threadPoolService;

import com.cas.utils.ThreadPoolUtil;
import org.springframework.stereotype.Service;

/**
 * @author: xianglong[xiang_long@suixingpay.com]
 * @date: 16:37 2020-05-11
 * @version: V1.0
 * @review: 线程池复用
 */
@Service
public class ThreadService {

    public String execute() {
        System.out.println("主线程：" + Thread.currentThread().getName());
        ThreadPoolUtil.execute(() -> System.out.println("当前线程:" + Thread.currentThread().getName()));
        return "ok";
    }


}
