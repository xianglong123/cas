package com.cas.algorithm.dp;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 13:44 2020-06-16
 * @version: V1.0
 * @review: 动态规划 时间复杂度O(N) 空间复杂度O(1)
 */
public class leetCode_70 {

    public int climbStairs(int n) {
        // 斐波那契数
        // 楼下这三个数的思想叫滚动数组
        int a = 0, b = 0, c = 1;
        for (int i = 1; i <= n; i++) {
            a = b;
            b = c;
            c = a + b;
        }
        return c;
    }
}
