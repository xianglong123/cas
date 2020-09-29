package com.cas.owner.proxy;

import com.cas.owner.cglib.ProxyFactory;
import com.cas.owner.cglib.UserDaoImpl;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 20:40 2020-09-29
 * @version: V1.0
 * @review:
 */
public class CglibBeanTest {

    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory(new UserDaoImpl());
        UserDaoImpl proxy = (UserDaoImpl) proxyFactory.getProxyInstance();
        proxy.save();
    }

}
