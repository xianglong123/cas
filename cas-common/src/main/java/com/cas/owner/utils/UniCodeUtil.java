package com.cas.owner.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author: xianglong
 * @date: 2019/10/6 17:56
 * @version: V1.0
 * @review: xiang_long
 */
@Slf4j
public class UniCodeUtil {

    /**
     * string  转 unicode
     *
     * @param str
     * @return
     */
    public static String string2Unicode(String str) {
        return JSON.toJSONString(str, SerializerFeature.BrowserCompatible);
    }

    /**
     * unicode 转 string
     *
     * @param enc
     * @return
     */
    public static String uniCode2String(String enc) {
        try {
            return URLDecoder.decode(enc, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            log.info("uniCode转String异常!!!");
        }
        return null;
    }


}
