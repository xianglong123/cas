package com.cas.owner.algorithm.二维数组;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 21:44 2020-06-17
 * @version: V1.0
 * @review: 螺旋矩阵II
 */
public class leetCode_59 {

    public static int[][] generateMatrix(int n) {
        // 分层添加,初始化一个结果
        int[][] res = new int[n][n];
        int rows = n, columns = n;
        int left = 0, top = 0, right = rows - 1, bottom = columns - 1;
        // 初始值
        int num = 1;
        while (left <= right && top <= bottom && num <= n*n) {
            for (int column = left; column <= right; column ++) {
                res[top][column] = num++;
            }

            for(int row = top + 1; row <= bottom; row ++) {
                res[row][right] = num ++;
            }

            if(left < right && top < bottom && num <= n*n) {
                for (int column = right - 1; column > left; column --) {
                    res[bottom][column] = num++;
                }

                for (int row = bottom; row > top; row --) {
                    res[row][left] = num++;
                }
            }
            left ++;
            top ++;
            right --;
            bottom --;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(generateMatrix(3));
    }
}
