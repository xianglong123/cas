package com.cas.domain;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author: xianglong[xiang_long@suixingpay.com]
 * @date: 14:21 2020-01-20
 * @version: V1.0
 * @review:
 */
@Component
@Data
public class User {

    @Value("#{merch.username?.toUpperCase() + '312312312'}")
    private String username;

    @Value("123")
    private String password;

    @Value("#{1+2}")
    private int age;

    @Value("#{'222' eq '222'}")
    private boolean sex;

    @Value("#{T(System).currentTimeMillis()}")
    private Long initTime;

    private String hight;

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
