package com.cas.interview.滑动模块;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 09:57 2020-06-15
 * @version: V1.0
 * @review:
 */
public class leetCode_242 {
    public boolean isAnagram(String s, String t) {
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for (char c : s.toCharArray()) {
            need.put(c, need.get(c) == null ? 1 : need.get(c) + 1);
        }

        int right = 0;
        int valite = 0;
        char[] chars = t.toCharArray();

        while (right < chars.length) {
            // 新增数据
            char c = chars[right++];
            if(need.containsKey(c)) {
                window.put(c, window.get(c) == null ? 1 : window.get(c) + 1);
                if(window.get(c).equals(need.get(c))) {
                    valite ++;
                }
            }
        }

        if(valite == need.size()) {
            return true;
        } else {
            return false;
        }
    }
}
