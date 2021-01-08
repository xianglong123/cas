package com.cas.algorithm.华为笔试练习题;

import java.util.Scanner;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 19:12 2020-06-25
 * @version: V1.0
 * @review:
 */
public class 等差数列 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n = in.nextInt();
            int res = 2;
            int sum = 2;
            for (int i = 1; i < n; i ++) {
                res += 3;
                sum += res;
            }
            System.out.println(sum);
        }
    }
// 2 5 8 11 14
}
