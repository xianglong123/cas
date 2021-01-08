package com.cas.components.beanAware;

import com.cas.configs.AppConfig;
import com.cas.components.beanAware.person.BussinessPerson;
import com.cas.components.beanAware.person.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 15:46 2020-01-20
 * @version: V1.0
 * @review:
 */
@Slf4j
public class ServiceTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        Person person = ctx.getBean(BussinessPerson.class);
        person.service();
        ctx.close();
    }


}
