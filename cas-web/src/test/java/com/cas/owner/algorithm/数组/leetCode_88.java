package com.cas.owner.algorithm.数组;

import java.util.Arrays;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 18:01 2020-06-15
 * @version: V1.0
 * @review:
 */
public class leetCode_88 {

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int firstA = m;
        int endA = nums1.length - 1;
        int firstB = 0;
        int endB = nums2.length - 1;

        while (firstA <= endA && firstB <= endB) {
            // 需要比较的数据
            if(nums1[firstA] == 0) {
                nums1[firstA++] = nums2[firstB++];
            } else {
                firstA++;
            }
        }
        Arrays.sort(nums1);
    }

    public static void main(String[] args) {
        int[] a = {1,2,3,0,0,0};
        int[] b = {2,5,6};
        merge(a, 3, b, 3);

    }

}
