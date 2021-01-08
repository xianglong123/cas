package com.cas.jvm.内存溢出;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 21:18 2020-03-21
 * @version: V1.0
 * @review: 因为运行时常量池已经在java堆中了，所以我们测试一下
 */
public class RuntimeConstantPoolInternOOMTest {

    public static void main(String[] args) {
        String str1 = "java2";
        System.out.println(str1.intern() == str1);
    }
}
