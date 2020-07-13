package com.cas.owner.utils;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;

/**
 * @author: xianglong
 * @date: 2019/9/27 15:55
 * @version: V1.0
 * @review: xiang_long
 */
@Slf4j
public class DESUtil {

    /**
     * 加密
     *
     * @param datasource
     * @param password
     * @return
     */
    public static byte[] encrypt(String datasource, String password) {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(password.getBytes("UTF-8"));
            //创建一个密钥工厂,然后用它把DESKeySpec转换成
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(desKey);
            //Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES");
            //用密钥初始化Cipher对象，ENCRYPT_MODE用于将Cipher初始化为加密模式的常量
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, random);
            //现在，获取数据并加密，正式执行加密操作
            //按单部分操作加密或解密数据，或者结束一个多部分操作
            return cipher.doFinal(datasource.getBytes("UTF-8"));
        } catch (Throwable e) {
            log.error("DES加密异常,详情：" + e.toString());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     *
     * @param datasource
     * @param password
     * @return
     */
    public static String decrypt(byte[] datasource, String password) throws Exception {
        //DES算法要求有一个可信任的随机数据源
        SecureRandom random = new SecureRandom();
        //创建一个DESKeySpec对象
        DESKeySpec desKey = new DESKeySpec(password.getBytes());
        //创建一个密钥工厂,然后用它把DESKeySpec转换成
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKey);
        //Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance("DES");
        //用密钥初始化Cipher对象，ENCRYPT_MODE用于将Cipher初始化为加密模式的常量
        cipher.init(Cipher.DECRYPT_MODE, secretKey, random);
        //现在，获取数据并加密，正式执行加密操作
        //按单部分操作加密或解密数据，或者结束一个多部分操作
        return new String(cipher.doFinal(datasource));
    }

    public static String byte2hex(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        String stmp = "";
        for (int i = 0; i < bytes.length; i++) {
            stmp = Integer.toHexString(bytes[i] & 0xFF);
            if (stmp.length() == 1) {
                stringBuilder.append("0").append(stmp);
            } else {
                stringBuilder.append(stmp);
            }
        }
        return stringBuilder.toString().toUpperCase();
    }

    public static byte[] hex2byte(byte[] b) throws UnsupportedEncodingException {
        if ((b.length % 2) != 0) {
            throw new IllegalArgumentException("长度不是偶数");
        }
        byte[] b2 = new byte[b.length / 2];
        for (int n = 0; n < b.length; n += 2) {
            String item = new String(b, n, 2, "UTF-8");
            b2[n / 2] = (byte) Integer.parseInt(item, 16);
        }
        return b2;
    }

}
