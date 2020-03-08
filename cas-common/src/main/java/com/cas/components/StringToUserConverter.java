package com.cas.components;

import com.cas.domain.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author: xianglong[xiang_long@suixingpay.com]
 * @date: 14:29 2020-02-22
 * @version: V1.0
 * @review: 类型转换器
 */
@Component
public class StringToUserConverter implements Converter<String, User> {

    @Override
    public User convert(String source) {
        User user = new User();
        String[] strArr = source.split("-");
        String username = strArr[0];
        String password = strArr[1];
        int age = Integer.parseInt(strArr[2]);
        user.setUsername(username);
        user.setPassword(password);
        user.setAge(age);
        return user;
    }
}
