package com.cas.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 17:42 2020-12-15
 * @version: V1.0
 * @review: 文件中输入数据
 * 字符流：更擅长处理字符，类似中文这种数据
 * 字节流：更擅长处理字节，类似图片，音频，声音这类数据
 *
 */
public class fileTest {

    public static void main(String[] args) throws Exception {
//        byByte();
//        byChar();
        copyImage();
    }


    /**
     * 字节流复制图片
     * @throws IOException
     */
    private static void copyImage() throws IOException {
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            File file = new File("/Users/xianglong/Desktop/intent_saven.png");
            fis = new FileInputStream(file);
            fos = new FileOutputStream("/Users/xianglong/Desktop/intent.png");

            int date = 0;

            while ((date = fis.read()) != -1) {
                fos.write(date);
                fos.flush();
            }
            System.out.println("复制完毕");
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 字符流复制文件
     */
    private static void byChar() {
        Reader fr=null;
        BufferedReader br=null;
        Writer fw=null;
        BufferedWriter bw=null;
        try {
            fr=new FileReader("/Users/xianglong/Desktop/aaa.txt");
            br=new BufferedReader(fr);
            fw=new FileWriter("/Users/xianglong/Desktop/bbb.txt");
            bw=new BufferedWriter(fw);
            String str;
            while((str=br.readLine())!=null){
                bw.write(str);
                bw.newLine();
                bw.flush();
            }
            System.out.println("写入成功");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            try {
                if (bw != null) {
                    bw.close();
                }
                if (fw != null) {
                    fw.close();
                }
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 字节流读取文件
     * @throws Exception
     */
    private static void byByte() throws Exception {
        FileOutputStream fileOutputStream = null;
        FileInputStream fileInputStream = null;

        File file = new File("/Users/xianglong/Desktop/aaa.txt");

        if (!file.exists()) {
            file.mkdir();
        }
        try {
            fileOutputStream = new FileOutputStream(file);
            String str = "i like you 123";
            fileOutputStream.write(str.getBytes());
            fileInputStream = new FileInputStream(file);
            int n = 0;
            StringBuilder sBuffer = new StringBuilder();
            while (n != -1)  //当n不等于-1,则代表未到末尾
            {
                n = fileInputStream.read();//读取文件的一个字节(8个二进制位),并将其由二进制转成十进制的整数返回
                char by = (char) n; //转成字符
                sBuffer.append(by);
            }
            System.out.println(sBuffer.toString());
        } finally {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        }
    }


}
