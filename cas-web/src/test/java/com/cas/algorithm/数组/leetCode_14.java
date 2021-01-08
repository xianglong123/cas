package com.cas.algorithm.数组;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 22:27 2020-06-16
 * @version: V1.0
 * @review: 最长公共前缀
 */
public class leetCode_14 {

    public static String longestCommonPrefix(String[] strs) {
        if(strs.length == 0) {return "";}
        // 公共前缀
        String suffix = "";
        // 标准字符
        String flag = strs[0];
        int index = 1;
        while (index <= flag.length()) {
            for(String str : strs) {
                if (!str.startsWith(flag.substring(0, index))) {
                    return flag.substring(0, index - 1);
                }
            }
            index ++;
        }
        return flag.substring(0, index - 1);
    }

    public static void main(String[] args) {
        String[] strings = {"a"};
        System.out.println(longestCommonPrefix(strings));
    }



}
