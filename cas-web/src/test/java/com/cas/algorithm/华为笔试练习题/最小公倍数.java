package com.cas.algorithm.华为笔试练习题;

import java.util.Scanner;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 18:21 2020-06-24
 * @version: V1.0
 * @review:
 */
public class 最小公倍数 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        int b = in.nextInt();
        int m = min(a, b);
        System.out.println(a * b / m);


    }

    public static int min(int a, int b) {
        if (a < b) {
            int t = b;
            b = a;
            a = t;
        }

        while (b != 0) {
            if (a == b) {
                return a;
            } else {
                int k = a % b;
                a = b;
                b = k;
            }
        }
        return a;
    }


}
