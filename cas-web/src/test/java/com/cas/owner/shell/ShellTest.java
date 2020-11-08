package com.cas.owner.shell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 14:42 2020-10-20
 * @version: V1.0
 * @review:
 */
public class ShellTest {

    public static void main(String[] args) throws IOException {
        Process exec = Runtime.getRuntime().exec("ls -l /");
        printStream(exec.getInputStream());
        printStream(exec.getErrorStream());
    }

    private static void printStream(InputStream inputStream) {
        if (inputStream == null) {
            System.out.println("input null");
            return;
        }
        String line = "";
        try (BufferedReader input = new BufferedReader(new InputStreamReader(inputStream))) {
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e1) {
            System.out.println("输出流失败" + e1);
            e1.printStackTrace();
        }
    }
}
