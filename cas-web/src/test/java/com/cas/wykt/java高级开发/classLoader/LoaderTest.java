package com.cas.wykt.java高级开发.classLoader;

import java.util.Arrays;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 09:08 2020-07-24
 * @version: V1.0
 * @review:
 */
public class LoaderTest {

    public static void main(String[] args) {
        String property = System.getProperty("java.class.path");
        String[] split = property.split(":");
        Arrays.asList(split).forEach(System.out::println);
    }

}
