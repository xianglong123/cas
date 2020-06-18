package com.cas.interview.dp;

import java.util.Arrays;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 16:21 2020-06-16
 * @version: V1.0
 * @review: 零钱兑换
 *
 * 不会待定。。。。
 */
public class leetCode_322 {

    public static int coinChange(int[] coins, int amount) {
        if(amount == 0) {return 0;}
        Arrays.sort(coins);
        int ans = Integer.MAX_VALUE;
        return coinChange(coins, amount, 0, 0, ans) == Integer.MAX_VALUE ? -1 : ans;
    }

    public static int coinChange(int[] coins, int amount, int c_index, int count, int ans) {
        if(amount == 0) {
            return Math.min(ans, count);
        }
        if(c_index == coins.length) return ans;

        for (int k = amount / coins[c_index]; k >= 0 && k + count < ans; k--) {
            coinChange(coins, amount - k * coins[c_index], c_index + 1, count + k, ans);
        }
        return ans;
    }

    public static void main(String[] args) {
        int a[] = {1,5,6};
        System.out.println(coinChange(a, 15));
    }

}
