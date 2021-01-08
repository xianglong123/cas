package com.cas.algorithm.华为笔试练习题;

import java.util.Scanner;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 10:09 2020-06-25
 * @version: V1.0
 * @review:
 */
public class 求解立方根 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            double input = in.nextDouble();
            double min = 0;
            double max = input;
            double mid = 0;
            while (max - min > 0.000001) {
                mid = (max + min) / 2;
                if (mid * mid * mid > input) {
                    max = mid;
                } else if (mid * mid * mid < input) {
                    min = mid;
                } else {
                    break;
                }
            }
            System.out.printf("%.1f\n", mid);
        }
    }


}
