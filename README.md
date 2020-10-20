##  Buffer

**缓冲区（Buffer）**：**缓冲区本质上是一个可以读写数据的内存块**，可以理解成是一个容器对象(含数组)，该对象提供了一组方法，可以更轻松地使用内存块，缓冲区对象内置了一些机制，能够跟踪和记录缓冲区的状态变化情况。**Channel提供从文件、网络读取数据的渠道，但是读取或写入的数据都必须经由Buffer。**

Buffer类定义了所有缓冲区都具有的四个属性来提供其所包含的数据元素信息。

```Java
private int mark = -1; //标记
private int position = 0; //下一个要被读或写的元素的索引
private int limit; //缓冲区当前终点，不能对缓冲区超过己先的位置进行读写操作，可以修改
private int capacity; //容量，不可变
```

常用方法

~~~java
public final int capacity()//返回此缓冲区的容量
public final int position()//返回此缓冲区的位置
public final Buffer position(int newPositio()//设置此缓冲区的位置
public final int limit()//返回此缓冲区的浪制
public final Buffer limit(int newLimit)//设置此援冲区的限制
~~~

基本使用

```Java
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
```

## Channel

### **基本介绍**

1) NIO的通道类似于流，但有些区别如下:
通道可以同时进行读写，而流只能读或者只能写
通道可以实现异步读写数据
通道可以从缓冲读数据，也可以写数据到缓冲:

2) BlO中的stream是单向的，例如FilelnputStream 对象只能进行读取教据的操作，而NIO中的通道(Channel)是双向的，可以读操作，也可以写操作。
3) **Channel在NIO中是一个接口public interface Channel extends Closeable**
4) 常用的 Channel类有:FileChannel、DatagramChannel、ServerSocketChannel和SocketChannel。
5)**FileChannel用于文件的数据读写，DatagramChannel用于UDP的数据读写，ServerSocketChannel和 SocketChannel用于TCP的数据读写。**

案例一：

```Java
public class NIOFileChannel {
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
```

案例二

```Java
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
```