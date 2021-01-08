package com.cas.algorithm.华为笔试练习题;

import java.util.Scanner;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 13:53 2020-06-26
 * @version: V1.0
 * @review:
 */
public class 整数对最小和 {

    static int[] array1 = null;
    static int[] array2 = null;
    static int aLen = 0;
    static int bLen = 0;

    static int k = 0;

    // 数据处理 最小和
    static int sum = 0;


    // 本题为考试多行输入输出规范示例，无需提交，不计分。

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 数组个数
        int N = 2;


        for (int i = 0; i < N; i++) {
            // 数组的长度
            int n = sc.nextInt();
            if (i == 0) {
                array1 = new int[n];
            } else {
                array2 = new int[n];
            }

            for (int j = 0; j < n; j++) {

                if (i == 0) {
                    array1[j] = sc.nextInt();
                } else {
                    array2[j] = sc.nextInt();
                }
            }
        }

        // 需要取多少对元素
        k = sc.nextInt();
        // 数据处理 最小和
        sum = 0;
        // 两数组的长度
        aLen = array1.length;
        bLen = array2.length;
        getRes(0, 0);
        System.out.println(sum);
    }

    public static void getRes(int aIndex, int bIndex) {
        for (int i = aIndex; i < aLen; i ++) {
            for (int j = bIndex; j < bLen; j ++) {
                if (i < aLen - 1 && array1[i] + array2[j] <= array1[i + 1] + array2[bIndex]) {
                    sum += array1[i] + array2[j];
                    System.out.println("所选数组：" + array1[i] + "  " + array2[j] + "结果：" + (array1[i] + array2[j]));
                    k --;
                } else if (i == aLen - 1){
                    if (array1[i] + array2[j] <= array1[0] + array2[j + 1]) {
                        sum += array1[i] + array2[j];
                        System.out.println("所选数组：" + array1[i] + "  " + array2[j] + "结果：" + (array1[i] + array2[j]));
                        k--;
                    } else {
                        getRes(0, j);
                    }// 9 + 5 + 17 + 43 + 26 = 31 + 69
                } else {
                    break;
                }
                if (k == 0) {
                    break;
                }
            }
            if (k == 0) {
                break;
            }
        }


    }

}
