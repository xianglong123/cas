package com.cas.components.beanAware.person;

import com.cas.components.beanAware.animal.Animal;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 15:37 2020-01-20
 * @version: V1.0
 * @review:
 */
@Component
public class BussinessPerson implements Person, BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean, DisposableBean {

    private Animal animal = null;

    public BussinessPerson(Animal animal) {
        this.animal = animal;
    }

    @Override
    public void service() {
        this.animal.use();
    }

    @Override
    @Autowired @Qualifier("cat")
    public void setAnimal(Animal animal) {
        System.out.println("延迟依赖注入!!!");
        this.animal = animal;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("【" + this.getClass().getSimpleName() + "】调用 setBeanFactory");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("【" + this.getClass().getSimpleName() + "】调用 setBeanName");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("【" + this.getClass().getSimpleName() + "】调用 destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("【" + this.getClass().getSimpleName() + "】调用 afterPropertiesSet");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("【" + this.getClass().getSimpleName() + "】调用 setApplicationContext");
    }

    /**
     * @PostConstruct 启动容器的时候会启用
     */
    @PostConstruct
    public void init() {
        System.out.println("【" + this.getClass().getSimpleName() + "】注解@PostConstruct 定义的自定义初始化方法!!!");
    }

    @PreDestroy
    public void destroy1() {
        System.out.println("【" + this.getClass().getSimpleName() + "】注解@PreDestroy 定义的自定义销毁方法!!!");
    }

}
