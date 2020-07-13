package com.cas.owner.algorithm.数组;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 14:41 2020-06-24
 * @version: V1.0
 * @review:
 */
public class leetCode_1295 {

    public int findNumbers(int[] nums) {
        int res = 0;
        for (int i = 0; i <= nums.length - 1; i ++) {
            if (String.valueOf(nums[i]).length() % 2 == 0) {
                res ++;
            }
        }
        return res;
    }


    public static void main(String[] args) {
        int a = 1234;
        System.out.println(String.valueOf(a).length());
    }
}
