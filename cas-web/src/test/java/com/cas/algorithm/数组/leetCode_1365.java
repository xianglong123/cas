package com.cas.algorithm.数组;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 14:49 2020-06-24
 * @version: V1.0
 * @review: 有多少小于当前数字的数字
 */
public class leetCode_1365 {

    public static int[] smallerNumbersThanCurrent(int[] nums) {
        //桶计数算法
        int[] bucket = new int[101];
        for(int i:nums)
            bucket[i]++;

        //桶中间处理，积累前面的桶结果
        for(int i=1;i<101;i++)
            bucket[i] += bucket[i-1];

        // int[] res = new int[nums.length];
        for(int i=0;i<nums.length;i++)
            nums[i] = nums[i] > 0 ? bucket[nums[i]-1] : 0;

        return nums;
    }

    public static void main(String[] args) {
        int[] a = {8, 1, 2, 2, 3};
        System.out.println(smallerNumbersThanCurrent(a));
    }

}
