package com.thread;

/**
 * Created by zhoujian on 2018/6/13.
 */
public class SynchronizedTest {

    public synchronized void test() {
        System.out.println("当前线程:"+Thread.currentThread().getName()+":start");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("当前线程:"+Thread.currentThread().getName()+":end");
    }


    public synchronized static void staticTest(){
        System.out.println("当前static线程:"+Thread.currentThread().getName()+":start");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("当前static线程:"+Thread.currentThread().getName()+":end");
    }
}
