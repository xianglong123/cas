package com.cas.algorithm.数组;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 22:40 2020-06-17
 * @version: V1.0
 * @review: 最佳观光组合
 */
public class leetCode_1014 {

    public static int maxScoreSightseeingPair(int[] A) {
        // 最高分
        int res = 0;
        // 空间换时间
        // 当前最高分
        int sumMax = A[0];
        for (int j = 1; j <= A.length - 1; j ++) {
            res = Math.max(sumMax + A[j] - j, res);
            sumMax = Math.max(A[j] + j, sumMax);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] a = {8, 1, 5, 2, 6};
        System.out.println(maxScoreSightseeingPair(a));
    }
}
