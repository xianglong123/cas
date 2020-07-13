package com.cas.owner.algorithm.数组;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 13:33 2020-06-19
 * @version: V1.0
 * @review: 宝石和石头
 */
public class leetCode_771 {

    public int numJewelsInStones(String J, String S) {
        Set<Character> set = new HashSet<>();
        int count = 0;
        for (char c : J.toCharArray()) {
            set.add(c);
        }

        for(char c : S.toCharArray()) {
            if(set.contains(c)) {
                count ++;
            }
        }
        return count;
    }
}
