package com.cas.owner.algorithm.数组;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 14:25 2020-06-24
 * @version: V1.0
 * @review: 数组异或操作
 */
public class leetCode_1486 {

    public static int xorOperation(int n, int start) {
        int sum = start;
        for (int i = 1; i < n; i++) {
            sum ^= start + 2 * i;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(xorOperation(5, 0));
    }

}
