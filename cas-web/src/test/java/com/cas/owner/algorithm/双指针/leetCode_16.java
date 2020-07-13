package com.cas.owner.algorithm.双指针;

import java.util.Arrays;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 14:05 2020-06-24
 * @version: V1.0
 * @review: 最接近的三数之和
 */
public class leetCode_16 {

    public int threeSumClosest(int[] nums, int target) {
        // 排序
        Arrays.sort(nums);
        int min = 0;
        int first = 0, end = 2;
        int len = nums.length - 1;

        while (end <= len) {
            if (nums[first] - nums[end] > 0) {
                // 1 2 3 4 5   8
            }


        }


        return -1;
    }

}
