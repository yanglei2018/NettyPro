package com.study.nio;

import java.nio.IntBuffer;

/**
 * @author Yang Lei
 * @version 1.0
 * @date 2020/10/17 9:54
 * @description Buffer的基本使用
 */
public class BasicBuffer {
    public static void main(String[] args) {
        //创建一个Buffer，可以存入五个数据
        IntBuffer intBuffer = IntBuffer.allocate(5);

        for (int i = 0; i < intBuffer.capacity(); i ++){
            intBuffer.put(i*2);
        }

        //进行读写转换
        intBuffer.flip();

        while (intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }
    }
}
