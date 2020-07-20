package com.cas.owner.socker.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Client_test {
    public static void main(String[] str) {
        try {
            //确定端口
            int port = 1230;
            //创建DatagramSocket 对象，并指定该程序的通信端口为8080
            DatagramSocket receiveSocket = new DatagramSocket(port);
            String out = "";
            System.out.println("客户端准备ok");
            while (true) {
                //创建接收缓冲区
                byte[] by = new byte[1024];
                do {
                    //创建接收数据包
                    DatagramPacket receivePacket = new DatagramPacket(by, by.length);
                    //接收数据
                    receiveSocket.receive(receivePacket);
                    //解析消息并打印数据
                    out = new String(receivePacket.getData(), 0, receivePacket.getLength());
                    System.out.println("服务端发送来的数据：" + out);
                } while (!out.equals("q"));
                receiveSocket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}