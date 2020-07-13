package com.cas.owner.algorithm.华为笔试练习题;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 18:21 2020-06-25
 * @version: V1.0
 * @review:
 */
public class 字符统计 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String str = in.nextLine();
            Map<Character, Integer> map = new HashMap<>();
            for (Character c : str.toCharArray()) {
                map.put(c, map.get(c) == null ? 1 : map.get(c) + 1);
            }

            List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
            Comparator<Map.Entry<Character, Integer>> byValue = Comparator.comparing(Map.Entry<Character, Integer>::getValue).reversed();
            Comparator<Map.Entry<Character, Integer>> byKey = Comparator.comparing(Map.Entry<Character, Integer>::getKey);
            list.sort(byValue.thenComparing(byKey));
            list.forEach(a -> System.out.print(a.getKey()));
        }


    }
}
