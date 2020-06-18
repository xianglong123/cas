package com.cas.interview.数组;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 23:32 2020-06-17
 * @version: V1.0
 * @review: 重新排列数组
 */
public class leetCode_1470 {

    public int[] shuffle(int[] nums, int n) {
        // 搞一个数组保存结果
        int[] res = new int[2*n];

        for(int i = 0; i <= n - 1; i ++) {
            res[2 * i] = nums[i];
            res[2 * i + 1] = nums[i + n];
        }
        return res;
    }
}
