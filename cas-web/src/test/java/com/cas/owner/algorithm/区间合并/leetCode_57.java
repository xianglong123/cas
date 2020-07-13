package com.cas.owner.algorithm.区间合并;

import java.util.Arrays;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 18:52 2020-06-19
 * @version: V1.0
 * @review: 插入区间【未完成】
 */
public class leetCode_57 {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        int[][] res = new int[intervals.length][2];
        int idx = -1;
        for (int[] interval : intervals) {
            if (idx == -1 || newInterval[0] > res[idx][1]) {
                res[++idx] = interval;
            } else {
                // 反之将当前区间合并至结果数组的最后区间
                res[idx][1] = Math.max(res[idx][1], interval[1]);
            }
        }
        return Arrays.copyOf(res, idx + 1);
    }
}
