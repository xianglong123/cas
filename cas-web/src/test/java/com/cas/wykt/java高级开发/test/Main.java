package com.cas.wykt.java高级开发.test;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 07:31 2020-07-14
 * @version: V1.0
 * @review:
 */
public class Main {

    public static void main(String[] args) {
        AQSTest aqsTest = new AQSTest() {
            @Override
            protected int tryAdd(int a, int b) {
                return a * b;
            }
        };

        System.out.println(aqsTest.add(3,5));

    }

}
