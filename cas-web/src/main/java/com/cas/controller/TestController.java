package com.cas.controller;


import com.cas.service.accountService.AccountService;
import com.cas.service.testService.TestService;
import com.cas.utils.StringUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private AccountService accountService;

    /**
     * c测试系统是否可用
     * @return
     */
    @PostMapping("/test")
    public String testApplication() {
        return testService.queryCount() + "";
    }

    /**
     * 获取Account 对象通过userId
     * @param userId
     * @return
     */
    @RequestMapping(value = "/queryAccount")
    @ResponseBody
    public String queryAccount(String userId) {
        Gson gson =  StringUtil.getGson();
        System.out.println(accountService.queryAccount(userId));
        return gson.toJson(accountService.queryAccount(userId));
    }

    /**
     * jsp 跳转测试
     * @return
     */
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    /**
     * 上传 case
     */
    @GetMapping("/upload")
    public String upload() {
        return "upload/upload";
    }


}