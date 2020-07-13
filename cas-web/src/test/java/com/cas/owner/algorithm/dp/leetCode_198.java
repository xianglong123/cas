package com.cas.owner.algorithm.dp;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 13:54 2020-06-16
 * @version: V1.0
 * @review: 打家劫舍，res = MAx(res[i-1], res[i-2] + Hi)
 */
public class leetCode_198 {

    public static int rob(int[] nums) {
        if(nums.length == 0) {return 0;}
        if(nums.length == 1) {return nums[0];}

        int first = nums[0], second = Math.max(nums[0], nums[1]);
        for (int i = 2; i <= nums.length - 1; i++) {
            int temp = second;
            second = Math.max(nums[i] + first, second);
            first = temp;
        }
        return second;
    }


    public static void main(String[] args) {
        int a[] = {1,2,3,5,10,15,3,1,20,1};
        System.out.println(rob(a));
    }
}
