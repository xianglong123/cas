package com.cas.owner.algorithm.其他;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 21:53 2020-02-09
 * @version: V1.0
 * @review:
 */
public class AiQiYi_1 {
    /**
     * 272 467 787 459
     * <p>
     * 对应输出应该为:
     * <p>
     * 3652264
     *
     *
     * 867 482 774 322
     *
     * 对应输出应该为:
     *
     * 1271376
     *
     * 用例:
     * 208 29 962 483
     *
     * 对应输出应该为:
     *
     * 1501968
     *
     * 161*161*29 = 751709
     * 483-161-161-29 = 132
     * 208-66-161 = -19
     * 208 - 161 = 47
     * 66 + 19 = 85
     * 208*29*85 = 512720
     * 208*29*246=
     * 47*29*161=
     *
     * @param args
     */

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<Integer> list = new ArrayList<>();
        while (in.hasNextInt()) {
            int a = in.nextInt();
            int b = in.nextInt();
            int c = in.nextInt();
            int n = in.nextInt();

            //硬核排序
            int x = (a < b ? a : b) < c ? (a < b ? a : b) : c;
            int z = (a > b ? a : b) > c ? (a > b ? a : b) : c;
            int y = a + b + c - x - z;

            //一共可以切的刀数
            int count = x + y + z - 3;
            //实际切的刀数
            int nCount = n < count ? n : count;

            // x,y,z边剩余刀数
            int xYu = x - 1;
            int yYu = y - 1;
            int zYu = z - 1;

            //每条边承受最大刀数
            int everyDaoNum = nCount / 3;

            //x,y,z会承受的刀数
            int xDaoNum = xYu > everyDaoNum ? everyDaoNum : xYu;
            int yDaoNum = yYu > everyDaoNum ? everyDaoNum : yYu;
            int zDaoNum = zYu > everyDaoNum ? everyDaoNum : zYu;

            //多余的刀数
            int exertDaoNum = nCount - xDaoNum - yDaoNum - zDaoNum;

            //多余刀数在y和z的分布
//            int yExert = (yYu-yDaoNum) > exertDaoNum ? ;

            //总的块数
            int kNum = (xDaoNum + 1) * (yDaoNum + 1) * (zDaoNum + 1) + exertDaoNum * (xDaoNum + 1) * (yDaoNum + 1);
            System.out.println(kNum);
        }


    }
    //0 1 2 3  4  5  6   7  8  9 10 11  12  n
    //1 2 4 8 12 18 27  36 48 64 80 100 125 ？
    //1 1 2 4  4  6  9  9  12 16 16 20  25
    // 刀是3的倍数 ((n/3+1)^2)*(n/3+1)
    // 刀数有余为1 ((n/3+1)^2)*(n/3+2)
    // 刀数有余为2 ((n/3+2)^2)*(n/3+1)


}
