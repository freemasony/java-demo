package com.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhoujian on 2018/10/19.
 */
public class ThreadMain {

    public static void main(String[] args){
        Lock lock=new ReentrantLock();

        new Thread(()-> runmethod(lock),"thread1").start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                runmethod(lock);
            }
        },"thread2").start();
    }

    public static void runmethod(Lock lock){
        lock.lock();
        for(int i=0;i<=5;i++){
            System.out.println("ThreadName:"+Thread.currentThread().getName()+(" i="+i));
        }
        System.out.println();
        lock.unlock();
    }
}
