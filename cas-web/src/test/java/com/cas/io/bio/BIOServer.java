package com.cas.io.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 20:03 2020-10-29
 * @version: V1.0
 * @review: 测试BIO小demo
 */
public class BIOServer {

    public static void main(String[] args) throws Exception {
        ExecutorService threadPool = Executors.newCachedThreadPool();

        ServerSocket serverSocket = new ServerSocket(6666);

        System.out.println("服务器启动了");

        while (true) {
            // 监听， 等待客户端连接
            final Socket socket = serverSocket.accept();
            System.out.println("连接到一个客户端");

            threadPool.execute(() -> {
                handler(socket);
            });
        }
    }

    private static void handler(Socket socket) {

        try {
            byte[] bytes = new byte[1024];
            InputStream inputStream = socket.getInputStream();
            while (true) {
                int read;
                if ((read = inputStream.read(bytes)) != -1) {
                    System.out.println(new String(bytes, 0, read));
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

}
