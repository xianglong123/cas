package com.cas.controller;


import com.cas.domain.ValidatorPojo;
import com.cas.pojo.AccountPo;
import com.cas.pojo.ExcelModel;
import com.cas.pojo.QueAnsPo;
import com.cas.pojo.User;
import com.cas.service.accountService.AccountService;
import com.cas.service.inqueryService.InqueryService;
import com.cas.service.pdfService.PdfService;
import com.cas.service.pdfService.PdfView;
import com.cas.service.questionService.QuestionService;
import com.cas.service.scheduled.DynamicScheduleTask;
import com.cas.service.testService.HelloService;
import com.cas.service.testService.HelloServiceImpl;
import com.cas.service.testService.TestService;
import com.cas.service.uploadService.UploadService;
import com.cas.utils.CookieUtil;
import com.cas.utils.SpringContextUtils;
import com.cas.utils.StringUtil;
import com.cas.validator.UserValidator;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
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

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private PdfService pdfService;

    @Autowired
    private QuestionService questionService;

    /**
     * c测试系统是否可用
     *
     * @return
     */
    @PostMapping("/test")
    public String testApplication() {
        return testService.queryCount() + "";
    }

    /**
     * 获取Account 对象通过userId
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/queryAccount")
    @ResponseBody
    public String queryAccount(String userId) {
        Gson gson = StringUtil.getGson();
        System.out.println(accountService.queryAccount(userId));
        return gson.toJson(accountService.queryAccount(userId));
    }

    /**
     * jsp 跳转测试
     *
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
     *
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
     *
     * @return
     */
    @RequestMapping("/getProfiles")
    @ResponseBody
    public String getProfiles() {
        String[] activeProfiles = SpringContextUtils.applicationContext.getEnvironment().getActiveProfiles();
        System.out.println(Arrays.toString(activeProfiles));
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
     * 测试事务的隔离级别 结论：单个sql本身就是事务，不需要测试，单一sql执行具有ACID
     */
    @RequestMapping("/addAccount")
    @ResponseBody
    public String addAccount() {
        int add = accountService.add();
        return add + "";
    }

    /**
     * 测试redis
     */
    @RequestMapping("/redis")
    @ResponseBody
    public String redis(String num) {
        redisTemplate.opsForValue().set("key" + num, "value" + num);
        return "ok";
    }

    /**
     * 测试redis事务，结合断点测试
     * redis事务和关系型数据库事务不一样，对于出错的命令redis只是报出错误，而错误后面的命令依旧被特别注意的地方。所以在执行redis事务前，严格地检测数据，以避免这样的事情发生
     *
     * @param num
     * @return
     */
    @RequestMapping("/multi")
    @ResponseBody
    public String multi(String num) {
        redisTemplate.opsForValue().set("key1", "value" + num);
        // 开启事务要手动设置下面这个开启事务
        redisTemplate.setEnableTransactionSupport(true);
        List list = (List) new SessionCallback() {
            @Override
            public Object execute(RedisOperations ro) throws DataAccessException {
                // 设置要监控key
                ro.watch("key1");
                // 开启事务，在exec命令执行前，全部都只是进入队列
                ro.multi();
                ro.opsForValue().set("key2", "value2");
//                ro.opsForValue().increment("key" + num, 1); // 1
                // 获取值将为null,因为redis只是把命令放入队列
                Object value2 = ro.opsForValue().get("key2");
                System.out.println("命令在队列，所有 value 为null 【" + value2 + "】");
                ro.opsForValue().set("key3", "value3");
                Object value3 = ro.opsForValue().get("key3");
                System.out.println("命令在队列，所有 value 为null 【" + value3 + "】");
                // 执行 exec 命令，将先判别 key1 是否在监控后被修改过，如果是则不执行事务，否则就执行事务
                return ro.exec();
            }
        }.execute(redisTemplate);
        System.out.println(list);
        return "ok";
    }


    /**
     * 测试redis使用流水线功能
     * 10万数据读写378毫秒，一秒奖近30万数据读写
     * 与事务一样，使用流水线的过程中，所有的命令也只是进入队列而没有执行，所以所有的命令返回值都为空
     *
     * @return
     */
    @RequestMapping("/pipeline")
    @ResponseBody
    public String pipeline() {
        Long start = Instant.now().toEpochMilli();
        redisTemplate.executePipelined(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                for (int i = 1; i <= 100000; i++) {
                    operations.opsForValue().set("pipeline_" + i, "value_" + i);
                    String value = (String) operations.opsForValue().get("pipeline_" + i);
                    if (i == 100000) {
                        System.out.println("命令只是进入队列， 所有值为空【" + value + "】");
                    }
                }
                return null;
            }
        });
        Long end = Instant.now().toEpochMilli();
        System.out.println("耗时：" + (end - start) + "毫秒。");
        return "ok";
    }

    /**
     * 测试 Redis 消息发送
     */
    @RequestMapping("/publish")
    @ResponseBody
    public String publish() {
        redisTemplate.convertAndSend("topicl", "发布订阅测试信息");
        return "ok";
    }

    /**
     * 测试简单的Lua脚本
     */
    @RequestMapping("/lua")
    @ResponseBody
    public String lua() {
        DefaultRedisScript<String> rs = new DefaultRedisScript<>();
        // 设置脚本
        rs.setScriptText("return 'Hello Redis'");
        // 定义返回类型。 注意：如果没有这个定义，Spring不会返回结果
        rs.setResultType(String.class);
        RedisSerializer<String> stringRedisSerializer = redisTemplate.getStringSerializer();
        // 执行Lua脚本
        String str = (String) redisTemplate.execute(rs, stringRedisSerializer, stringRedisSerializer, null);
        return str;
    }

    /**
     * 测试 自定义的类型转换器好不好用
     * com.cas.components.StringToUserConverter
     * 测试请求地址：http://127.0.0.1:8081/test/converter?user=xl-pass123-23
     *
     * @param user
     * @return
     */
    @GetMapping("/converter")
    @ResponseBody
    public com.cas.domain.User converter(com.cas.domain.User user) {
        return user;
    }

    /**
     * 测试 JSR-303 验证
     */
    @PostMapping("/valid")
    @ResponseBody
    public Map<String, Object> valid(@Valid ValidatorPojo validatorPojo, Errors errors) {
        Map<String, Object> errMap = new HashMap<>();
        // 获取错误列表
        List<ObjectError> oes = errors.getAllErrors();
        for (ObjectError oe : oes) {
            if (oe instanceof FieldError) {
                FieldError fe = (FieldError) oe;
                errMap.put(fe.getField(), oe.getDefaultMessage());
            } else {
                // 非字段错误
                errMap.put(oe.getObjectName(), oe.getDefaultMessage());
            }
        }
        return errMap;
    }

    /**
     * 调用控制器前先执行这个方法
     *
     * @param binder
     */
//    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // 绑定验证器
        binder.setValidator(new UserValidator());
        // 定义日期参数格式，参数不再需注解@DateTimeFormat, boolean参表示是否允许为空
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), false));
    }

    /**
     * 测试自定义验证器
     * <p>
     * 请求：http://127.0.0.1:8081/test/validUser?user=xl-pass123-23&date=2020-02-21
     * 返回：{"date":1582214400000,"hight":"身高不能为空","user":{"username":"xl","password":"pass123","age":23,"sex":false,"initTime":null,"hight":null}}
     *
     * @param user
     * @param errors
     * @param date
     * @return 结论：自定义验证器生效，记得一定先@initBinder 绑定验证器
     */
    @GetMapping("/validUser")
    @ResponseBody
    public Map<String, Object> validUser(@Valid com.cas.domain.User user, Errors errors, Date date) {
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        map.put("date", date);
        // 获取错误列表
        List<ObjectError> oes = errors.getAllErrors();
        for (ObjectError oe : oes) {
            if (oe instanceof FieldError) {
                FieldError fe = (FieldError) oe;
                map.put(fe.getField(), fe.getDefaultMessage());
            } else {
                // 非字段错误
                map.put(oe.getObjectName(), oe.getDefaultMessage());
            }
        }
        return map;
    }

    /**
     * 测试 pdf 文件
     *
     * @return
     */
    @GetMapping("/export/pdf")
    public ModelAndView exportPdf() {
        log.info("pdf 查询开始......");
        // 模拟用户信息列表
        List<com.cas.domain.User> userList = Arrays.asList(new com.cas.domain.User("xl", "123456"), new com.cas.domain.User("xl2", "123456"));
        // 定义PDF 视图
        log.info("pdf 视图构建开始......");
        View view = new PdfView(pdfService.exportService());
        log.info("pdf 视图构建结束......");
        ModelAndView mv = new ModelAndView();
        // 设置视图
        mv.setView(view);
        mv.addObject("userList", userList);
        log.info("pdf 查询结束......");
        return mv;
    }

    /**
     * 添加 问题 至 que_ans表
     *
     * @param queAnsPo
     * @return
     */
    @PostMapping("/addQuestion")
    public String addQuestion(QueAnsPo queAnsPo) {
        try {
            questionService.add(queAnsPo);
        } catch (Exception e) {
            log.error("数据添加失败", e);
        }
        log.info("问题添加成功：" + queAnsPo.getQuestion());
        return "redirect:questionView";
    }

    /**
     * 跳转 至 添加问题页面
     */
    @GetMapping("/questionView")
    public String addQuestionView() {
        return "question/questionView";
    }

    /**
     * 设置cookie 结论：可以设置cookie
     */
    @GetMapping("/setCookie")
    @ResponseBody
    public String setCookie(HttpServletResponse response, String key, String value) {
        CookieUtil.set(response, key, value, true);
        return "ok";
    }

    /**
     * 获取cookie 结论：可以获取cookie
     */
    @GetMapping("/getCookie")
    @ResponseBody
    public String setCookie(HttpServletRequest request, String key) {
        request.getSession();
        return CookieUtil.getValue(request, key);
    }

    /**
     * 断开 Session 也就是断开浏览器和服务器的响应   结论：无法断开服务器与浏览器的连接
     */
    @GetMapping("/delSeesion")
    @ResponseBody
    public String delSeesion(HttpServletRequest request) {
        // 注销所有Session
        request.getSession().invalidate();
        return "ok";
    }

    /**
     * HttpServletRequest 包含前端传过来的一切，看看都有啥
     */
    @GetMapping("/requestInfo")
    @ResponseBody
    public String requestInfo(HttpServletRequest request) throws IOException {
        // 传说流只能取一次，我看是不是真的？
        log.warn("1  " + request.getInputStream().toString());
        log.warn("2  " + request.getInputStream().toString());
        log.info("getRequestURL():{}", request.getRequestURL().toString());
        log.info("getAuthType():{}", request.getAuthType());
        log.info("getRemoteHost():{}", request.getRemoteHost());
        log.info("getLocalPort():{}", request.getLocalPort());
        log.info("1:{}", request.getRemotePort());
        log.info("2:{}", request.getServerPort());
        log.info("3:{}", request.getContextPath());

        log.info("4:{}", request.getHeader("content-typeHandler"));
        log.info("5:{}", request.getMethod());
        log.info("6:{}", request.getPathInfo());

        log.info("7:{}", request.getPathTranslated());
        log.info("8:{}", request.getQueryString());
        log.info("9:{}", request.getRequestedSessionId());

        log.info("a:{}", request.getRequestURI());
        log.info("b:{}", request.getProtocol());
        log.info("c:{}", request.getLocalAddr());

        log.info("d:{}", request.getLocalName());
        log.info("e:{}", request.getServerName());
        return "ok";
    }




}
