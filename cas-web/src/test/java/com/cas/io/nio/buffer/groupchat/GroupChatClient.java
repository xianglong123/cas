package com.cas.io.nio.buffer.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 17:44 2020-11-11
 * @version: V1.0
 * @review:
 */
public class GroupChatClient {

    // 定义相关的属性
    private final String HOST = "127.0.0.1";
    private final int PORT = 6667;
    private Selector selector;
    private SocketChannel socketChannel;
    private String username;

    // 构造器
    public GroupChatClient() throws IOException {

        selector = Selector.open();
        // 连接服务器
        socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress(HOST, PORT));

        // 设置非阻塞
        socketChannel.configureBlocking(false);

        // 将channel 注册到selector
        socketChannel.register(selector, SelectionKey.OP_READ);
        // 得到 username
        username = socketChannel.getLocalAddress().toString().substring(1);

        System.out.println(username + " is ok...");
    }

    // 向服务器发送消息
    public void sendInfo(String info) {

        info = username + "  说：" + info;

        try {
            socketChannel.write(ByteBuffer.wrap(info.getBytes()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 读取服务器回复的消息
    public void readInfo() {
        System.out.println("readInfo");
        try {
            int readChannels = selector.select();
            if (readChannels > 0) { // 又可以用的通道
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

                while (iterator.hasNext()) {

                    SelectionKey key = iterator.next();

                    if (key.isReadable()) {
                        // 得到相关的通道
                        SocketChannel sc = (SocketChannel) key.channel();
                        // 得到一个buffer
                        ByteBuffer buffer = ByteBuffer.allocate(1024);

                        // 把读到的缓冲区
                        sc.read(buffer);
                        // 读取到的缓冲区的数据转成字符串
                        String msg = new String(buffer.array());
                        System.out.println(msg.trim());
                    }
                    iterator.remove();
                    System.out.println("end");
                }
            } else {
                System.out.println("没有可用的通道");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws Exception {

        // 启动我们客户端
        GroupChatClient chatClient = new GroupChatClient();

        // 启动一个线程
        new Thread(() -> {
            while (true) {
                chatClient.readInfo();
                try {
                    Thread.currentThread().sleep(3000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // 发送数据给服务器端
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            chatClient.sendInfo(s);
        }


    }


}
