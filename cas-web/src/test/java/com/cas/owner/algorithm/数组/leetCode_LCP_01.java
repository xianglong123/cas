package com.cas.owner.algorithm.数组;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 13:02 2020-06-19
 * @version: V1.0
 * @review: 猜数字
 */
public class leetCode_LCP_01 {

    public int game(int[] guess, int[] answer) {
        // 记录结果
        int res = 0;
        for (int i = 0; i < 3; i ++) {
            if(guess[i] == answer[i]) {
                res ++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println((1 ^ 3));
    }
}
