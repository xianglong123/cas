package com.cas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 20:45 2020-07-29
 * @version: V1.0
 * @review:
 */
//@Controller
@RestController
@RequestMapping("/www")
public class wwwController {

    @GetMapping("getName")
    public String getName(Integer age) {
        System.out.println(" age = " + age);
        return "ok";
    }

    /**
     * 明天讲：get 获取地址中的参数
     * @param name
     * @param age
     * @return
     */
    @GetMapping("getName/{age}")
    public String getName2(String name, Integer age) {
        System.out.println("name= " + name + " age = " + age);
        return "ok";
    }

    // 作业：
    // 1，写一个get方法，传入一个name,打印出来
    // 打印方法 System.out.println(" age = " + age);

    /**
     * 要是返回值是map
     * @return
     */
    @GetMapping("getMap")
    public Map<String, String> getMap() {
        // 1. 我们创建一个map对象
        // 基本数据类型：int float double boolean ...
        Map<String, String> map = new HashMap<>();
        // 2. 使用这个对象，赋值 put:放值的方法
        map.put("name", "zz");
        // 3. 返回前端
        return map;
    }

    //  ################# 下面是post方法 ##############

    /**
     * post 请求
     * @param age
     * @return
     */
    @PostMapping("getName2")
    public String getName2(Integer age) {
        System.out.println(" age = " + age);
        return age + "";
    }

}
