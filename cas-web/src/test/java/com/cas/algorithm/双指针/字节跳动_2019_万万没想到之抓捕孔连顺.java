package com.cas.algorithm.双指针;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 15:07 2020-06-23
 * @version: V1.0
 * @review:
 * 我叫王大锤，是一名特工。我刚刚接到任务：在字节跳动大街进行埋伏，抓捕恐怖分子孔连顺。和我一起行动的还有另外两名特工，我提议
 *
 * 1. 我们在字节跳动大街的N个建筑中选定3个埋伏地点。
 * 2. 为了相互照应，我们决定相距最远的两名特工间的距离不超过D。
 *
 * 我特喵是个天才! 经过精密的计算，我们从X种可行的埋伏方案中选择了一种。这个方案万无一失，颤抖吧，孔连顺！
 * ……
 * 万万没想到，计划还是失败了，孔连顺化妆成小龙女，混在cosplay的队伍中逃出了字节跳动大街。只怪他的伪装太成功了，就是杨过本人来了也发现不了的！
 *
 * 请听题：给定N（可选作为埋伏点的建筑物数）、D（相距最远的两名特工间的距离的最大值）以及可选建筑的坐标，计算在这次行动中，大锤的小队有多少种埋伏选择。
 * 注意：
 * 1. 两个特工不能埋伏在同一地点
 * 2. 三个特工是等价的：即同样的位置组合(A, B, C) 只算一种埋伏方法，不能因“特工之间互换位置”而重复使用
 */
public class 字节跳动_2019_万万没想到之抓捕孔连顺 {

    public static void main(String[] args) {
        // 数据收集
        Scanner in = new Scanner(System.in);
        int N, D;
        N = in.nextInt();
        D = in.nextInt();

        // 字节跳动大楼先用list装，再转数组
        List<Integer> list = new ArrayList<>();
        while (in.hasNext()) {
            list.add(in.nextInt());
            if (--N == 0) {
                break;
            }
        }
        // 数据处理[典型双指针问题]
        System.out.println(lurk(list, D));
    }

    /**
     * 计算埋伏方案
     * @return
     */
    public static int lurk(List<Integer> list, int d) {
        Integer[] array = list.toArray(new Integer[0]);
        // 结果
        int res = 0;
        // 双指针
        int first = 0, end = 2;
        int len = array.length - 1;

        while (first < end) {
            if ((array[end] - array[first]) > d) {
                // first ++;
                first ++;
            } else {
                // 索引校验
                if (end - first < 2) {
                    if (end == len) {
                        break;
                    }
                    end ++;
                } else {
                    // 完美情况
                    res = ((end - first - 1) + res % 99997867) % 99997867;
                    if (end == len) {
                        first ++;
                    } else {
                        end ++;
                    }
                }
            }
        }
        return res;
    }



}
