package com.cas.algorithm.数组;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 16:09 2020-06-24
 * @version: V1.0
 * @review: 解压缩编码列表
 */
public class leetCode_1313 {

    public static int[] decompressRLElist(int[] nums) {
        int len = 0;
        for (int i = 0; i < nums.length; i += 2) {
            len += nums[i];
        }

        int[] res = new int[len];

        for (int i = 0, index = 0; i <= (nums.length - 1) / 2; i++) {
            for (int j = 0; j < nums[2 * i]; j++) {
                res[index ++] = nums[2 * i + 1];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4};
        System.out.println(decompressRLElist(a));
    }

}
