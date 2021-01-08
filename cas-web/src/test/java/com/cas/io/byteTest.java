package com.cas.io;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 09:18 2020-11-30
 * @version: V1.0
 * @review: 一个字符等于两个字节，一个字节等于8位
 */
public class byteTest {

    public static void main(String[] args) {
        char a = 'a';
        String b = "b";
        System.out.println(a);
        System.out.println(b.getBytes());

    }

}
