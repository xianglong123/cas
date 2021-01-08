package com.cas.algorithm.华为笔试练习题;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 18:08 2020-06-25
 * @version: V1.0
 * @review:
 */
public class 最长上升子序列 {


    public static void main(String[] args) {
        Scanner in  = new Scanner(System.in);
        int N = in.nextInt();
        int[] nums = new int[N];
        int idx = 0;
        while (in.hasNext()) {
            nums[idx ++] = in.nextInt();
            if (--N <= 0) {
                break;
            }
        }
        List<Integer> list = new ArrayList<>();
        GetResult(N, nums, list);
        System.out.println(list.get(0));
    }


    public static int GetResult(int num, int[] nums, List pResult) {
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
        pResult.add(maxans);
        return 0;
    }


}
