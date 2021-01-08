package com.cas.algorithm.滑动模块;

import java.util.HashSet;
import java.util.Set;

class Solution3 {
    public static int lengthOfLongestSubstring(String s) {
        int first = 0, end = 1, res = 0;
        char[] a = s.toCharArray();
        if (a.length == 1) {return 1;}
        Set<Character> set = new HashSet<>();
        while(end < a.length) {
            for (int i = first; i < end; i ++) {
                if(a[end] == a[i]) {
                    first = i + 1;
                }
            }
            res = Math.max(res, end - first + 1);
            end ++;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring(" "));
    }
}



