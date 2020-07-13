package com.cas.owner.algorithm.其他;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 19:47 2020-02-09
 * @version: V1.0
 * @review:
 */
public class HuaWei_1 {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {//注意while处理多个case              int a = in.nextInt();
            list.add(sc.nextInt());
        }

        for(Integer hasWaterPlus : list) {
            //一共交换的瓶子的和
            int count = 0;
            while(hasWaterPlus >= 2) {
                if(hasWaterPlus == 2) {
                    count ++;
                    break ;
                }
                count += hasWaterPlus/3;
                hasWaterPlus = hasWaterPlus/3 + hasWaterPlus%3;
            }
            //10/3=3   1   ,   4/3=1...1, 1+1=2
            System.out.println(count);
        }
    }
}
