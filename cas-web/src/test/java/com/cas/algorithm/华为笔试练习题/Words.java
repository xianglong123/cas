package com.cas.algorithm.华为笔试练习题;

import org.junit.Test;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 13:37 2020-06-26
 * @version: V1.0
 * @review:
 */
public class Words {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String str = in.nextLine();

            // 记录单词的个数
            int num = 1;
            // 记录单词的总长度
            int wLen = 0;

            for (Character c : str.toCharArray()) {
                if (c == ' ') {
                    num ++;
                } else {
                    wLen ++;
                }
            }
            DecimalFormat df = new DecimalFormat("#.00");
            System.out.println(df.format((double) wLen / num));
        }
    }

    @Test
    public void test() {
        char a = ' ';
        DecimalFormat df = new DecimalFormat("#.00");
        System.out.println(df.format((double) 11 / 3));
        System.out.println((double) 10 / 3);
    }

}
