package com.cas.interview.滑动模块;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 16:18 2020-06-14
 * @version: V1.0
 * @review:
 */
public class leetCode438 {

    public static List<Integer> findAnagrams(String s, String p) {
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        // 先保存需要出现的数据
        for (char c : p.toCharArray()) {
            need.put(c, need.get(c) == null ? 1 : need.get(c) + 1);
        }
        int left = 0;
        int right = 0;
        int valite = 0;
        int len = p.length();
        char[] chars = s.toCharArray();
        List<Integer> list = new ArrayList<>();
        while (right < chars.length) {
            // 新增的数据
            char a = chars[right];
            right ++;

            // 符合要求 我们这么做
            if(need.containsKey(a)) {
                window.put(a, window.get(a) == null ? 1 : window.get(a) + 1);
                if(window.get(a).equals(need.get(a))) {
                    valite ++;
                } else if (window.get(a) > need.get(a)) {
                    while (window.get(a) > need.get(a)) {
                        window.put(chars[left], window.get(chars[left]) - 1);
                        if(!window.get(chars[left]).equals(need.get(chars[left]))) {
                            valite--;
                        }
                        left++;

                    }
                }
            } else {
                // 连续不符合条件则清除
                window.clear();
                valite = 0;
                left = right;
            }

            while(valite == need.size()) {
                list.add(left);
                // 需要移除的元素
                char b = chars[left++];
                if(need.containsKey(b)) {
                    window.put(b, window.get(b) - 1);
                    valite--;
                }
            }
        }
        return list;
    }


    public static void main(String[] args) {
        findAnagrams("abaacbabc", "abc").forEach(System.out::println);
    }

}
