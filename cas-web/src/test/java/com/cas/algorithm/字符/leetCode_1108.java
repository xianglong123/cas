package com.cas.algorithm.字符;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 14:32 2020-06-24
 * @version: V1.0
 * @review: ip 地址无效化
 */
public class leetCode_1108 {

    public static String defangIPaddr(String address) {
        return address.replaceAll("\\.", "\\[\\.\\]");
    }

    public static void main(String[] args) {
        System.out.println(defangIPaddr("1.1.1.1"));
    }
}
