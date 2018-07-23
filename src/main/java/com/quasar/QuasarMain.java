package main.java.com.quasar;

import sun.misc.BASE64Encoder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by zhoujian on 2018/7/4.
 */
public class QuasarMain {

    public static void main(String[] args) throws Exception {
//        int fiberNum=1000000;
//        CountDownLatch countDownLatch=new CountDownLatch(1);
//        AtomicInteger atomicInteger=new AtomicInteger(0);
//        for(int i=0;i<=fiberNum;i++){
//             new Fiber<>(()->{
//                atomicInteger.incrementAndGet();
//                if(atomicInteger.get()==fiberNum)
//                    System.out.println("***done**");
//
//                 Strand.sleep(1000000);
//             }).start();
//        }
//        countDownLatch.await();

        test();
    }



    public static void  test() throws Exception {
        File file = new File("D:\\123.jpg");
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        InputStream inputStream=new FileInputStream(file);
        byte[] buffer = new byte[1024];
        int len = 0;
        while( (len=inputStream.read(buffer)) != -1 ){
            outStream.write(buffer, 0, len);
        }
        inputStream.close();
        BASE64Encoder encoder = new BASE64Encoder();
        System.out.println( encoder.encode(outStream.toByteArray()));
    }



}
