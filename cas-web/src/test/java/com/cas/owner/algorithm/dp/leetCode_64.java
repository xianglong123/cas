package com.cas.owner.algorithm.dp;

import java.util.Scanner;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 19:24 2020-06-17
 * @version: V1.0
 * @review: 最小路径和
 */
public class leetCode_64 {

    public int minPathSum(int[][] grid) {

        // grid(i,j)=grid(i,j)+min(grid(i+1,j),grid(i,j+1))
        return -1;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNextInt()) {//注意while处理多个case              int a = in.nextInt();
            int n = Integer.valueOf(in.next());
            int res = 0;


            while(n != 0) {
                if (n < 2) {
                    break;
                }

                if(n >= 3) {
                    res += n / 3;
                } else if (n == 2) {
                    res += 1;
                    break;
                }
                n = n / 3 + n % 3;
            }
            System.out.println(res);
        }
    }
}
