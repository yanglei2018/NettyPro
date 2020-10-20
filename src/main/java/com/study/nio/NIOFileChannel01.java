package com.study.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Yang Lei
 * @version 1.0
 * @date 2020/10/20 15:29
 * @description FileChannel实例一：将字符串写入到文件中
 */
public class NIOFileChannel01 {
    @lombok.SneakyThrows
    public static void main(String[] args) {
        String str = "hello,world";

        //创建一个输出流
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\file01.txt");

        //通过输出流fileOutputStream获取对应的FileChannel
        FileChannel fileChannel = fileOutputStream.getChannel();

        //创建一个缓冲区Buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //1.将str放入到byteBuffer
        byteBuffer.put(str.getBytes());

        //2.进行读写翻转
        byteBuffer.flip();

        //3.将byteBuffer吸入到通道fileChannel
        fileChannel.write(byteBuffer);

        //关闭流
        fileOutputStream.close();
    }
}
