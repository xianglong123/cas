package com.cas.algorithm.回溯;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 18:46 2020-06-23
 * @version: V1.0
 * @review: 有效的数独
 */
public class leetCode_36 {

    static int n = 3;
    static int N = n * n;

    static int[][] rows = new int[N][N + 1];
    static int[][] columns = new int[N][N + 1];
    static int[][] boxs = new int[N][N + 1];

    static boolean isFalg = true;

    public static boolean isValidSudoku(char[][] board) {

        for (int i = 0; i < 9; i++) {
            if(!isFalg) {
                break;
            }
            for (int j = 0; j < 9; j++) {
                char d = board[i][j];
                if (d != '.') {
                    if(!isOk(Character.getNumericValue(d), i, j)) {
                        isFalg = false;
                        break;
                    }
                }
            }
        }
        return isFalg;
    }

    // 判断是否有效
    public static boolean isOk(int d, int row, int column) {
        int idx = (row / 3) * 3 + column / 3;
        rows[row][d] ++;
        columns[column][d] ++;
        boxs[idx][d] ++;
        return rows[row][d] + columns[column][d] + boxs[idx][d] == 3;
    }

    public static void main(String[] args) {
        char[][] a = {{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}};
        System.out.println(isValidSudoku(a));
    }

}
