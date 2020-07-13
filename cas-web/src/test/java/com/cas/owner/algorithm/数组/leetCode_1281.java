package com.cas.owner.algorithm.数组;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 13:40 2020-06-19
 * @version: V1.0
 * @review: 整数的各位积和之差
 */
public class leetCode_1281 {

    public int subtractProductAndSum(int n) {
        int chen = 1;
        int sum = 0;
        for(String c : ( n + "").split("")) {
            chen *= Integer.parseInt(c);;
            sum += Integer.parseInt(c);;
        }
        return chen - sum;
    }

    public int subtractProductAndSum2(int n) {
        int chen = 1;
        int sum = 0;
        while(n != 0) {
            chen *= n % 10;
            sum += n % 10;
            n /= 10;
        }
        return chen - sum;
    }


    public static void main(String[] args) {
        int chen = 1;
        for(String c : ( 123 + "").split("")) {
            chen += Integer.parseInt(c);
            System.out.println(chen);
        }
    }
}
