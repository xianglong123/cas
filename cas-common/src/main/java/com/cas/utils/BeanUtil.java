package com.cas.utils;

import java.lang.reflect.Field;
import java.math.BigDecimal;

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


}
