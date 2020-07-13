package com.cas.owner.algorithm.华为笔试练习题;

import org.junit.Test;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 22:23 2020-06-25
 * @version: V1.0
 * @review:
 */
public class 记负均正 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[] a = new int[N];
        int index = 0;
        while (in.hasNext()) {
            a[index ++] = in.nextInt();
            if(-- N <= 0) {
                break;
            }
        }

        // 负数个数记录
        int fu = 0;
        // 正数和记录
        int sum = 0;
        // 正数个数记录
        int zheng = 0;

        for (int i = 0; i <= a.length - 1; i ++) {
            if (a[i] < 0) {
                fu ++;
            } else {
                zheng ++;
                sum += a[i];
            }
        }

        DecimalFormat df = new DecimalFormat("#.0");
        DecimalFormat df2 = new DecimalFormat("#");
        System.out.print(fu + " " + (sum % zheng == 0 ? df2.format((double)sum / zheng) : df.format((double)sum / zheng)));
    }

    @Test
    public void test() {
        int a = 100;
        DecimalFormat df = new DecimalFormat("#");
        System.out.println(df.format(4.546));
        System.out.printf("%.1f\n", Math.ceil((double) a / 3));
    }

}
