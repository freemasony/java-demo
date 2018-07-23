package com.io;

import com.thread.ThreadPoolUtil;
import com.thread.ThreadTask;

import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.math.BigDecimal;

/**
 * Created by zhoujian on 2018/4/11.
 */
public class eee {

    public static void main(String[] args) throws FileNotFoundException, NoSuchFieldException, IllegalAccessException {

        BigDecimal bd = new BigDecimal("");


        for(int i=1;i<=30;i++){
            ThreadPoolUtil.execute(new ThreadTask(i));
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("****线程没结束:"+ThreadPoolUtil.executor);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("****线程没结束:"+ThreadPoolUtil.executor);

        for(int i=31;i<=60;i++){
            ThreadPoolUtil.execute(new ThreadTask(i));
        }


            System.out.println("****线程没结束:"+ThreadPoolUtil.executor);
    }

    public static void integerpd() throws NoSuchFieldException, IllegalAccessException {
        Class cache=Integer.class.getDeclaredClasses()[0];
        Field field = cache.getDeclaredField("cache");
        field.setAccessible(true);
        Integer[] d = (Integer[]) field.get(cache);
        d[132]=d[133];
        int a=2;
        int b=a+a;
        System.out.printf("%d + %d = %d",a,a,b);
        System.out.println();
    }
}
