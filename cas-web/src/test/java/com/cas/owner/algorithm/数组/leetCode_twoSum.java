package com.cas.owner.algorithm.数组;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 11:59 2020-06-15
 * @version: V1.0
 * @review: 无序数组求两数之和等于target，用map辅助
 */
public class leetCode_twoSum {

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i <= nums.length - 1; i++) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i ++) {
            int complate = target - nums[i];
            if (map.containsKey(complate) && map.get(complate) != i) {
                return new int[] {i, map.get(complate)};
            }
        }
        return null;
    }


    public static void main(String[] args) {
        int nums[] = {3,3};
        twoSum(nums, 6);
    }
}
