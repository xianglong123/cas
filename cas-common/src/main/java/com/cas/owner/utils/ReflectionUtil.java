package com.cas.owner.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author: xianglong
 * @date: 2019/8/13 22:43
 * @version: V1.0
 * @review: xiang_long
 * 反射工具：
 * 1.通过类路径，方法名，方法参数反射执行方法
 * 2.通过类路径，方法名，方法参数反射执行"私有方法"
 * 3.通过反射获取私有属性值
 */
public class ReflectionUtil {

    /**
     * 通过类路径，方法名，方法参数反射执行方法
     *
     * @param classPath
     * @param methodName
     * @param args
     * @throws Exception
     */
    public static void getRefMethod(String classPath, String methodName, Object... args) throws Exception {
        Class<?> userClass = Class.forName(classPath);
        Object classType = userClass.newInstance();
        Class<?>[] classes = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            classes[i] = args[i].getClass();
        }
        Method method2 = userClass.getMethod(methodName, classes);
        method2.invoke(classType, args);
    }

    /**
     * 通过类路径，方法名，方法参数反射执行"私有方法"
     */
    public static void getPrivateRefMethod(String classPath, String methodName, Object... args) throws Exception {
        Class<?> className = Class.forName(classPath);
        Object classType = className.newInstance();
        Class<?>[] classes = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            classes[i] = args[i].getClass();
        }
        Method method = className.getDeclaredMethod(methodName, classes);
        method.setAccessible(true);
        method.invoke(classType, args);
    }

    /**
     * 通过反射获取私有属性值
     *
     * @param classPath 类路径
     * @param fieldName 属性名
     * @throws Exception
     */
    public static void getRefField(String classPath, String fieldName, String value) throws Exception {
        Class<?> className = Class.forName(classPath);
        Object classType = className.newInstance();
        Field field = className.getDeclaredField(fieldName);
        //禁止语言访问检查，所以才会让我们访问私有属性
        field.setAccessible(true);
        field.set(classType, value);
    }



}
