package com.nio;

import com.utils.ThreadPoolUtil;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class SelectorListen {

    private static ThreadPoolUtil threadPoolUtil;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Selector selector=null;
        ServerSocketChannel serverSocketChannel=null;
		try {
            selector=Selector.open();
            serverSocketChannel=ServerSocketChannel.open();
			InetSocketAddress address=new InetSocketAddress(InetAddress.getLocalHost(),8888);
			serverSocketChannel.socket().bind(address);
			serverSocketChannel.configureBlocking(false);
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
			while(true){
			    if(selector.select()==0)
			        continue;

                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while(iterator.hasNext()){
                    SelectionKey selectionKey=iterator.next();
                    if(selectionKey.isAcceptable()){
                        ServerSocketChannel ssc= (ServerSocketChannel) selectionKey.channel();
                        SocketChannel clientSocket = ssc.accept();

                        if(clientSocket==null)
                            continue;
                        clientSocket.configureBlocking(false);
                        clientSocket.register(selector,SelectionKey.OP_READ);
                    }
                    if(selectionKey.isConnectable()){
                        continue;
                    }
                    if(selectionKey.isReadable()){
                        threadPoolUtil=new ThreadPoolUtil(1,1);
                        threadPoolUtil.execute(new ReadClientSocketThread(selectionKey,null));
                        threadPoolUtil.shutdown();
                    }
                    if(selectionKey.isWritable()){
                        continue;
                    }
                    iterator.remove();
                }
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
		    try {
                if(serverSocketChannel!=null)
                    serverSocketChannel.close();
                if(selector!=null)
                    selector.close();
            }catch (Exception e){
		        e.printStackTrace();
            }
        }

	}
	

}
