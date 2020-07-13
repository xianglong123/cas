package com.cas.owner.service.scheduled;

import com.cas.owner.service.accountService.AccountService;
import com.cas.owner.service.grippeService.GrippeService;
import com.cas.owner.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: xianglong
 * @date: 2019/10/8 16:07
 * @version: V1.0
 * @review: xiang_long
 * 动态定时任务，可以把定时任务策略从数据库中获取
 */
@Component
@Slf4j
@Configuration      //1.主要用于标记配置类，兼备Component的效果。这里没有@Configuration是没有问题的
//@EnableScheduling   // 2.开启定时任务
public class DynamicScheduleTask implements SchedulingConfigurer{

    private static final int _1MB = 1024 * 1024;

    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static Map<String, String> map = new HashMap<>();

    static {
        map.put("time", "*/10 * * * * ?");
        threadLocal.set("threadLocal is running!!!");
    }

    @Autowired
    private GrippeService grippeService;

    @Autowired
    private AccountService accountService;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar){

        taskRegistrar.addTriggerTask(
                //1.添加任务内容(Runnable)
//                () -> grippeService.collectDate(),
                () -> accountService.pushAutoId(),
                //2.设置执行周期(Trigger)
                triggerContext -> {
                    //2.1 从数据库获取执行周期
                    String cron = getCron();
                    //2.2 合法性校验.
                    if (StringUtils.isBlank(cron)) {
                        // Omitted Code ..
                    }
                    //2.3 返回执行周期(Date)
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                }
        );
    }

    //每隔一小时
    private String getCron(){
//        byte[] allocation = new byte[4 * _1MB];
        System.out.println("线程号: " + Thread.currentThread().getName() + "  时间计划: " + map.get("time"));
        System.out.println("ThreadLocal 线程号: " + Thread.currentThread().getName() + "   ThreadLocal: " + threadLocal.get());
        if(StringUtil.isEmpty(map.get("time")))
            return "0/3 * * * * ?";
        else
            return map.get("time");
    }

}
