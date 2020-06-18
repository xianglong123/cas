package com.cas.interview.其他;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 23:06 2020-06-09
 * @version: V1.0
 * @review:
 */
public class LeetCode {

    static int translateNum(int num) {
        if (num<=9) {return 1;}
        //获取输入数字的余数，然后递归的计算翻译方法
        int ba = num%100;
        //如果大于9或者大于26的时候，余数不能按照2位数字组合，比如56，只能拆分为5和6；反例25，可以拆分为2和5，也可以作为25一个整体进行翻译。
        if (ba<=9||ba>=26) {return translateNum(num/10);}
        else  {return translateNum(num/10)+translateNum(num/100);}
    }

    public static void main(String[] args) {
        System.out.println(translateNum(123));
    }
}
