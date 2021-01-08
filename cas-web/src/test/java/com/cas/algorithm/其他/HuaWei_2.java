package com.cas.algorithm.其他;

import java.util.Scanner;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 20:40 2020-02-09
 * @version: V1.0
 * @review: 完美诠释什么叫标记算法：将需要的数据变向的标记出来，再做筛选
 */
public class HuaWei_2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNextInt()) {
            //测试数据个数
            int num = in.nextInt();
            //数据容器
            int[] a = new int[num];
            //循环
            for(int i = 0; i < num; i++) {
                a[i] = in.nextInt();
            }

            //标记
            int[] c = quchong(a);

            for(int i = 0; i < 1000; i++) {
                if(c[i] == 1) {
                    System.out.println(i);
                }
            }

        }
    }

    private static int[] quchong(int[] a) {
        int[] b = new int[1000];

        for(int i = 0;i < 1000; i++) {
            b[i] = 0;
        }

        for(int i = 0; i < a.length; i++) {
            b[a[i]] = 1;
        }
        return b;
    }

}
