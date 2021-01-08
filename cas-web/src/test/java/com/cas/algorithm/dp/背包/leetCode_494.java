package com.cas.algorithm.dp.背包;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 15:58 2020-06-18
 * @version: V1.0
 * @review: 目标和[中]
 */
public class leetCode_494 {

    public static int findTargetSumWays(int[] nums, int S) {
        int[][] dp = new int[nums.length][2001];
        dp[0][nums[0] + 1000] = 1;
        dp[0][-nums[0] + 1000] += 1;
        for (int i = 1; i < nums.length; i++) {
            for (int sum = -1000; sum <= 1000; sum++) {
                if (dp[i - 1][sum + 1000] > 0) {
                    dp[i][sum + nums[i] + 1000] += dp[i - 1][sum + 1000];
                    dp[i][sum - nums[i] + 1000] += dp[i - 1][sum + 1000];
                }
            }
        }
        return S > 1000 ? 0 : dp[nums.length - 1][S + 1000];
    }


    public static void main(String[] args) {
        int[] a = {1,1,1,1,1};
        findTargetSumWays(a, 3);
    }


}
