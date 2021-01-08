package com.cas.algorithm.数组;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 11:50 2020-06-15
 * @version: V1.0
 * @review:
 */
public class leetCode_167 {
    public int[] twoSum(int[] numbers, int target) {
        int first = 0;
        int end = numbers.length - 1;
        while (first <= end) {
            int sum = numbers[first] + numbers[end];
            if(sum == target) {
                int a[] = {first + 1, end + 1};
                return a;
            } else if (sum < target) {
                first ++;
            } else {
                end --;
            }
        }
        return null;
    }

}
