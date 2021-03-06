package com.cas.components;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 17:54 2020-01-20
 * @version: V1.0
 * @review: @Scope 可以定义这个Bean 在容器中是单例还是原型
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON) //ConfigurableBeanFactory 只能定义原型和单例
//@Scope(WebApplicationContext.SCOPE_REQUEST)  //WebApplicationContext  可以定义 Bean 的 会话，请求和应用作用域
public class ScopeBean {
}
