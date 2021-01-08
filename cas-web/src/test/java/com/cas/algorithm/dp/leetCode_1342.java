package com.cas.algorithm.dp;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 13:28 2020-06-19
 * @version: V1.0
 * @review: 将数字变成0的操作次数
 */
public class leetCode_1342 {

    public int numberOfSteps (int num) {
        int count = 0;
        while(num !=0) {
            if(num % 2 == 0) {
                num /= 2;
            } else {
                num -= 1;
            }
            count++;
        }
        return count;
    }
}
