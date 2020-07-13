package com.cas.owner.algorithm.华为笔试练习题;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 11:11 2020-06-26
 * @version: V1.0
 * @review:
 */
public class 在字符串中找出连续最长的数字串 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String str = in.nextLine();
            str += "a";
            // 最长长度
            int maxLen = 0;
            // 最长字符
            List<String> list = new ArrayList<>();
            // 当前最长数字字符
            StringBuilder stringBuilder = new StringBuilder();

            for (Character c : str.toCharArray()) {
                if (c >= '0' && c <= '9') {
                    stringBuilder.append(c);
                } else {
                    maxLen = Math.max(maxLen, stringBuilder.length());
                    if (stringBuilder.length() > 0) {
                        list.add(stringBuilder.toString());
                    }
                    stringBuilder = new StringBuilder();
                }
            }

            for (String strl : list) {
                if (strl.length() == maxLen) {
                    System.out.print(strl + ",");
                }
            }
            System.out.print(maxLen);
        }
    }
}
