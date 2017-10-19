package com.BlockingQueue;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by Administrator on 2017/7/17.
 */
public class ArrayBlockingQueueTest {

    public static void test(){
        ArrayBlockingQueue arrayBlockingQueue=new ArrayBlockingQueue(5);
        try {
            arrayBlockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static int test1(int i){
        try{
            System.out.println(i);
            List list=null;
            list.add(1);
            i++;

        }catch (Exception e){
            i++;
            System.out.println("2");
            return i;
        }finally {
            i++;
            System.out.println("3");
        }
        return i;
    }

    public  static void main(String[] args) throws ParseException {
        int aaa=1;
        Timestamp timestamp=new Timestamp(new Date().getTime());
        SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd");
        System.out.println(sdf1.format(timestamp));
        aaa=3;
        System.out.println(aaa);

    }
}
