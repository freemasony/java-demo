package com.thread;

import java.util.concurrent.CountDownLatch;

/**
 * Created by zhoujian on 2018/6/13.
 */
public class SynchronizedMain {


    public static void main(String[] args) throws InterruptedException {
            for (int i = 0; i < 5; i++) {
                SynchronizedTest t1=new SynchronizedTest("aaa");
                SynchronizedTest t2=new SynchronizedTest("aaa");
                Thread thread1=new Thread(new SynchronizedTask(t1));
                thread1.setName("thread1线程-"+i+"--");
                Thread thread2=new Thread(new SynchronizedTask(t1));
                thread2.setName("thread2线程-"+i+"--");
                thread1.start();
                thread2.start();
            }

    }

}
