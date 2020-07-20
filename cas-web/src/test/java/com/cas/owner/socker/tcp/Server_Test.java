package com.cas.owner.socker.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Server_Test extends Thread {
    ServerSocket server = null;
    Socket socket = null;

    public Server_Test(int port) {
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        super.run();
        try {
            System.out.println(getdate() + "  等待客户端连接...");
            socket = server.accept();
            new sendMessThread().start();// 连接并返回socket后，再启用发送消息线程
            System.out.println(getdate() + "  客户端 （" + socket.getInetAddress().getHostAddress() + "） 连接成功...");
            InputStream in = socket.getInputStream();
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = in.read(buf)) != -1) {
                System.out.println(getdate() + "  客户端: （"
                        + socket.getInetAddress().getHostAddress() + "）说："
                        + new String(buf, 0, len, "UTF-8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getdate() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
    }

    class sendMessThread extends Thread {
        @Override
        public void run() {
            super.run();
            Scanner scanner = null;
            OutputStream out = null;
            try {
                if (socket != null) {
                    scanner = new Scanner(System.in);
                    out = socket.getOutputStream();
                    String in = "";
                    do {
                        in = scanner.next();
                        out.write(("" + in).getBytes("UTF-8"));
                        out.flush();// 清空缓存区的内容
                    } while (!in.equals("q"));
                    scanner.close();
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    // 函数入口
    public static void main(String[] args) {
        // sockerTest();
        Server_Test server = new Server_Test(1234);
        server.start();
    }


//    public static void sockerTest() throws IOException, InterruptedException {
//        ServerSocket server = new ServerSocket(1234);
//        System.out.println("等待客户端连接...");
//        Socket socket = server.accept();
//        System.out.println("连接成功...");
//        Thread.sleep(4000L);
//        InputStream in = socket.getInputStream();
//        int len = 0;
//        byte[] buf = new byte[1024];
//        System.out.println(buf);
//        int read = in.read(buf);
//        while ((len = in.read(buf)) != -1) {
//            System.out.println(getdate() + "  客户端: （"
//                    + socket.getInetAddress().getHostAddress() + "）说："
//                    + new String(buf, 0, len, "UTF-8"));
//        }
//    }

}