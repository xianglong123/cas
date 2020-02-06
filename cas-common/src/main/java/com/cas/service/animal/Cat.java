package com.cas.service.animal;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @author: xianglong[xiang_long@suixingpay.com]
 * @date: 15:44 2020-01-20
 * @version: V1.0
 * @review:
 */
@Primary
@Component
public class Cat implements Animal{

    @Override
    public void use() {
        System.out.println("猫【" + Cat.class.getSimpleName() + "】是宠物。");
    }
}
