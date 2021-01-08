package com.cas.algorithm.数组;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 21:39 2020-06-15
 * @version: V1.0
 * @review:
 */
public class leetCode_9 {

    public boolean isPalindrome(int x) {
        String str = Integer.toString(x);
        return str.equals(new StringBuilder(str).reverse().toString());
    }

    public boolean isPalindrome2(int x) {
        Integer i = x;
        int reverse = Integer.reverse(x);
        System.out.println(reverse);

        return false;
    }

    static boolean isPalindrome3(int x) {
        if (0 == x % 10 && x != 0) return false;
        int y = 0;
        while (x > y) {
            // y 一位一位的加
            y = y * 10 + x % 10;
            // x 一位一位的减
            x /= 10;
        }
        return x == y || x == y / 10;

    }


    public static void main(String[] args) {
        System.out.println(isPalindrome3(-121));
    }

}
