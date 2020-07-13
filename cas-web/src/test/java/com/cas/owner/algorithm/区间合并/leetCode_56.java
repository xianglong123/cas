package com.cas.owner.algorithm.区间合并;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 18:14 2020-06-19
 * @version: V1.0
 * @review: 合并区间
 */
public class leetCode_56 {


    /**
     * 输入: [[1,3],[2,6],[8,10],[15,18]]
     * 输出: [[1,6],[8,10],[15,18]]
     * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/merge-intervals
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param intervals
     * @return
     */
    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(v -> v[0]));
        // 遍历区间
        int[][] res = new int[intervals.length][2];
        int idx = -1;

        for (int[] interval : intervals) {
            if (idx == -1 || interval[0] > res[idx][1]) {
                res[++idx] = interval;
            } else {
                // 反之将当前区间合并至结果数组的最后区间
                res[idx][1] = Math.max(res[idx][1], interval[1]);
            }
        }
        return Arrays.copyOf(res, idx + 1);
    }


    public static void main(String[] args) {
        int[][] values = {{1,3}, {2,6}, {8,10}, {15,18}};
        System.out.println(merge(values));
    }
}
