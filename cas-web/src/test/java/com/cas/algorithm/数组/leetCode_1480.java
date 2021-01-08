package com.cas.algorithm.数组;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 00:12 2020-06-17
 * @version: V1.0
 * @review:
 */
public class leetCode_1480 {

    public int[] runningSum(int[] nums) {
        // 保存结果
        int[] res = new int[nums.length];
        // 记录当前和
        int sum = 0;

        for (int i = 0; i <= nums.length; i ++) {
            sum += nums[i];
            res[i] = sum;
        }
        return res;
    }
}
