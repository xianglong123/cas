package com.cas.owner.socker.udp;

import lombok.SneakyThrows;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;

public class Server_Test extends Thread{

    DatagramSocket sendDatagramSocket = null;

    @SneakyThrows
    @Override
    public void run() {
        try {
            sendDatagramSocket = new DatagramSocket(11111);
            System.out.println(getdate() + "  等待客户端连接...");
            new SendMessage().start();
            //创建接收缓冲区
            byte[] by = new byte[1024];
            //创建接收数据包
            DatagramPacket receivePacket = new DatagramPacket(by, by.length);
            while(!sendDatagramSocket.isClosed()) {
                sendDatagramSocket.receive(receivePacket);
                String string = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println(getdate() + "  客户端：（" + sendDatagramSocket.getInetAddress().getHostAddress()
                        + ") 说：" + string);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String getdate() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
    }

    class SendMessage extends Thread {
        @Override
        public void run() {
            Scanner scanner = null;
            DatagramPacket sendPscket = null;
            try {
                if (!sendDatagramSocket.isClosed()) {
                    scanner = new Scanner(System.in);
                    byte[] in;
                    do {
                        in = scanner.next().getBytes();
                        sendPscket = new DatagramPacket(in, in.length, InetAddress.getByName("127.0.0.1"), 1230);
                        //通过DatagramSocket 的send方法发送数据
                        sendDatagramSocket.send(sendPscket);
                    } while (!Arrays.toString(in).equals("q"));
                    scanner.close();
                    sendDatagramSocket.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        Server_Test test = new Server_Test();
        test.start();
    }

//    public static void main(String[] str) {
//        try {
//            //创建DatagramSocket 对象，并指定该程序的通信端口为10000
//            DatagramSocket sendDatagramSocket = new DatagramSocket(11111);
//            //确定要发送的消息
//            String string = " udp Demo";
//            //转成字符数组类型
//            byte[] by = string.getBytes();
//            //确定要发送的端口
//            int port = 1230;
//            //创建发送类型的数据包，这个数据包包含了要发往的ip地址和端口
//            DatagramPacket sendPscket = new DatagramPacket(by, by.length, InetAddress.getByName("127.0.0.1"), port);
//            //通过DatagramSocket 的send方法发送数据
//            sendDatagramSocket.send(sendPscket);
//            //创建接收缓冲区
//            byte[] bt = new byte[1024];
//            //创建接收类型的数据包
//            //DatagramPacket(byte[] buf, int length)构造 DatagramPacket，用来接收长度为 length 的数据包
//            DatagramPacket receivePacket = new DatagramPacket(bt, bt.length);
//            //通过DatagramSocket 的receive方法发送数据
//            sendDatagramSocket.receive(receivePacket);
//            //打印---数据包
//            String daString = new String(receivePacket.getData(), 0, receivePacket.getLength());//getData()方法是返回数据缓冲区
//            System.out.println(daString);
//            //关闭接收
//            sendDatagramSocket.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }




}