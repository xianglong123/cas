package com.cas.websocket;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 17:58 2020-12-04
 * @version: V1.0
 * @review:
 */
@ServerEndpoint("/WebSocketTest")
public class WebSocketTest {

    private Session session;

    @OnOpen
    private void onOpw(Session session) {
        this.session = session;
        System.out.println("打开了连接");
    }

    @OnMessage // 收到消息执行
    public void onMessage(String message, Session session) {
        System.out.println(message);
        try {
            sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("关闭连接");
    }

    @OnError // 连接错误执行
    public void onError(Throwable error, Session session) {
        System.out.println("错误的时候执行");
        error.printStackTrace();
    }

    public void sendMessage(String message) throws IOException {
        this.session.getAsyncRemote().sendText(message);
    }

}
