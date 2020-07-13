package com.cas.owner.algorithm.数组;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 19:20 2020-06-23
 * @version: V1.0
 * @review: 二进制求和
 */
public class leetCode_67 {

    public static String addBinary(String a, String b) {
        // 数据准备
        int car = 0;
        StringBuilder str = new StringBuilder();

        for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0; i--, j--) {
            int sum = car;
            sum += i >= 0 ? a.charAt(i) - '0' : 0;
            sum += j >= 0 ? b.charAt(j) - '0' : 0;
            str.append(sum % 2);
            car = sum / 2;
        }
        str.append(car == 1 ? car : "");
        return str.reverse().toString();
    }


    public static void main(String[] args) {
        System.out.println(addBinary("1010", "1011"));
    }

}
