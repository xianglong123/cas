
package com.cas.algorithm.滑动模块;

import java.util.ArrayList;
import java.util.List;

class Solution5 {
    public static List<Integer> findAnagrams(String s, String p) {
        char[] character = p.toCharArray();
        char[] chars = p.toCharArray();
        int charCount = 0;
        int count = p.length();
        List<Integer> resList = new ArrayList<>();
        for (int i = 0; i < chars.length; i ++) {
            charCount += (int)chars[i];
        }
        for(int i = 0; i < p.length(); i ++) {
            count += (int)character[i];
        }

        for (int j = 0; j < character.length - 2; j ++) {
            if (count == charCount) {

            }
        }

        System.out.println((int)character[0]);

        return null;
    }

    public static void main(String[] args) {
        findAnagrams("a", "ab");
    }
}
