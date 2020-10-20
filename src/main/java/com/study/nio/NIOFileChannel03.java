package com.study.nio;

import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Yang Lei
 * @version 1.0
 * @date 2020/10/20 16:45
 * @description
 */
public class NIOFileChannel03 {
    @SneakyThrows
    public static void main(String[] args) {
        FileInputStream fileInputStream = new FileInputStream("1.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("2.txt");

        FileChannel channel01 = fileInputStream.getChannel();
        FileChannel channel02 = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        while (true){//循环两次，第一次循环读出所有的内容，第二次读出文件末尾-1
            byteBuffer.clear();//重要 如果少了这一步 将read一直为0
            int read = channel01.read(byteBuffer);
            System.out.println(read);
            if (read == -1){
                break;
            }
            byteBuffer.flip();
            channel02.write(byteBuffer);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }
}
