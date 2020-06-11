package com.cas.clone;

import com.cas.domain.User;
import com.cas.utils.MyUtil;

/**
 * @author: xianglong[xiang_long@suixingpay.com]
 * @date: 18:37 2020-06-09
 * @version: V1.0
 * @review: 通过序列化和反序列化实现深度克隆
 */
public class CloneTest {

    public static void main(String[] args) throws Exception{
        User user = new User("xl","123");
        User user1 = MyUtil.clone(user);
        user1.setAge(22);
        System.out.println(user);
        System.out.println(user1);
    }

}

