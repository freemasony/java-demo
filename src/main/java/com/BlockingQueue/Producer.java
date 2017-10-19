package com.BlockingQueue;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

	 BlockingQueue<String> queue;  
	  
	    public Producer(BlockingQueue<String> queue) {  
	        this.queue = queue;  
	    }  
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
         try {
        	 String temp="A Product 生产线程："+Thread.currentThread().getName();
    		 System.out.println("I have made a product:"  
                     + Thread.currentThread().getName());  
			queue.put(temp);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//如果队列是满的话，会阻塞当前线程  
	}

}
