package com.cas.domain.invoke;

import lombok.Data;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 16:22 2020-01-28
 * @version: V1.0
 * @review: 这里存储的是反射所需要的数据
 */
@Data
public class Invocation {

    private Object[] params;
    private Method method;
    private Object target;

    public Invocation(Object target, Method method, Object[] params) {
        this.target = target;
        this.method = method;
        this.params = params;
    }

    //反射方法
    public Object proceed() throws InvocationTargetException, IllegalAccessException {
        //哪个方法 执行在 (哪个类，哪些参数);
        return method.invoke(target, params);
    }

}
