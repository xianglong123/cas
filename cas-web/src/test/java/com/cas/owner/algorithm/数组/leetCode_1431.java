package com.cas.owner.algorithm.数组;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 23:25 2020-06-17
 * @version: V1.0
 * @review: 加一颗糖变成最多持有糖的孩子
 */
public class leetCode_1431 {


    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        // 第一层循环找到最大的值
        int max = 0;
        for(int i = 0; i <= candies.length - 1; i ++) {
            max = Math.max(max, candies[i]);
        }

        // 第二层循环找到最终解
        List<Boolean> res = new ArrayList<>();
        for (int i = 0; i <= candies.length - 1; i ++) {
            if (candies[i] + extraCandies >= max)
                res.add(true);
            else
                res.add(false);
        }
        return res;
    }
}
