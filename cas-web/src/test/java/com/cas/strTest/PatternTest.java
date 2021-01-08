package com.cas.strTest;

import org.junit.Test;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 13:55 2020-05-06
 * @version: V1.0
 * @review: 正则表达式的测试用例
 * 博客：https://blog.csdn.net/jiaobuchong/article/details/81257570
 */
public class PatternTest {


    /**
     * 正则表达式，标准用法
     */
    @Test
    public void test1() {
        String telStr = "010-12345678";
        String regex = "0\\d{2}-?\\d{8}|0\\d{3}-?\\d{7}";
        System.out.println(telStr.matches(regex));
    }

    /**
     * 正则表达式高级用法：分组替换
     * 组是用括号划分的正则表达式，可以根据组的编号来引用某个组。组号为 0 表示整个表达式，组号 1 表示第一对括号扩起的组，以此类推。
     * 比如 A(B(C))D 有三个组：组 0 是 ABCD，组 1 是 BC，组 2 是 C，
     * 可以根据有多少个左括号来来确定有多少个分组，括号里的表达式都称子表达式
     * Matcher 对象提供很多方法：
     *
     * goupCount() 返回该正则表达式模式中的分组数目，对应于「左括号」的数目
     * group(int i) 返回对应组的匹配字符，没有匹配到则返回 null
     * start(int group) 返回对应组的匹配字符的起始索引
     * end(int group) 返回对应组的匹配字符的最后一个字符索引加一的值
     */
    @Test
    public void test2() {
        String regex = "(\\d{3})-(\\d{3})-(\\d{3})";
        Pattern pattern = Pattern.compile(regex);
        String input = "123-456-789";
        Matcher matcher = pattern.matcher(input);
        while(matcher.find()) {
            System.out.println(matcher.group(0));
            System.out.println(matcher.group(1) + "---" + matcher.group(2) + "---" + matcher.group(3));
        }
    }

    /**
     * 字符串分组替换，正则表达式高级应用
     */
    @Test
    public void test3() {
        String one = "hello girl hi hot".replaceFirst("(\\w+)\\s+(\\w+)", "$2 $1"); // 只替换首次匹配的数据
        String two = "hello girl hi hot".replaceAll("(\\w+)\\s+(\\w+)", "$2 $1"); // 替换所有匹配的数据
        System.out.println(one);
        System.out.println(two);
    }

    /**
     * 正则表达式：重复标点符号替换,高级用法
     */
    @Test
    public void test4() {
        String input = "我喜欢你，，，但是我不知道，，，，爱情到底有没有价值！！！！我想说：：：……";
        // 重复标点符号替换
        String regex = "([，？！?!,:：]|\\.\\.\\.|……)+";
        System.out.println(input.replaceAll(regex, "$1"));
    }

    /**
     * 正则ip排序
     * 结果：
     * 2.2.2.2
     * 8.109.90.30
     * 10.10.10.10
     * 102.49.23.13
     * 192.68.1.254
     */
    @Test
    public void test5() {
        String ip = "192.68.1.254 102.49.23.013 10.10.10.10 2.2.2.2 8.109.90.30";
        // 先进行填充
        ip = ip.replaceAll("(\\d+)", "00$1");
        System.out.println(ip);
        System.out.println("\t");
        // 再按格式去掉0
        ip = ip.replaceAll("0*(\\d{3})", "$1");
        System.out.println(ip);
        String[] strings = ip.split(" ");
        Arrays.sort(strings);
        Arrays.asList(strings).forEach(System.out::println);
        System.out.println("\t");
        // 将添加的0去掉
        Arrays.asList(strings).forEach(a -> System.out.println(a.replaceAll("0*(\\d+)", "$1")));
    }

    /**
     * 替换重复出现的两位数之间的内容，服，不服不行
     */
    @Test
    public void test6() {
        System.out.println("xx12abbdd12356".replaceAll("(\\d{2}).+?\\1", ""));//xx356
    }

    /**
     * 正则表达式：贪婪模式和非贪婪模式
     */
    @Test
    public void test7() {
        String str = "[][][][][]";
        String greedRegex = ".+\\[";
        Pattern pattern = Pattern.compile(greedRegex);
        Matcher matcher = pattern.matcher(str);
        // 贪婪模式
        while(matcher.find()) {
            System.out.println(matcher.group());
        }

        // 非贪婪模式
        String noGreedRegex = "(.+?\\[)";
        Pattern compile = Pattern.compile(noGreedRegex);
        Matcher matcherNoGreed = compile.matcher(str);
        while(matcherNoGreed.find()) {
            System.out.println(matcherNoGreed.group(1));
        }
    }




}
