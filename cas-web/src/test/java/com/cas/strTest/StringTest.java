package com.cas.strTest;

import com.alibaba.fastjson.JSONObject;
import com.cas.domain.sms.SmsBatch;
import com.cas.domain.sms.SmsBatchDetail;
import com.cas.pojo.User;
import com.cas.utils.StringUtil;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
public class StringTest{

    private static final String url = "/Users/xianglong/Desktop/aa.xlsx";


    public static void main(String[] args) {
        String name = "img/a.png";
        String s = FilenameUtils.getName(name);
        System.out.println(s);
        try {
            throwException();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e);
        }
    }

    /**
     * 获取文件的路径
     */
    @Test
    public void test() {
        File file = new File(url);
        //获取文件的绝对路径
        System.out.println(file.getAbsolutePath());
        //获取文件的相对路径
        System.out.println(file.getPath());
    }

    /**
     * if - else if
     */
    @Test
    public void test2() {
        int a = 5;
        if (a < 9) {
            System.out.println("9");
        } else if (a < 8) {
            System.out.println("8");
        } else {
            System.out.println("else");
        }
    }

    /**
     * 抛出异常
     */
    private static void throwException() {
        Object o = null;

        try {
            o.getClass();
        } catch (Exception e) {
            log.info("122", e);
            log.info(String.valueOf(e));
        }
    }

    /**
     * indexOf 用''效率更高， ""效率低下
     */
    @Test
    public void test3() {
        String str = "a.bcdefg";
        System.out.println(str.indexOf('.'));
    }

    /**
     * Map 迭代
     */
    @Test
    public void test4() {
        Map<String, String> params = new HashMap<String, String>(){{
            put("a_key", "a_value");
            put("b_key", "b_value");
            put("c_key", "c_value");
        }};
        Set<Map.Entry<String, String>> entries = params.entrySet();
        for (Map.Entry<String, String> map : entries) {
            System.out.println(map.getKey());
        }
        System.out.println(entries);

        Set<String> strings = params.keySet();
        System.out.println(strings);

    }

    /**
     * MAP
     */
    @Test
    public void test5() {
        Map<String, Object> map = new HashMap<>();
        User user = new User();
        user.setAge(1);
        map.put("user", user);
        System.out.println(map);
    }


    /**
     * json [] 转换成map
     */
    @Test
    public void test6() {
        String str = "[{\"name\":\"xl\"},{\"name\":\"tt\"}]";
        List<String> list = JSONObject.parseArray(str, String.class);
        System.out.println(list);

    }


    /**
     * json 定向获取值
     */
    @Test
    public void test7() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "xianglong");
        map.put("age", "23");
        String json = StringUtil.getGson().toJson(map);

        //第一种 根据json字符串转换成jsonObject
        JsonObject asJsonObject = new JsonParser().parse(json).getAsJsonObject();
        System.out.println(asJsonObject.get("name").getAsString());

        //第二种
        JSONObject jsonObject = JSONObject.parseObject(json);
        System.out.println(jsonObject.get("name"));
    }

    /**
     * Double 自动转换  除法要是有浮点数就会自动将int转换成浮点数
     */
    @Test
    public void test8() {
        double x = 2.0;
        int y = 4;
        int z = 5;
//        x/=++y;
        System.out.println(x / y);
        System.out.println(y / z);
    }

    /**
     * 查看String 的内存地址
     */
    @Test
    public void test9() {
        String name = "aa";
        System.out.println(name == "aa");
    }

    /**
     * 查看int 和 Integer
     */
    @Test
    public void test10() {
        int i = 0;
        Integer j = new Integer(0);
        System.out.println(i == j);
        System.out.println(j.equals(i));
    }

    /**
     * 二进制运算符～
     */
    @Test
    public void test11() {
        System.out.println(~2);
        System.out.println(~3);
        System.out.println(~4);
    }

    /**
     * str 行参 和 入参
     */
    @Test
    public void test12() {
        String str = "hello";
        System.out.println(str);
    }


    /**
     * 判断字符串是不是以836开头
     */
    @Test
    public void test13() {
        String mno = "83600000000";
        System.out.println(mno.startsWith("8"));
        System.out.println(mno.startsWith("83"));
        System.out.println(mno.startsWith("836"));
        System.out.println(mno.startsWith("8360"));
        System.out.println(mno.startsWith("8361"));
    }

    /**
     * 数组转换
     */
    @Test
    public void test14() {
        int a[][] = {{1, 2}, {3, 4}, {5, 6}};
        System.out.println(a[0][0]);
        System.out.println(a[0][1]);
        System.out.println(a[1][0]);
        System.out.println(a[1][1]);
        System.out.println(a[2][0]);
        System.out.println(a[2][1]);
    }

    /**
     * String的长度是否包含空格
     */
    @Test
    public void test15() {
        String en = "my name";
        System.out.println(en.length());
    }

    /**
     * 截取字符串,截取不能超过字符串长度上限
     */
    @Test
    public void test16() {
        String val = "abcdefg";
        System.out.println(val.substring(0, 6));
    }

    /**
     * int 和 String 相加
     * 结论： int 会被转换成 string
     */
    @Test
    public void test17() {
        String a = "7";
        int b = 2;
        int c = 3;
        System.out.println(b + a + c);
        // 遇到字符串会将前面的公式字符串化，括号里面的不会被字符串化(里面没有字符串提前)
        System.out.println(b + c + a + (b + b));
    }

    /**
     * s1 指向 常量池
     * s2 指向 对象，对象里面有常量池
     */
    @Test
    public void test18() {
        String s1 = "HelloWorld";//redundant
        String s2 = new String("HelloWorld");
        System.out.println(s1 == s2);// Reports any use of == or != to strTest for String equality, instead of an equals() call;
        System.out.println(s1.equals(s2));
    }

    /**
     * 替换字符串中英文括号
     */
    @Test
    public void test19() {
        String cprRegNmCn = "中国蝗虫技术科技（有限公司）(北京分公司)";
        String upRegNm = cprRegNmCn.replace("(", "").replace(")", "").replace("（", "").replace("）", "");
        System.out.println(upRegNm);
    }

    /**
     * 测试StringBuilder 的" "是否有效,结论：空格会被拼接到StringBuilder中
     */
    @Test
    public void test20() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("asd").append(" ").append("dsa");
        System.out.println(stringBuilder);
    }

    /**
     * 手机号掩码 + 怎么写一个通用的程序运行时间检测？
     */
    @Test
    public void test21() throws InterruptedException {
        String phoneCard = "15811317734";
        //程序运行时间开始，精确到毫秒级
        long startTime = Instant.now().toEpochMilli();
        //线程休眠200毫秒
        Thread.sleep(200);
        System.out.println(phoneCard.substring(0, 3) + "****" + phoneCard.substring(7, 11));
        long endTime = Instant.now().toEpochMilli();
        System.out.println("开始时间：" + startTime + "\n结束时间：" + endTime + "\n程序执行时间：" + (endTime - startTime));
    }

    /**
     * StringBuilder 替换指定位置的参数
     * 结论：字符串从0开始算起，包前不包后替换
     */
    @Test
    public void test22() {
        String str = "15811317734";
        StringBuilder stringBuilder = new StringBuilder("15811317734");
        System.out.println(stringBuilder.replace(3, 7, "****"));
        System.out.println(new StringBuilder(str).replace(3, 7, "****"));
    }

    /**
     * 字符串 转 时间格式
     */
    @Test
    public void test23() throws Exception {
        String str = "20200203121212";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        System.out.println(dateFormat.parse(dateFormat.format(format.parse(str))));
    }

    /**
     * List 转 json
     */
    @Test
    public void test24() {
        AdsAssignRuleItem assignRuleItem0 = new AdsAssignRuleItem("11");
        AdsAssignRuleItem assignRuleItem1 = new AdsAssignRuleItem("11");
        List<AdsAssignRuleItem> list = new ArrayList<>();
        AdsAssignRuleItem assignRuleItem2 = new AdsAssignRuleItem("12");
        AdsAssignRuleItem assignRuleItem3 = new AdsAssignRuleItem("13");
        AdsAssignRuleItem assignRuleItem4 = new AdsAssignRuleItem("14");
        list.add(assignRuleItem2);
        list.add(assignRuleItem3);
        list.add(assignRuleItem4);

        List<AdsAssignRuleItem> list2 = new ArrayList<>();
        list2.add(assignRuleItem1);
        list2.add(assignRuleItem3);
        list2.add(assignRuleItem4);
        assignRuleItem0.setAssignRuleItems(list);
        assignRuleItem1.setAssignRuleItems(list2);

        System.out.println(StringUtil.getGson().toJson(assignRuleItem0));

    }


    /**
     * 模拟父子集
     */
    @Data
    private class AdsAssignRuleItem {
        // 值
        private String value;
        // 值子集
        private List<AdsAssignRuleItem> assignRuleItems;
        AdsAssignRuleItem(String value) {
            this.value = value;
        }
    }

    /**
     * Integer 超过280就不是相等的了
     */
    @Test
    public void test25() {
        Integer a = 390;
        Integer b = 390;
        System.out.println(a==b);
        // 拆箱之后比较
        int ai = a;
        int bi = b;
        System.out.println(ai == bi);
    }

    /**
     * 如果在set里面放相同的对象，会去重吗
     * 结论：内容完全相同就会去重，不完全相同则不会
     */
    @Test
    public void test26() {
        Set<User> userSet = new HashSet<>();
        userSet.add(new User("1", "xl"));
        userSet.add(new User("1", "xl2RR"));
        System.out.println(userSet);
    }

    /**
     * java8 先排序再去重
     * 结论:可按照排序规则进行去重
     */
    @Test
    public void test27() {
        List<User> list = new ArrayList<>();
        list.add(new User("2", "xl3"));
        list.add(new User("1", "xl2")); //去重
        list.add(new User("3", "xl"));
        list.add(new User("1", "xl")); //保留




        ArrayList<User> collect = list.stream()
                .sorted(Comparator.comparing(User::getName))
                .collect(Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(User::getUid))),
                        ArrayList::new));

        System.out.println(collect);
    }

    /**
     * java8 distinct()去重
     * 结论：完全相同就会去重
     */
    @Test
    public void test28() {
        List<User> list = new ArrayList<>();
        list.add(new User("2", "xl3"));
        list.add(new User("1", "xl")); //去重
        list.add(new User("3", "xl"));
        list.add(new User("1", "xl")); //保留

        List<User> collect = list.stream().distinct().collect(Collectors.toList());
        System.out.println(collect);
    }

    /**
     * String 删除最后一个字符
     */
    @Test
    public void test29() {
        String str = "123,456,789,";
        StringBuilder builder = new StringBuilder(str);
        System.out.println(builder.deleteCharAt(builder.length() - 1).toString());
        // 指定偏移量开始插入字符
        builder.insert(2, "add");
        // 颠倒字符串
        builder.reverse();

    }

    /**
     * 实用
     * List 转 字符串
     * 例如 List -->  a,b,c,d
     */
    @Test
    public void test30() {
        List<String> list = Arrays.asList("a", "b", "c", "d");
        String join = StringUtils.join(list, ",");
        System.out.println(join);
    }

    /**
     * 比较(S+S)和StringBuilder.append()方法效率
     */
    @Test
    public void test31() {
        // S+S
        String a = "1";
        long startTime = System.currentTimeMillis();
        for(int i = 0; i <= 100000; i++) {
            a += "1";
        }
        long endTime = System.currentTimeMillis();
        System.out.println("S+S 运行时间: " + (endTime - startTime));
    }

    /**
     * 比较(S+S)和StringBuilder.append()方法效率
     */
    @Test
    public void test32() {
        StringBuilder a=new StringBuilder("1");
        long startTime=System.currentTimeMillis();
        for(int i=0;i<1000000;i++){
            a.append("1");
        }
        long endTime=System.currentTimeMillis();
        System.out.print(endTime-startTime);
    }

    /**
     * List<String[]> 内部结构
     */
    @Test
    public void test33() {
        List<String[]> list = new ArrayList<>();
        list.add(new String[]{"1","11"});
        list.add(new String[]{"2","22"});
        for(String[] strings : list) {
            System.out.println(strings[0] + " " + strings[1]);
        }
    }

    /**
     * 数组 索引的问题
     * 结论："/home/app/share/3/4/5" 转换成数组 [0] 是 null
     */
    @Test
    public void test34() {
        String str = "/home/app/share/3/4/5";
        System.out.println(str.split("/")[1]);
        System.out.println(str.split("/")[0]);
        System.out.println(str.split("/")[3]);
    }

    /**
     * 测试 下面这种等于 是否可行
     * 结论：不可行
     */
    @Test
    public void test35() {
        if("'1','2'".equals("'1','3'")) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }

    /**
     * 自动装箱
     */
    @Test
    public void test36() {
        int i = 20;
        Integer j = 10;
        System.out.println(i-j);
    }

    /**
     * 省市区匹配 正则
     */
    @Test
    public void test37() {
        String[] strings = StringDateUtil.getStrings();
        for (String cprRegAddr : strings) {
            String regex1 = "((?<province>[^省]+省|.+自治区))(?<city>[^市]+市|.+自治州)(?<dist>[^县]+?县|.+?区|.+?局)?(?<street>.+)";
            Matcher m = Pattern.compile(regex1).matcher(cprRegAddr);
            if (m.matches()) {
                String provinceNm = m.group("province");
                String cityNm = m.group("city");
                String distNm = m.group("dist");
                String street = m.group("street");
                log.info("规则一:省市解析成功,省={}, 市={}, 区={}, 街道={}", provinceNm, cityNm, distNm, street);
                continue;
            }
            String regex2 = "(?<city>(?<province>北京|上海|天津|重庆)市?)(?<dist>[^区]+?区)?(?<street>.+)";
            Matcher m2 = Pattern.compile(regex2).matcher(cprRegAddr);

            if (m2.matches()) {
                String provinceNm = m2.group("province");
                String cityNm = provinceNm.concat("市");
                String distNm = m2.group("dist");
                String street = m2.group("street");
                log.info("规则二:省市解析成功,省={}, 市={}, 区={}, 街道={}", provinceNm, cityNm, distNm, street);
                continue;
            }

            String regex3 = "(?<city>[^市]+市|.+自治州)(?<dist>[^县]+?县|.+?区|.+?局|.+?镇)?(?<street>.+)?";
            Matcher m3 = Pattern.compile(regex3).matcher(cprRegAddr);
            if (m3.matches()) {
                String cityNm = m3.group("city");
                String distNm = m3.group("dist");
                String street = m3.group("street");
                log.info("规则三:省市解析成功, 市={}, 区={}, 街道={}", cityNm, distNm, street);
                continue;
            }
            log.warn("失败匹配数据：{}", cprRegAddr);

        }
    }

    /**
     * 对象Object的9个方法
     */
    @Test
    public void test38() {
        Object obj = new Object();
        User user = new User();
        user.setAge(2);
        System.out.println(user.hashCode());
        System.out.println(user.toString());
        System.out.println(user.getClass().getName() + "@" + Integer.toHexString(user.hashCode()));

    }

    /**
     * 随机产生两位数
     */
    @Test
    public void test39() {
        int ends = new Random().nextInt(99);
        System.out.println(ends);
    }

    /**
     * 遍历json
     */
    @Test
    public void test40() {
        String json = "{\"belieheneOrpName\":\"徐正明\",\"belieheneOrpCrdNo\":\"320111196602194815\",\"belieheneOrpCrdTyp\":\"1\",\"belieheneOrpCrdSttDt\":\"20051111\"," +
                "\"belieheneOrpCrdExpDt\":\"20251111\",\"belieheneOrpCrdFlg\":\"00\"}";
        JSONObject jsonObject = JSONObject.parseObject(json);
        for (Map.Entry<String, Object> entries: jsonObject.entrySet()){
            System.out.println(entries.getKey() + "  " + entries.getValue());
        }
    }


    /**
     * 查看java对象头
     */
    @Test
    public void test41() {
        User user = new User("123", "向龙", 24, new Date());
        System.out.println(user.hashCode());
        System.out.println(ClassLayout.parseInstance(user).toPrintable());
    }

    /**
     * List迭代器，优点[可运用于所有类型的遍历]
     * Arrays.asList() 不支持remove, 抛异常
     */
    @Test
    public void test42() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        Iterator<String> iterator = list.iterator();
        // 很显然迭代器有优点也有缺点，推荐还是加强for循环
        while (iterator.hasNext()) {
            iterator.next();
                iterator.remove();
        }
        System.out.println(list);
    }

    /**
     * 方便查看equals重写
     */
    @Test
    public void test43() {
        String str = "法人身份证12正面";
        System.out.println(str.equals(str));
        Integer a = 1;
        System.out.println(a.equals(1));
        User user = new User();
        System.out.println(user.equals(new User()));
    }

    /**
     * java 中的 Math.round(-1.5) 等于多少？
     */
    @Test
    public void test44() {
        System.out.println(Math.round(-1.6));
    }

    /**
     * 变量替换
     */
    @Test
    public void test45() {
        Map<String, List<String>> contentVariablesMap = null;
        Set<String> contentVariablesMapKeySet = null;

        SmsBatch batch = new SmsBatch();
        batch.setKeys("15811317734,15023437718");
        batch.setContentVariables("{var1:[向龙,xianglong],var2:[100,200]}");
        batch.setContent("您好var1: 欠款 var2元");
        contentVariablesMap = StringUtil.getGson().fromJson(batch.getContentVariables(), new TypeToken<Map<String, List<String>>>() {
        }.getType());


        contentVariablesMapKeySet = contentVariablesMap.keySet();

        String[] keys = batch.getKeys().split(",");
        for (int i = 0; i < keys.length; i++) {
            SmsBatchDetail detail = new SmsBatchDetail(batch.getContent());
            for (String vk : contentVariablesMapKeySet) {
                detail.setContent(detail.getContent().replaceAll(vk, contentVariablesMap.get(vk).get(i)));
            }
            System.out.println(detail.getContent());
        }
    }

    /**
     * treeMap简单方法使用
     * 以下是java.util.TreeMap.lastKey()方法的声明。
     * lastKey() 方法用于返回当前在此映射的最后一个(最高)键。
     */
    @Test
    public void test46() {
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(2, "two");
        treeMap.put(1, "one");
        treeMap.put(5, "five");
        treeMap.put(3, "three");
        treeMap.put(7, "seven");
        treeMap.put(6, "six");
        System.out.println("lastKey():-> " + treeMap.lastKey());
        System.out.println("firstKey():-> " + treeMap.firstKey());
    }

    /**
     * breakPoint condition 断点函数测试
     * 适用场景：对循环测试需要测试某个固定条件时候可用
     */
    @Test
    public void test47() {
        String[] strs = {"1", "2", "3", "4", "5"};
        for (String str : strs) {
            System.out.println(str);
        }
    }

    /**
     * 加载因子越大节省内存但查找效率低，加载因子越小耗内存但查找效率高，系统默认加载因子为0.75，一般情况下我们是无需修改的。
     */
    @Test
    public void test48() {
        Set<String> set = new HashSet<>(100,2);
        System.out.println();
    }

    /**
     * for(;;) 和 while(1) 死循环 跳出方法
     * for(;;) 底层命令更简单
     */
    @Test
    public void test49() throws InterruptedException {
        Integer i = 1;
        for(;;) {
            Thread.sleep(500);
            System.out.println(i++);
            if(i == 5) {
                break;
            }
        }
    }

    /**
     * String 偏移量
     */
    @Test
    public void test50() {
        char[] chars = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
        byte[] bytes = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
        String str = new String(chars, 3, 2);
        str.getChars(0,1, chars, 3);
        System.out.println(str);

        String str2 = new String(bytes, 3, 2, Charset.defaultCharset());
        System.out.println(str2.codePointCount(0,2));
    }

    /**
     * 字符转大小写
     */
    @Test
    public void test51() {
        String str = "ABCdefGhiJK";
        System.out.println(str.toUpperCase());

    }

}
