package com.cas.interview.滑动模块;

/**
 * 滑动模块
 */
class Solution2 {
    public static int minSubArrayLen(int s, int[] nums) {
        int first = 0;
        int end = -1 ;
        int min = nums.length + 1;
        int val = 0;
        while(end < nums.length) {
            if(val >= s) {
                val -= nums[first];
                first ++;
            }else if (end < nums.length - 1) {
                end ++ ;
                val += nums[end];
            } else {
                break;
            }
            if(val >= s && end - first + 1 < min) {
                min = end - first + 1;
            }
        }
        if(min == nums.length + 1) {
            return 0;
        }
        return min;
    }


    public static void main(String[] args) {
        int[] a = {2,3,1,2,4,3};
        System.out.println(a.length);
        System.out.println(minSubArrayLen(7, a));
    }
}