package com.cas.algorithm.dp;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 15:40 2020-06-17
 * @version: V1.0
 * @review: 最长上升子序列
 */
public class leetCode_300 {

    public static int lengthOfLIS(int[] nums) {
        if(nums.length == 0) return 0;

        // 记录所有数组当前最大上升序列
        int[] dp = new int[nums.length];
        dp[0] = 1;
        // 记录最长上升子序列长度
        int res = 1;
        for (int i = 1; i <= nums.length - 1; i++) {
            // 当前最大上升子序列
            int currentRes = 0;
            for (int j = 0; j < i; j ++) {
                if(nums[i] > nums[j]) {
                    currentRes = Math.max(currentRes, dp[j]);
                }
            }
            dp[i] = currentRes + 1;
            res = Math.max(dp[i], res);
        }
        // dp[i] = max(dp[j]) + 1, 0 <= j <i
        return res;
    }

    public static int lengthOfLIS2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxans = 1;
        for (int i = 1; i < dp.length; i++) {
            int maxval = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    maxval = Math.max(maxval, dp[j]);
                }
            }
            dp[i] = maxval + 1;
            maxans = Math.max(maxans, dp[i]);
        }
        return maxans;
    }

    public static void main(String[] args) {
        int[] a = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(lengthOfLIS2(a));
    }
}
