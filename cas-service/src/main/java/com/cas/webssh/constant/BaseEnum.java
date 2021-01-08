package com.cas.service.webssh.constant;

/**
 * 基础枚举类
 * @ClassName BaseEnum
 * @Author luo jin jiang
 * @Date 2019/6/27 21:21
 * @Version 1.0
 */
public interface BaseEnum<K,V> {
    /**
     * 获取编码
     * @return 编码
     */
    K code();

    /**
     * 获取描述
     * @return 描述
     */
    V desc();
}
