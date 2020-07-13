package com.cas.owner.algorithm.字符;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 13:52 2020-06-23
 * @version: V1.0
 * @review:
 *
 * 我叫王大锤，是一家出版社的编辑。我负责校对投稿来的英文稿件，这份工作非常烦人，因为每天都要去修正无数的拼写错误。但是，优秀的人总能在平凡的工作中发现真理。我发现一个发现拼写错误的捷径：
 *
 * 1. 三个同样的字母连在一起，一定是拼写错误，去掉一个的就好啦：比如 helllo -> hello
 * 2. 两对一样的字母（AABB型）连在一起，一定是拼写错误，去掉第二对的一个字母就好啦：比如 helloo -> hello
 * 3. 上面的规则优先“从左到右”匹配，即如果是AABBCC，虽然AABB和BBCC都是错误拼写，应该优先考虑修复AABB，结果为AABCC
 */
public class 字节跳动_2019_万万没想到之聪明的编辑 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 收集原始数据
        List<String> list = new ArrayList<>();
        int index = in.nextInt();
        while(in.hasNext()) {
            list.add(in.next());
            if (--index == 0) {
                break;
            }
        }
        // 数据处理
        list.forEach(str -> correctStringTwo(correctString(str)));
    }

    /**
     * 矫正数据 第一轮矫正
     * @param str
     * @return
     */
    public static String correctString(String str) {
        // 1, 三个字母相连去掉最后一个
        char[] chars = str.toCharArray();
        // 矫正数据
        StringBuilder stringBuilder = new StringBuilder();
        // 记录不合要求数据
        Cor cor = new Cor();
        for (Character c : chars) {
            if (c.equals(cor.getC())) {
                if (cor.getNum() < 2) {
                    cor.setNum(cor.getNum() + 1);
                    stringBuilder.append(c);
                }
            } else {
                stringBuilder.append(c);
                // 重置cor对象
                cor.reset(c);
            }
        }
        return stringBuilder.toString();
    }

    public static void  correctStringTwo(String str) {
        // 成对出现进行处理
        char[] chars = str.toCharArray();
        // 矫正之后的数据
        StringBuilder stringBuilder = new StringBuilder();
        // 记录不合要求数据
        Cor cor = new Cor();
        for (int i = 0; i <= chars.length - 1; i ++) {
            if (i < chars.length - 1 && chars[i] == chars[i + 1]) {
                if (cor.getNum() == 2) {
                    stringBuilder.append(chars[i]);
                    cor.reset(chars[i]);
                } else {
                    stringBuilder.append(chars[i]);
                    stringBuilder.append(chars[i]);
                    cor.initCor(chars[i]);
                }
                i++;
            } else {
                stringBuilder.append(chars[i]);
                cor.reset(chars[i]);
            }

        }
        System.out.println(stringBuilder.toString());
    }

    public static class Cor {
        private Character c;
        private int num;

        public void initCor(Character c) {
            this.c = c;
            this.num = 2;
        }

        public void reset(Character c) {
            this.c = c;
            this.num = 1;
        }

        public Character getC() {
            return c;
        }

        public void setC(Character c) {
            this.c = c;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }

}
