package com.cas.interview.滑动模块;

import java.util.Arrays;

class Solution {
    public static int findLength(int[] A, int[] B) {
        if (A == null || B == null) {
            return 0;
        }
        int res = 0;
        int[][] dp = new int[A.length + 1][B.length + 1];
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                dp[i][j] = A[i - 1] == B[j - 1] ? dp[i - 1][j - 1] + 1 : 0;
                res = Math.max(res, dp[i][j]);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int a[] = {1,2,3,2,1};
        int b[] = {3,2,1,4,7};
        System.out.println(findLength(a, b));
        String as = "s";
        System.out.println(as.toCharArray());
    }

}