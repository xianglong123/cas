package com.cas.zother;

import java.security.MessageDigest;
import java.util.Scanner;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 14:37 2020-02-12
 * @version: V1.0
 * @review:
 */
public class SignCreateTest {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("输入姓名：");
        String accName = in.next();
        System.out.println("########");
        System.out.print("输入身份证：");
        String certificateNo = in.next();
        System.out.println("姓名：" + accName + "  身份证：" + certificateNo + "  业务码(固定):SXF60060001_01");
        String str = "accName="+ accName +"&certificateNo=" + certificateNo + "&funcCode=SXF60060001_01&key=ors";
        String result = md5Encode(str).toUpperCase();
        System.out.println(result);
    }


    private static final String[] HEX_DIGITS = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    /**
     * 转换字节数组为16进制字串
     *
     * @param b 字节数组
     * @return 16进制字串
     */
    public static String byteArrayToHexString(byte[] b) {
        StringBuilder resultSb = new StringBuilder();
        for (byte aB : b) {
            resultSb.append(byteToHexString(aB));
        }
        return resultSb.toString();
    }

    /**
     * 转换byte到16进制
     *
     * @param b 要转换的byte
     * @return 16进制格式
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return HEX_DIGITS[d1] + HEX_DIGITS[d2];
    }

    /**
     * MD5编码
     *
     * @param origin 原始字符串
     * @return 经过MD5加密之后的结果
     */
    public static String md5Encode(String origin) {
        String resultString = null;
        try {
            resultString = origin;
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byteArrayToHexString(md.digest(resultString.getBytes("UTF-8")));
        } catch (Exception e) {
            System.out.println("加密异常");
        }
        return resultString;
    }
}
