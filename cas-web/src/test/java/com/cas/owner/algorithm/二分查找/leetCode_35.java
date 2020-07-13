package com.cas.owner.algorithm.二分查找;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 17:04 2020-06-17
 * @version: V1.0
 * @review: 搜索插入位置
 */
public class leetCode_35 {

    public int searchInsert(int[] nums, int target) {
        for(int i = 0; i <= nums.length - 1; i++) {
            if(target <= nums[i]) {
                return i;
            }
        }
        return nums.length;
    }


    /**
     * 典型二分法
     */
    public static int serachInsert2(int[] nums, int target) {
        // 搞两左右标
        int left = 0, right = nums.length - 1;
        while(left <= right) {
            int mid = (left + right) / 2;
            if(nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
