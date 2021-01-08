package com.cas.ssh.ssh2;

import ch.ethz.ssh2.ChannelCondition;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 09:57 2020-11-26
 * @version: V1.0
 * @review:
 */
public class Ssh2Test {


    public static void main(String[] args) throws IOException {
        Connection conn = new Connection("192.168.56.101");
        conn.connect();
        boolean flag = conn.authenticateWithPassword("root", "1391086179xl..");
        if (flag) {
            Session session = conn.openSession();
            session.execCommand("tail -f /root/other/show_ports.sh");
            session.waitForCondition(ChannelCondition.STDOUT_DATA, 5 * 60 * 1000);
            System.out.println(processStdout(session.getStdout(), "utf-8"));
            session.close();

        }
    }


    public static String processStdout(InputStream in, String charset) {
        InputStream stdout = new StreamGobbler(in);
        StringBuffer buffer = new StringBuffer();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(stdout, charset));
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
//                buffer.append(line + "\n");
            }
            br.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }

}
