package com.cas.service.animal;

import org.springframework.stereotype.Component;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 15:43 2020-01-20
 * @version: V1.0
 * @review:
 */
@Component
public class Dog implements Animal{

    @Override
    public void use() {
        System.out.println("狗【" + Dog.class.getSimpleName() + "】是看门的。");
    }
}
