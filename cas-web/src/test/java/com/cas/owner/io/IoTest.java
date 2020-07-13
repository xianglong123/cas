package com.cas.owner.io;

import com.cas.domain.User;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 10:49 2020-06-22
 * @version: V1.0
 * @review: java 序列化和反序列化   节点流和处理流的区别：https://www.cnblogs.com/komean/p/10276770.html
 */
public class IoTest {

    public static void main(String[] args) {
        User user = new User();
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        ByteArrayOutputStream bis = null;

        try {
            oos = new ObjectOutputStream(new FileOutputStream("/Users/xianglong/Desktop/user.txt"));
            oos.writeObject(user);
            oos.writeObject(user);

            ois = new ObjectInputStream(new FileInputStream("/Users/xianglong/Desktop/user.txt"));
            User user1 = (User) ois.readObject();
            User user2 = (User) ois.readObject();
            String str = "xianglong";
            bis = new ByteArrayOutputStream(10);
            bis.write(12);
            byte[] bytes = bis.toByteArray();
            System.out.println(bytes[0]);

            System.out.println(user);
            System.out.println(user1);
            System.out.println(user2);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            if (oos != null) {
                oos.close();
            }
            if (ois != null) {
                ois.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
