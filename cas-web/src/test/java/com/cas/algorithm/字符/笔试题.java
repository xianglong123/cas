package com.cas.algorithm.字符;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 20:24 2020-09-14
 * @version: V1.0
 * @review:
 * 假如 0：A 1:B 25:Z 26:AA 27:AB ...
 */
public class 笔试题 {

    public static void main(String[] args) {
        // A:65  Z:90
//        System.out.println((int)'Z');
        System.out.println(getCharByInt(10000));
        System.out.println(26*26);
    }


    public static String getCharByInt(int index) {
        if (index <= 25) {
            return String.valueOf((char)(index + 65));
        }
        return (char) (index / 26 - 1 + 65) + String.valueOf((char)(index % 26 + 65));

    }

}
