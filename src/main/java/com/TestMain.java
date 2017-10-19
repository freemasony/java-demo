package main.java.com;

import com.enumtest.EnumCode;

import java.util.HashMap;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhoujian on 2017/8/6.
 */
public class TestMain {
    private static  volatile Vector<Integer> vector=new Vector<>();

    private static AtomicInteger race=new AtomicInteger();

    public static void main(String[] args){

        EnumCode eventCode = EnumCode.valueOf("LOGIN");
        System.out.println("******eventCode:"+eventCode.name());

//            for(int i=0;i<10;i++){
//                vector.add(i);
//                race.incrementAndGet();
//            }
//        HashMap map=new HashMap();
//
//            for(int a=0;a<30;a++) {
//
//                Thread removeThread = new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        for (int i = 0; i < vector.size(); i++) {
//                            vector.remove(i);
//                        }
//                    }
//                });
//                removeThread.setPriority(Thread.MAX_PRIORITY);
//
//                Thread printThread = new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        for (int i = 0; i < vector.size(); i++) {
//                            System.out.println(vector.get(i));
//                        }
//                    }
//                });
//                printThread.setPriority(Thread.MIN_PRIORITY);
//                removeThread.start();
//                printThread.start();
//
//            }
    }
}
