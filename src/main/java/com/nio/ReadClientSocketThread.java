package com.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

/**
 * Created by Administrator on 2017/6/30.
 */
public class ReadClientSocketThread implements Runnable {

    private SelectionKey selectionKey;

    private Object responseMessage;

    private SocketChannel clientSocketChannel;

    public ReadClientSocketThread(SelectionKey selectionKey, Object responseMessage) {
        this.selectionKey = selectionKey;
        this.responseMessage = responseMessage;
        this.clientSocketChannel= (SocketChannel) selectionKey.channel();
    }

    public ReadClientSocketThread() {}

    @Override
    public void run() {
        System.out.println("当前ReadClientSocketThread线程---- "+Thread.currentThread().getName() +"  --- start...");

        try {
            ByteBuffer buffer=ByteBuffer.allocate(1024);
            buffer.flip();
            clientSocketChannel.read(buffer);
            buffer.clear();
            String data=new String(buffer.array()).trim();
            System.out.println("线程: "+Thread.currentThread().getName()+"-读取数据"+data);
            clientSocketChannel.write(buffer);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                clientSocketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("当前ReadClientSocketThread线程---- "+Thread.currentThread().getName() +"  --- end...");
    }
}
