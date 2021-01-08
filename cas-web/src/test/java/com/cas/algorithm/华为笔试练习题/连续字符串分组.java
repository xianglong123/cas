package com.cas.algorithm.华为笔试练习题;

import org.junit.Test;

import java.util.Scanner;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 10:37 2020-06-25
 * @version: V1.0
 * @review:
 */
public class 连续字符串分组 {

    public static void main(String[] args) {
        Scanner in  = new Scanner(System.in);
        int N = in.nextInt();
        String[] strings = new String[N];
        int index = 0;
        while (in.hasNext()) {
            strings[index++] = in.next();
            N --;
            if (N <= 0) {
                break;
            }
        }

        // 搞一个函数对字符串进行8位补位输出
        for (String str : strings) {
            showEight(str);
        }
    }



    public static void showEight(String str) {
        StringBuilder stringBuilder = new StringBuilder(str);
        for (int i = 0; i < getLen(str) - str.length(); i ++) {
            stringBuilder.append("0");
        }

        for (int i = 0; i < getLen(str) / 8; i ++) {
            System.out.println(stringBuilder.subSequence(i * 8, i * 8 + 8));
        }


    }

    public static int getLen(String str) {
        return (str.length() / 8) * 8 + (str.length() % 8 == 0 ? 0 : 8);
    }

    @Test
    public void test() {
        int a = 1;
        System.out.println(String.format("%d0", a));
    }

}
