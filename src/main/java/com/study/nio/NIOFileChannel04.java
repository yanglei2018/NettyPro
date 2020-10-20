package com.study.nio;

import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * @author Yang Lei
 * @version 1.0
 * @date 2020/10/20 17:07
 * @description 使用transferFrom方法
 */
public class NIOFileChannel04 {
    @SneakyThrows
    public static void main(String[] args) {
        FileInputStream fileInputStream = new FileInputStream("1.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("2.txt");

        FileChannel sourceChannel = fileInputStream.getChannel();
        FileChannel destChannel = fileOutputStream.getChannel();

        destChannel.transferFrom(sourceChannel,0,sourceChannel.size());

        fileInputStream.close();
        fileOutputStream.close();
        sourceChannel.close();
        destChannel.close();
    }
}
