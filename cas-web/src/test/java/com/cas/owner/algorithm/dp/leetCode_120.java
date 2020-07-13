package com.cas.owner.algorithm.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 15:01 2020-06-17
 * @version: V1.0
 * @review: 三角形最小路径和
 */
public class leetCode_120 {

    public static int minimumTotal(List<List<Integer>> triangle) {
        // 记录一个数组
        int[] a = new int[triangle.size()];
        for (List<Integer> list : triangle) {
            for (int i = list.size() - 1; i >= 0 ; i --) {
                if(i == 0) {
                    a[i] = list.get(i) + a[i];
                } else if(i == list.size() - 1) {
                    a[i] = list.get(i) + a[i - 1];
                } else {
                    a[i] = Math.min(a[i], a[i - 1]) + list.get(i);
                }
            }
        }
        Arrays.sort(a);
        return a[0];
    }


    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Arrays.asList(2));
        triangle.add(Arrays.asList(3,4));
        triangle.add(Arrays.asList(6,5,7));
        triangle.add(Arrays.asList(4,1,8,3));
        System.out.println(minimumTotal(triangle));

    }


}
