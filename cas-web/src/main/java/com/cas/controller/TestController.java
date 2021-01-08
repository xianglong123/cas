package com.cas.controller;


import com.cas.domain.ValidatorPojo;
import com.cas.service.syncService.SyncService;
import com.cas.service.inqueryService.InqueryService;
import com.cas.service.pdfService.PdfService;
import com.cas.service.pdfService.PdfView;
import com.cas.service.scheduled.DynamicScheduleTask;
import com.cas.service.uploadService.UploadService;
import com.cas.utils.CookieUtil;
import com.cas.utils.SpringContextUtils;
import com.cas.utils.ThreadPoolUtil;
import com.cas.validator.UserValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Api(value = "API - 测试controller")
@Slf4j
@Controller
@RequestMapping("/test")
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successful — 请求已完成"),
        @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
        @ApiResponse(code = 401, message = "未授权客户机访问数据"),
        @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
        @ApiResponse(code = 500, message = "服务器不能完成请求")}
)
public class TestController {

    @Autowired
    private UploadService uploadService;

    @Autowired
    private InqueryService inqueryService;

    @Autowired
    private PdfService pdfService;

    @Autowired
    private SyncService countDownLatchService;

    /**
     * jsp 跳转测试
     *
     * @return
     */
    @ApiOperation(value = "跳转hello页面")
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    /**
     * Excel 本地导出 case
     */
    @ApiOperation(value = "Excel 本地导出 跳转测试页面")
    @GetMapping("/upload")
    public String upload() {
        return "upload/upload";
    }

    /**
     * Excel 远程下载
     */
    @ApiOperation(value = "Excel 远程下载")
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
    @ApiOperation(value = "上传 Excel 并 通过模版解析")
    @PostMapping(value = "/uploadhttp", headers = "content-type=multipart/form-data")
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
    @ApiOperation(value = "udi 核心代码")
    @PostMapping("/query")
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


    /**
     * 获取当前运行环境dev|strTest|prod
     *
     * @return
     */
    @ApiOperation(value = "获取当前运行环境dev|strTest|prod")
    @PostMapping("/getProfiles")
    @ResponseBody
    public String getProfiles() {
        String[] activeProfiles = SpringContextUtils.applicationContext.getEnvironment().getActiveProfiles();
        System.out.println(Arrays.toString(activeProfiles));
        return activeProfiles[0];
    }

    /**
     * 重新设置ThreadLocal
     */
    @ApiOperation(value = "重新设置ThreadLocal")
    @PostMapping("/setThreadLocal")
    @ResponseBody
    private String setThreadLocal(String time) {
        System.out.println("线程被运行: " + Thread.currentThread().getName());
        DynamicScheduleTask.map.put("time", time);
//        DynamicScheduleTask.threadLocal.set(time);
        return "ok!";
    }

    /**
     * 测试 自定义的类型转换器好不好用
     * com.cas.components.converter.StringToUserConverter
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
    @ApiOperation(value = "请求request所包含参数")
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

    /**
     * 查看HTTP码
     */
    @ApiOperation(value = "查看HTTP码")
    @GetMapping("/queryHttpCode")
    @ResponseBody
    public String queryHttpCode() {
        return
                "1**---信息，服务器收到请求，需要请求者继续执行操作\n\n"
                        + "2** 成功，操作被成功接收并处理\n"
                        + "200===确定，客服端请求成功\n"
                        + "201===已创建\n"
                        + "202===已接受\n"
                        + "203===非权威性信息\n"
                        + "204===无内容\n"
                        + "205===重置内容\n"
                        + "206===部分内容\n\n"

                        + "3**---重定向，需要进一步的操作以完成请求\n"
                        + "3xx===重定向\n"
                        + "302===对象已移动\n"
                        + "304===未修改\n\n"

                        + "4**---客户端错误，请求包含语法错误或无法完成请求\n"
                        + "403===禁止访问\n"
                        + "404===无法找到文件\n"
                        + "405===资源被静止\n"
                        + "406===无法接受\n"
                        + "407===要求代理身份验证\n"
                        + "410===永远不可用\n"
                        + "411===访问被拒绝\n"
                        + "412===先决条件失败\n"
                        + "414===请求uri太长\n\n"

                        + "5**---服务器错误，服务器在处理请求的过程中发生了错误\n"
                        + "500===内部服务器错误\n"
                        + "501===未实现\n"
                        + "502===网关错误\n"

                        + "HTTP码博客地址===https://blog.csdn.net/angelo_gs/article/details/88943169\n";
    }

    @ApiOperation(value = "线程池任务完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "value", value = "以英文,分割数据", required = false,
                    dataType = "string", paramType = "query", defaultValue = "5,6,7,2")
    })
    @GetMapping("/countDown")
    @ResponseBody
    public String countDown(String value) {
        String[] split = value.split(",");
        List<Integer> va = new ArrayList<>();
        Arrays.asList(split).forEach(a -> va.add(Integer.valueOf(a)));
        return countDownLatchService.subNum(va.toArray(new Integer[0]));
    }


    @ApiOperation(value = "线程池任务超时中断")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "value", value = "以英文,分割数据", required = false,
                    dataType = "string", paramType = "query", defaultValue = "5,6,7,2")
    })
    @GetMapping("/poolFutureTask")
    @ResponseBody
    public String poolFutureTask(String value) throws ExecutionException, InterruptedException {
        String[] split = value.split(",");
        List<Integer> va = new ArrayList<>();
        Arrays.asList(split).forEach(a -> va.add(Integer.valueOf(a)));
        return countDownLatchService.process(va.toArray(new Integer[0]));
    }

    @ApiOperation(value = "终止线程池，此线程池已废，只能重启或者重新new")
    @GetMapping("/shutdown")
    @ResponseBody
    public String shutdown() {
        ThreadPoolUtil.shutdown();
        return "线程已shutdown";
    }

}




