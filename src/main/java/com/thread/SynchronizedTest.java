package com.thread;

/**
 * Created by zhoujian on 2018/6/13.
 */
public class SynchronizedTest {

    public SynchronizedTest() {
    }

    private String name;

    public SynchronizedTest(String name) {
        this.name = name;
    }

    public   void test() {
        synchronized (this){
                System.out.println("当前线程:"+Thread.currentThread().getName()+":start");
        }

    }


    public synchronized static void staticTest(){
        for (int i = 0; i < 5; i++) {
            System.out.println("当前static线程:"+Thread.currentThread().getName()+":start");
        }

    }


    public void  test1(){
        synchronized (name){
            for (int i = 0; i <5; i++) {
                System.out.println("当前线程:"+Thread.currentThread().getName()+"name:"+name);
            }
        }
    }
}
