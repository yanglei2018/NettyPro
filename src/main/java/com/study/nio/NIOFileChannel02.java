package com.study.nio;

import lombok.SneakyThrows;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Yang Lei
 * @version 1.0
 * @date 2020/10/20 16:21
 * @description 从文件中读出数据并且输出到控制台
 */
public class NIOFileChannel02 {
    @SneakyThrows
    public static void main(String[] args) {
        //1.创建一个输入流
        File file = new File("D://file01.txt");
        FileInputStream fileInputStream = new FileInputStream(file);

        //2.创建一个byteBuffer对象
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());

        //3.fileInputStream创建一个Channel(数据已经到channel中了)
        FileChannel channel = fileInputStream.getChannel();

        //4.将通道的数据读入到byteBuffer
        channel.read(byteBuffer);

        //5.打印
        System.out.println(new String(byteBuffer.array()));
    }
}
