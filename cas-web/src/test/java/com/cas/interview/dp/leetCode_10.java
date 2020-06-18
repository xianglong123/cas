package com.cas.interview.dp;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 22:33 2020-06-17
 * @version: V1.0
 * @review: 小青蛙跳台阶
 */
public class leetCode_10 {

    public static int numWays(int n) {
        // f(n) = f(n - 1)  0 1 2 3 5 8 13 21
        // 滑动数组
        int a0 = 0, a1 = 1, sum = 1;
        for (int i = 1; i < n; i ++) {
            a0 = a1;
            a1 = sum;
            sum = a0 + a1;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(numWays(2));
    }
}
