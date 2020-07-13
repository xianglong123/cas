package com.cas.owner.algorithm.回溯;

import java.util.Scanner;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 19:47 2020-06-22
 * @version: V1.0
 * @review:
 */
public class huawei_2016_解数独 {

    private static final int n = 3;
    private static final int N = n * n;

    private static int[][] rows = new int[N][N + 1];
    private static int[][] columns = new int[N][N + 1];
    private static int[][] boxs = new int[N][N + 1];
    private static int[][] board;

    // 唯一解标示
    static boolean flag = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int row2 = 0;
        // 搞一个char数组
        board = new int[N][N];
        while (scanner.hasNext()) {
            String[] s = scanner.nextLine().split(" ");
            for (int j = 0; j <= 8; j ++) {
                board[row2][j] = Integer.valueOf(s[j]);
            }
            if (++row2 > 8) {
                break;
            }
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int num = board[i][j];
                if (num != 0) {
                    placeNumber(num, i, j);
                }
            }
        }
        backtrack(0, 0);

        for (int i = 0; i <= board.length - 1; i ++) {
            for (int j = 0; j <= board[0].length - 1; j ++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }

    }

    // 递归解题
    public static void backtrack(int row, int column) {
        if (board[row][column] == 0) {
            // 循环1-9尝试
            for (int i = 1; i <= 9; i++) {
                if (canPlace(i, row, column)) {
                    placeNumber(i, row, column);
                    placeNextNumber(row, column);
                    if (!flag) removePlaceNumber(i, row, column);
                }
            }
        } else placeNextNumber(row, column);
    }

    public static void placeNextNumber(int row, int column) {
        // 判断是否已经结束，解题成功则标示置为true
        if (row == N - 1 && column == N - 1) {
            flag = true;
        } else {
            if (column == N - 1) {
                backtrack(row + 1, 0);
            } else {
                backtrack(row, column + 1);
            }
        }
    }

    // 添加数字
    public static void placeNumber(int d, int row, int column) {
        int idx = (row / 3) * 3 + column / 3;
        rows[row][d]++;
        columns[column][d]++;
        boxs[idx][d]++;
        board[row][column] = d;
    }

    // 回滚
    public static void removePlaceNumber(int d, int row, int column) {
        int idx = (row / 3) * 3 + column / 3;
        rows[row][d]--;
        columns[column][d]--;
        boxs[idx][d]--;
        board[row][column] = 0;
    }

    public static boolean canPlace(int d, int row, int column) {
        int idx = (row / 3) * 3 + column / 3;
        return rows[row][d] + columns[column][d] + boxs[idx][d] == 0;
    }
}
