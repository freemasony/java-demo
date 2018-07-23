package com.thread;

/**
 * Created by zhoujian on 2018/6/13.
 */
public class SynchronizedMain {


    public static void main(String[] args){

        for(int i=1;i<=10;i++){
            SynchronizedTest t=new SynchronizedTest();
            Thread thread=new Thread(new SynchronizedTask(t));
            thread.start();
            System.out.println("当前并发线程:"+i);
        }

    }

}
