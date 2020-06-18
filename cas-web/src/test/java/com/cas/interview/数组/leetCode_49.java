package com.cas.interview.数组;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 10:51 2020-06-15
 * @version: V1.0
 * @review:
 */
public class leetCode_49 {

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List> need = new HashMap<>();

        for (String i : strs) {
            // 处理的数据
            char[] c = i.toCharArray();
            Arrays.sort(c);
            String s = Arrays.toString(c);
            if(!need.containsKey(s)) {
                need.put(s, new ArrayList());
            }
                need.get(s).add(i);
        }
        return new ArrayList(need.values());
    }


    public static void main(String[] args) {
        String[] strings = {"eat", "ate"};
        groupAnagrams(strings).forEach(System.out::println);
    }


}
