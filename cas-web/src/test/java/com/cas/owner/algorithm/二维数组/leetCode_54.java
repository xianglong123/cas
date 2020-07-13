package com.cas.owner.algorithm.二维数组;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 19:38 2020-06-17
 * @version: V1.0
 * @review: 螺旋矩阵，分层
 */
public class leetCode_54 {

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> order = new ArrayList<>();
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return order;
        }

        int rows = matrix.length, columns = matrix[0].length;
        int left = 0, right = columns - 1, top = 0, bottom = rows - 1;
        while(left <= right && top <= bottom) {
            for (int column = left; column <= right; column++) {
                order.add(matrix[top][column]);
            }
            for (int row = top + 1; row <= bottom; row++) {
                order.add(matrix[row][right]);
            }
            if (left < right && top < bottom) {
                for (int column = right - 1; column > left; column--) {
                    order.add(matrix[bottom][column]);
                }
                for (int row = bottom; row > top; row--) {
                    order.add(matrix[row][left]);
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return order;
    }


    public static void main(String[] args) {
        int[][] a = {{1,2,3},{4,5,6},{7,8,9},{1,2,3}};
        System.out.println(spiralOrder(a));
        System.out.println(a.length);
        System.out.println(a[0].length);
        System.out.println(a[1][2]);
    }
}
