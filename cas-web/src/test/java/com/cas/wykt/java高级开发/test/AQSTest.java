package com.cas.wykt.java高级开发.test;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 07:29 2020-07-14
 * @version: V1.0
 * @review:
 */
public class AQSTest {

    protected int tryAdd(int a, int b) {
        throw new UnsupportedOperationException();
    }

    public int add(int a, int b) {
        return tryAdd(a, b);
    }

}
