package com.cas.algorithm.华为笔试练习题;

import java.util.Scanner;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 10:28 2020-06-25
 * @version: V1.0
 * @review:
 */
public class 颠倒字符串 {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String str = in.nextLine();
            StringBuilder stringBuilder = new StringBuilder(str);

            System.out.println(stringBuilder.reverse());
        }
    }
}
