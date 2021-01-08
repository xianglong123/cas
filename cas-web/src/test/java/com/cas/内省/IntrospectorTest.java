package com.cas.内省;

import com.cas.domain.User;
import com.cas.utils.BeanUtil;
import com.cas.utils.StringUtil;

import java.util.Map;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 14:48 2020-03-27
 * @version: V1.0
 * @review: 比较两对象的差异
 */
public class IntrospectorTest {

    public static void main(String[] args) throws  Exception{
        User user = new User();
        User user2 = new User();
        user2.setAge(8);
        Map<String, String[]> diffMap = BeanUtil.copy(user2, user, true, true, "");
        System.out.println(StringUtil.getGson().toJson(user));
        System.out.println(StringUtil.getGson().toJson(user2));
        System.out.println(StringUtil.getGson().toJson(diffMap));
    }
}
