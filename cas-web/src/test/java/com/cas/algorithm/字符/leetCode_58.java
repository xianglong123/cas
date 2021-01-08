package com.cas.algorithm.字符;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 11:43 2020-06-19
 * @version: V1.0
 * @review: 左旋转字符串
 */
public class leetCode_58 {

    public String reverseLeftWords(String s, int n) {
        return s.substring(n) + s.substring(0, n);
    }

    public String reverseLeftWords2(String s, int n) {
        char[] chars = s.toCharArray();
        StringBuilder builder = new StringBuilder();

        for (int i = n; i <= chars.length - 1; i ++) {
            builder.append(chars[i]);
        }

        for (int i = 0; i < n; i ++) {
            builder.append(chars[i]);
        }

        return builder.toString();
    }
}
