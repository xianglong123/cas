package com.cas.owner.algorithm.华为笔试练习题;

import java.util.Scanner;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 22:02 2020-06-25
 * @version: V1.0
 * @review:
 */
public class 自守数 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            System.out.println(CalcAutomorphicNumbers(in.nextInt()));
        }
    }

    public static int CalcAutomorphicNumbers(int n) {
        // (n * n - n) % 100 == 0;
        int res = 0;
        for (int i = 0; i <= n; i ++) {
            if((i * i - i) % getLen(i) == 0) {
                System.out.println(i);
                res ++;
            }
        }
        return res;
    }

    public static int getLen(int n) {
        int res = 1;
        while(n != 0) {
            n /= 10;
            res *= 10;
        }
        return res;
    }

}
