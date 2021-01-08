package com.cas.algorithm.滑动模块;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 14:30 2020-06-14
 * @version: V1.0
 * @review:
 */
public class leetCode76 {
    public static String findAnagrams(String s, String p) {
        // need里面装需要出现的次数
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for (char c : p.toCharArray()) {
            need.put(c, need.get(c) == null ? 1 : need.get(c) + 1);
        }
        int left = 0;
        int right = 0;
        //出现的总次数
        int valid = 0;

        // 记录最小覆盖子串的起始索引及长度
        int start = 0, len = Integer.MAX_VALUE;
        char[] chars = s.toCharArray();

        while (right < chars.length) {
            // c 是将移入窗口的字符
            char c = chars[right];
            // 右移窗口
            right++;
            // 进行窗口内数据的一系列更新
            if (need.containsKey(c)) {
                window.put(c, window.get(c) == null ? 1 : window.get(c) + 1);
                if (window.get(c).equals(need.get(c))) {
                    valid++;
                }
            }

            // 判断左窗口是否要收缩
            while (valid == need.size()) {
                // 在这里更新最小覆盖子串
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                // d 是将移除窗口的字符
                char d = chars[left];
                // 左移窗口
                left++;
                // 进行窗口内数据的一系列更新
                if (need.containsKey(d)) {
                    if (window.get(d).equals(need.get(d))) {
                        valid--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }

    public static void main(String[] args) {
        System.out.println(findAnagrams("ADOBECODEBANC", "ABC"));
    }
}
