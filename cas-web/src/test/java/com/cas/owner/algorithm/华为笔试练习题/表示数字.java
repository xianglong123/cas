package com.cas.owner.algorithm.华为笔试练习题;

import java.util.Scanner;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 22:52 2020-06-25
 * @version: V1.0
 * @review:
 */
public class 表示数字 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            String str = in.nextLine();
            StringBuilder stringBuilder = new StringBuilder();
            for (Character c : str.toCharArray()) {
                if (c >= 48 && c <= 57) {
                    stringBuilder.append("*").append(c).append("*");
                } else {
                    stringBuilder.append(c);
                }
            }
            System.out.println(stringBuilder.toString().replace("**", ""));
        }


    }

}
