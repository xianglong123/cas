package com.cas.algorithm.字符;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 17:52 2020-06-22
 * @version: V1.0
 * @review: 给一串有重复字符的字符集，去重，输出剩下的结果
 */
public class huawei_2016_字符集合 {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            char[] res = in.next().toCharArray();

            // 去重
            Set<Character> set = new HashSet<>();
            StringBuilder stringBuilder = new StringBuilder();
            for (Character c : res) {
                if (set.add(c)) {
                    stringBuilder.append(c);
                }
            }

            System.out.println(stringBuilder.toString());
        }
    }

}
