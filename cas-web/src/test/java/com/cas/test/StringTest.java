package com.cas.test;

import com.alibaba.fastjson.JSONObject;
import com.cas.pojo.User;
import com.cas.utils.StringUtil;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.junit.Test;
import org.springframework.web.servlet.HandlerMapping;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.SimpleFormatter;

@Slf4j
public class StringTest {

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
        Map<String, String> params = new HashMap<>();
        params.put("a_key", "a_value");
        params.put("b_key", "b_value");
        params.put("c_key", "c_value");

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
        System.out.println(s1 == s2);// Reports any use of == or != to test for String equality, instead of an equals() call;
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
        System.out.println("开始时间：" + startTime + "\n结束时间：" + endTime +"\n程序执行时间：" + (endTime - startTime));
    }

    /**
     * StringBuilder 替换指定位置的参数
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
    public void test23() throws Exception{
        String str = "20200203121212";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        System.out.println(dateFormat.parse(dateFormat.format(format.parse(str))));
    }


}
