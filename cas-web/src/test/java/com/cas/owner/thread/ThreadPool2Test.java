package com.cas.owner.thread;

import com.cas.owner.utils.ThreadPoolUtil;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 12:11 2020-05-10
 * @version: V1.0
 * @review: 手动创建线程池 + 拒绝策略
 */
public class ThreadPool2Test {

    public static void main(String[] args) {
        ThreadPoolUtil.execute(() -> System.out.println("aa"));
    }

}
