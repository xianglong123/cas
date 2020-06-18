package com.cas.interview.dp;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 15:36 2020-06-16
 * @version: V1.0
 * @review: 最大子序和
 */
public class leetCode_53 {

    public static int maxSubArray(int[] nums) {
        if(nums.length == 1) {return nums[0];}
        int sum = nums[0];
        int res = nums[0];
        for(int i = 1; i <= nums.length - 1; i ++) {
            if(sum > 0) {
                sum += nums[i];
            } else {
                sum = nums[i];
            }
            res = Math.max(res, sum);
        }
        return res;
    }

    /**
     * 动态规划，f(x) = f(x - 1) > 0 ? f(x - 1) : 0 + h(x);
     * @param nums
     * @return
     */
    public static int maxSubArray2(int[] nums) {
        if(nums.length == 1) {return nums[0];}
        int sum = nums[0];
        // 存最大值
        int res = nums[0];
        for (int i = 1; i <= nums.length - 1; i ++) {
            sum = (sum > 0 ? sum : 0) + nums[i];
            res = Math.max(res, sum);
        }
        return res;
    }



    public static void main(String[] args) {
        int[] a = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray2(a));
    }

}
