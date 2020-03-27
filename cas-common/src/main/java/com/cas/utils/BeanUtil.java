package com.cas.utils;

import org.apache.commons.lang3.ArrayUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: xianglong
 * @date: 2019/10/18 17:42
 * @version: V1.0
 * @review: xiang_long
 */
public class BeanUtil {

    /**
     * 获取一个所有属性为默认值的对象
     * @param t
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T initDefaultBean(T t) throws Exception{
        Field[] fields = t.getClass().getDeclaredFields();
        for(Field field : fields) {
            field.setAccessible(true);
            if (field.getType() == String.class) {
                field.set(t,"");
            } else if (field.getType() == Integer.class) {
                field.set(t,0);
            } else if (field.getType() == BigDecimal.class){
                field.set(t,new BigDecimal("0"));
            } else if (field.getType() == Boolean.class) {
                field.set(t, false);
            } else {
                field.set(t,initDefaultBean(field));
            }
        }
        return t;
    }

    /**
     * 判断类型是不是基本数据类型
     * @param claszz
     * @return
     */
    public static boolean isBaseDefaultValue(Class claszz) {
        if(claszz.equals(java.lang.Integer.class)) {
            return true;
        } else if(claszz.equals(java.lang.Byte.class)) {
            return true;
        } else if(claszz.equals(java.lang.Long.class)) {
            return true;
        } else if(claszz.equals(java.lang.Double.class)) {
            return true;
        } else if(claszz.equals(java.lang.Float.class)) {
            return true;
        } else if(claszz.equals(java.lang.Character.class)) {
            return true;
        } else if(claszz.equals(java.lang.Short.class)) {
            return true;
        } else if(claszz.equals(java.lang.Boolean.class)) {
            return true;
        }
        return false;
    }

    /**
     * 比较两个对象差异的属性，并修改目标对象
     *
     * @param source 源对象
     * @param target 目标对象
     * @param recursive 是否递归处理子对象
     * @param diffPropUseFullPath 差异属性是否全路径
     * @param propertyPath 对象当前路径
     * @param ignoreProperties 不比较的属性
     * @return
     * @throws Exception
     */
    public static Map<String, String[]> _copy(Object source, Object target, boolean recursive, boolean diffPropUseFullPath, String propertyPath, String... ignoreProperties) throws Exception {

        // 申明差异属性容器
        Map<String, String[]> diffMap = new HashMap<>();

        // 获取对象类型
        Class<? extends Object> sourceType = source.getClass();
        Class<? extends Object> targetType = target.getClass();

        BeanInfo sourceBeanInfo = null;
        BeanInfo targetBeanInfo = null;

        try {
            // 通过内省获取BeanInfo
            sourceBeanInfo = Introspector.getBeanInfo(sourceType);
            targetBeanInfo = Introspector.getBeanInfo(targetType);
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }

        // 获取所有的属性
        assert sourceBeanInfo != null;
        PropertyDescriptor[] sourcePropertyDescriptors = sourceBeanInfo.getPropertyDescriptors();
        assert targetBeanInfo != null;
        PropertyDescriptor[] targetPropertyDescriptors = targetBeanInfo.getPropertyDescriptors();

        for (int i = 0; i < sourcePropertyDescriptors.length; i++) {
            // 获取单个属性进行比较
            PropertyDescriptor sourceDescriptor = sourcePropertyDescriptors[i];
            PropertyDescriptor targetDescriptor = targetPropertyDescriptors[i];
            // 获取当前比较的属性名 getname和getBaseName的区别在于首字母是否大写
            String propertyname = sourceDescriptor.getName();

            if (propertyname.equals("class")) {
                continue;
            }

            // 拼接全路径(牛逼)
            String newPropertyPath = (propertyPath + (propertyPath.equals("") ? "" : ".") + propertyname);
            // 过滤不比较对象
            if (!ArrayUtils.contains(ignoreProperties, newPropertyPath)) {
                // 拿到源对象的单个属性的值
                Object sourcePropVal = null;
                try {
                    sourcePropVal = sourceDescriptor.getReadMethod().invoke(source);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // 源对象属性值不为空
                if (sourcePropVal != null && StringUtil.isNotEmpty(sourcePropVal.toString())) {
                    // 获取目标对象的属性值
                    Object targetPropVal = null;
                    try {
                        targetPropVal = targetDescriptor.getReadMethod().invoke(target);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    // 非简单类型递归调用比较
                    if (!(sourcePropVal.getClass().isPrimitive()
                            || sourceDescriptor.getPropertyType().getName().contains("java")
                            || isBaseDefaultValue(sourcePropVal.getClass())) && recursive) {
                        // 目标对象为空先初始化，方便后面比较用
                        if (targetPropVal == null) {
                            targetPropVal = targetDescriptor.getPropertyType().newInstance();
                            targetDescriptor.getWriteMethod().invoke(target, targetPropVal);
                        }
                        // 递归调用
                        diffMap.putAll(_copy(sourcePropVal, targetPropVal, recursive, diffPropUseFullPath, newPropertyPath, ignoreProperties));
                    } else {
                        // 简单类型直接比较
                        if(!sourcePropVal.equals(targetPropVal)) {
                            targetDescriptor.getWriteMethod().invoke(target, sourcePropVal);
                            diffMap.put(diffPropUseFullPath ? newPropertyPath : propertyname, new String[] {String.valueOf(targetPropVal), String.valueOf(sourcePropVal)});
                        }
                    }
                }
            }
        }
        return diffMap;
    }


}
