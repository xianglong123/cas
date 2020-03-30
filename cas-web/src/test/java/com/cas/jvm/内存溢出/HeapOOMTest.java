package com.cas.jvm.内存溢出;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: xianglong[xiang_long@suixingpay.com]
 * @date: 19:31 2020-03-21
 * @version: V1.0
 * @review: VM args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError  堆内存溢出案例测试
 */
public class HeapOOMTest {

    static class OOMObject{}

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}
