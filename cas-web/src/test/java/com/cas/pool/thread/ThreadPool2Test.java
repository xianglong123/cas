package com.cas.pool.thread;

import com.cas.utils.ThreadPoolUtil;

/**
 * @author: xianglong[xiang_long@suixingpay.com]
 * @date: 12:11 2020-05-10
 * @version: V1.0
 * @review: 手动创建线程池 + 拒绝策略
 */
public class ThreadPool2Test {

    public static void main(String[] args) {
        ThreadPoolUtil.execute(() -> System.out.println("aa"));
    }

}
