package com.cas.algorithm.快慢指针;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 14:55 2020-06-19
 * @version: V1.0
 * @review: 快乐数
 */
public class leetCode_202 {

    public boolean isHappy(int n) {
        // 搞一个hash表记录出现的和，如果包含说明不可能是快乐数
        Set<Integer> set = new HashSet<>();

        while(n != 1) {
            if(set.contains(n)) return false;

            set.add(n);
            int sum = 0;
            while (n != 0) {
                sum += (n % 10) * (n % 10);
                n /= 10;
            }
            n = sum;
        }
        return true;
    }

}
