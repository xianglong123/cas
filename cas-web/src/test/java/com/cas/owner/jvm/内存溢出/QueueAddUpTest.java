package com.cas.owner.jvm.内存溢出;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 19:31 2020-03-21
 * @version: V1.0
 * @review: VM args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError  无限向队列添加数据
 * 结论： 20M的堆内存，大概添加17万次就OOM
 */
public class QueueAddUpTest {

    // 测试无限向Queue添加数据会出现什么情况
    private static final Queue<String> queue = new ConcurrentLinkedQueue<>();

    public static void main(String[] args) {
        String str = "queue_";
        for (int i = 0; i <= 1000000; i ++) {
            queue.add(str + i);
            System.out.println("当前运行次数: " + i);
        }
        System.out.println("运行完毕");
    }
}
