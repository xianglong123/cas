package com.cas.utils;

import java.net.URLDecoder;

/**
 * @author: xianglong
 * @date: 2019/10/14 11:45
 * @version: V1.0
 * @review: xiang_long
 */
public class URLEncoderUtil {

    /**
     * 就互联网传送中文格式转码
     */
    public static void getUrlDecod(String enc) throws Exception {
        //%D1%E9%D6%A4%C2%EB%A3%BA660071%A3%AC%D3%D0%D0%A7%C6%DA90%C3%EB%A3%AC%C8%E7%B7%C7%B1%BE%C8%CB%B2%D9%D7%F7%A3%AC%C7%EB%CE%F0%D0%B9%C2%A9%A1%A3
        String[] encType = {"UTF-16", "UTF-8", "GB2312", "GBK", "ISO-8859-1", "Unicode"};
        for (String str : encType) {
            System.out.println("***************" + str + "**********************");
//            String encode = URLEncoder.encode(enc,str);
//            System.out.println(str+"    "+encode);
            System.out.println(URLDecoder.decode(enc, str));
//            System.out.println("一个字符转换成"+ (str3.split("%").length-1)/enc.length()+"个长度的字节");
        }
    }
}
