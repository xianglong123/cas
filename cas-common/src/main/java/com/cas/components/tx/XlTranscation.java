package com.cas.components.tx;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 10:54 2020-07-09
 * @version: V1.0
 * @review: 自定义注解 + AOP 实现自定义事务
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface XlTranscation {
}
