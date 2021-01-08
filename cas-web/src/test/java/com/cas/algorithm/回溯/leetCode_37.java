package com.cas.algorithm.回溯;

import org.junit.Test;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 18:47 2020-06-22
 * @version: V1.0
 * @review: 解数独 【hard】
 */
public class leetCode_37 {

    private static final int n = 3;
    private static final int N = n * n;

    private static int[][] rows = new int[N][N + 1];
    private static int[][] columns = new int[N][N + 1];
    private static int[][] boxs = new int[N][N + 1];
    char[][] board;

    // 唯一解标示
    boolean flag = false;


    public void solveSudoku(char[][] board) {
        this.board = board;
        // 先构建一个二维数组，再解题
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char num = board[i][j];
                if (num != '.') {
                    int d = Character.getNumericValue(num);
                    placeNumber(d, i, j);
                }
            }
        }
        backtrack(0, 0);
    }

    // 递归解题
    public void backtrack(int row, int column) {
        if (board[row][column] == '.') {
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

    public void placeNextNumber(int row, int column) {
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
    public void placeNumber(int d, int row, int column) {
        int idx = (row / 3) * 3 + column / 3;
        rows[row][d]++;
        columns[column][d]++;
        boxs[idx][d]++;
        board[row][column] = (char) (d + '0');
    }

    // 回滚
    public void removePlaceNumber(int d, int row, int column) {
        int idx = (row / 3) * 3 + column / 3;
        rows[row][d]--;
        columns[column][d]--;
        boxs[idx][d]--;
        board[row][column] = '.';
    }

    public boolean canPlace(int d, int row, int column) {
        int idx = (row / 3) * 3 + column / 3;
        return rows[row][d] + columns[column][d] + boxs[idx][d] == 0;
    }

    @Test
    public void test() {
        char[][] data = {{'5', '3', '.', '.', '7', '.', '.', '.', '.'}, {'6', '.', '.', '1', '9', '5', '.', '.', '.'}, {'.', '9', '8', '.', '.', '.', '.', '6', '.'}, {'8', '.', '.', '.', '6', '.',
                '.', '.', '3'}, {'4', '.', '.', '8', '.', '3', '.', '.', '1'}, {'7', '.', '.', '.', '2', '.', '.', '.', '6'}, {'.', '6', '.', '.', '.', '.', '2', '8', '.'}, {'.', '.', '.', '4', '1'
                , '9', '.', '.', '5'}, {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        solveSudoku(data);
    }

}
