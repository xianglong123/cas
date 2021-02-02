package com.cas.io.nio.buffer;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 09:22 2020-10-31
 * @version: V1.0
 * @review: 只用一个buffer实现从一个文件到另一文件的拷贝
 */
public class FileChannel03Test {

    public static void main(String[] args) throws IOException {
//        copyByBuffer();
        copyByTransFerForm();
        return;
    }

    /**
     *
     * 通过方法来克隆
     * @throws IOException
     */
    private static void copyByTransFerForm() throws IOException {
        final FileInputStream fileInputStream = new FileInputStream(new File("/Users/xianglong/IdeaProjects/cas/hello.txt"));
        final FileOutputStream fileOutputStream = new FileOutputStream("/Users/xianglong/IdeaProjects/cas/hello3.txt");

        final FileChannel sourceCh = fileInputStream.getChannel();
        final FileChannel destCh = fileOutputStream.getChannel();

        destCh.transferFrom(sourceCh, 0, sourceCh.size());

        sourceCh.close();
        destCh.close();
        fileOutputStream.close();
        fileInputStream.close();
    }


    /**
     * 通过buffer克隆文件
     * @throws IOException
     */
    private static void copyByBuffer() throws IOException {
        final File file = new File("/Users/xianglong/IdeaProjects/cas/hello.txt");

        final FileInputStream fileInputStream = new FileInputStream(file);

        final FileChannel channel = fileInputStream.getChannel();

        ByteBuffer allocate = ByteBuffer.allocate((int)file.length());

        channel.read(allocate);

        System.out.println(new String(allocate.array()));


        // 写入到输出流中

        final FileOutputStream fileOutputStream = new FileOutputStream("/Users/xianglong/IdeaProjects/cas/hello2.txt");
        final FileChannel channel1 = fileOutputStream.getChannel();

        allocate.flip();
        channel1.write(allocate);


        fileInputStream.close();
        fileOutputStream.close();
    }

}
