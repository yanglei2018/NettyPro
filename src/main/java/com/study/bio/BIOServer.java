package com.study.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Yang Lei
 * @version 1.0
 * @date 2020/10/16 16:16
 * @description BIOServer
 */
public class BIOServer {
    public static void main(String[] args) throws IOException {
        /**
         * 思路：
         * 1. 创建一个线程池
         * 2. 如果有客户端连接，则创建一个线程，与之通信
         */
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();

        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("****服务器启动了****");

        while (true){
            System.out.println("****等待连接****");
            //监听，等待客户端连接
            final Socket socket = serverSocket.accept();
            System.out.println("连接到一个客户端");

            //创建一个线程，与之通信
            newCachedThreadPool.execute(new Runnable() {
                public void run() {
                    //可以和客户端通信
                    handler(socket);
                }
            });
        }
    }
    //和客户端通信的handler方法
    public static void handler(Socket socket){
        try {
            System.out.println("线程ID：" + Thread.currentThread().getId() + "名字：" + Thread.currentThread().getName());
            byte[] bytes = new byte[1024];
            //获取输入流
            InputStream inputStream = socket.getInputStream();

            while (true){
                System.out.println("线程ID：" + Thread.currentThread().getId() + "名字：" + Thread.currentThread().getName());

                System.out.println("read...");
                int read = inputStream.read(bytes);
                if (read != -1){
                    //输出客户端发送的数据
                    System.out.println(new String(bytes, 0, read));
                }else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            System.out.println("关闭连接");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
