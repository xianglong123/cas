package com.cas.algorithm.数组;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 21:25 2020-06-15
 * @version: V1.0
 * @review:
 */
public class leetCode_7 {

    public static int reverse(int x) {
        String xString = Integer.toString(x);
        String s = xString;
        int flag = 1;
        if(x < 0) {
            // 保存符号位和转换成正整数
            flag = -1;
            s = xString.substring(1);
        }
        try {
            return Integer.valueOf(new StringBuilder(s).reverse().toString()) * flag;
        } catch (Exception e) {
            return 0;
        }
    }
}
