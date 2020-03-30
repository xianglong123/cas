package com.cas.jvm.内存分配;

/**
 * @author: xianglong[xiang_long@suixingpay.com]
 * @date: 10:36 2020-03-28
 * @version: V1.0
 * @review: VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728
 */
public class PretenureSizeThresholdTest {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        System.out.println(3 * _1MB);
        byte[] allocation;
        allocation = new byte[4 * _1MB];
    }

}
