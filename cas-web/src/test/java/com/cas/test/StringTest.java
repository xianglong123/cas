package com.cas.test;

import com.alibaba.fastjson.JSONObject;
import com.cas.pojo.User;
import com.cas.utils.StringUtil;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        Map<String, String>  params = new HashMap<>();
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
        double x=2.0;
        int y=4;
        int z=5;
//        x/=++y;
        System.out.println(x/y);
        System.out.println(y/z);
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
        System.out.println(i==j);
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

    private void changeStr(String str) {
        str = "world";
    }


}
