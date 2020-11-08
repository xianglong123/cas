package com.cas.wykt.java高级开发.lock.reentrantLock;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 12:38 2020-07-12
 * @version: V1.0
 * @review: ConcuurentHashMap讲解： https://www.jianshu.com/p/865c813f2726
 */
public class ConcuurentHashMap1 {

    // 多任务环境需要
    // 任务都要对同一共享资源进行写操作
    // 对资源的访问是互斥的

    static int count = 100;


    public static void main(String[] args) {
        for (int i = 0; i <= 10000; i ++) {
            new Thread(() -> count ++).start();
        }
        System.out.println(count);
    }

    public static void mapTest() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("name", "xl");
        System.out.println(map.get("name"));
        ConcurrentSkipListMap<String, String> map1 = new ConcurrentSkipListMap<>();
        map1.put("age", "21");
        System.out.println(map1.get("age"));
    }
}
