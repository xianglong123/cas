package com.cas.controller;


import com.cas.pojo.AccountPo;
import com.cas.pojo.ExcelModel;
import com.cas.pojo.User;
import com.cas.service.accountService.AccountService;
import com.cas.service.inqueryService.InqueryService;
import com.cas.service.scheduled.DynamicScheduleTask;
import com.cas.service.testService.HelloService;
import com.cas.service.testService.HelloServiceImpl;
import com.cas.service.testService.TestService;
import com.cas.service.uploadService.UploadService;
import com.cas.utils.SpringContextUtils;
import com.cas.utils.StringUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private UploadService uploadService;

    @Autowired
    private InqueryService inqueryService;

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
     * Excel 本地导出 case
     */
    @GetMapping("/upload")
    public String upload() {
        return "upload/upload";
    }

    /**
     * Excel 远程下载
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response) {
        try {
            uploadService.exportSysSystemExcel(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传 Excel 并 通过模版解析
     */
    @RequestMapping("/uploadhttp")
    @ResponseBody
    public String uploadhttp(HttpServletRequest request) {
        try {
            uploadService.readExcelhttp(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok";
    }

    /**
     * udi 核心代码
     * @return
     */
    @RequestMapping("/query")
    @ResponseBody
    public String query() {
        StringBuilder sb = new StringBuilder();
        String str = "select id, user_id, freeze_Amount, balance, create_time, update_time from tcc_account.account where user_id = #{userId}";
        sb.append("<script>").append(str).append("</script>");
        Map<String, String> parameter = new HashMap<>();
        parameter.put("userId", "10000");
        String id = "queryDate2";
        List<LinkedHashMap<String, Object>> inquery = inqueryService.inquery(id, str, parameter);
        System.out.println(inquery);
        return inquery.toString();
    }


    @RequestMapping("/addObject")
    @ResponseBody
    public String addObj() {
        for (int i = 0; i <= 10; i++) {
            Map<String, String> map = new HashMap<>();
            AccountPo accountPo = new AccountPo();
            User user = new User();
            ExcelModel excelModel = new ExcelModel();
        }
        return "ok";
    }

    /**
     * 获取当前运行环境dev|test|prod
     * @return
     */
    @RequestMapping("/getProfiles")
    @ResponseBody
    public String getProfiles() {
        String[] activeProfiles = SpringContextUtils.applicationContext.getEnvironment().getActiveProfiles();
        System.out.println(activeProfiles);
        return activeProfiles[0];
    }

    /**
     * 重新设置ThreadLocal
     */
    @RequestMapping("/setThreadLocal")
    @ResponseBody
    private String setThreadLocal(String time) {
        System.out.println("线程被运行: " + Thread.currentThread().getName());
        DynamicScheduleTask.map.put("time", time);
//        DynamicScheduleTask.threadLocal.set(time);
        return "ok!";
    }

    /**
     * 测试自定义切点MyAspect
     */
    @RequestMapping("/myAspect")
    @ResponseBody
    private String myAspect() {
        HelloService helloService = new HelloServiceImpl();
        helloService.sayHello("xl");
        return "ok";
    }

    /**
     * 测试事务的隔离级别
     */
    @RequestMapping("/addAccount")
    @ResponseBody
    public String addAccount() {
        int add = accountService.add();
        return add + "";
    }




}
