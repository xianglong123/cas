package com.cas.utils;

import com.google.gson.Gson;
import org.springframework.util.StringUtils;

import static jdk.nashorn.internal.objects.NativeString.trim;

/**
 * @author: xianglong
 * @date: 2019/8/17 14:20
 * @version: V1.0
 * @review: xiang_long
 * 1.获取一个Gson实例
 */
public class StringUtil extends StringUtils {

    private static Gson gson;

    //初始化spring容器就实例化，率先加载
    static {
        gson = new Gson();
    }


    /**
     * 获取一个Gson实例
     *
     * @return
     */
    public static Gson getGson() {
        return gson;
    }

    /**
     * description: 判断<code>str</code>是否为空串或<code>null</code>
     *
     * @param str
     * @return 若为空串或<code>null</code>返回<code>true</code>,否则返回<code>false</code>
     */
    public static boolean isEmpty(String str) {
        if (null == str)
            return true;
        str = trim(str);
        return "".equals(str) ? true : false;
    }

}
