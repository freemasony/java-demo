package main.java.com.quasar;

import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.strands.Strand;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhoujian on 2018/7/4.
 */
public class QuasarMain {

    public static void main(String[] args) throws Exception {


        HttpClient httpClient = new DefaultHttpClient();
        try {
            // this twitter call returns json results.
            // see this page for more info: https://dev.twitter.com/docs/using-search
            // http://search.twitter.com/search.json?q=%40apple

            // Example URL 1: this yahoo weather call returns results as an rss (xml) feed
            //HttpGet httpGetRequest = new HttpGet("http://weather.yahooapis.com/forecastrss?p=80020&u=f");

            // Example URL 2: this twitter api call returns results in a JSON format
            HttpGet httpGetRequest = new HttpGet("http://10.100.200.101:8106/api/column_news_base?columnIds=421");

            // Execute HTTP request
            HttpResponse httpResponse = httpClient.execute(httpGetRequest);

//       System.out.println("----------------------------------------");
//       System.out.println(httpResponse.getStatusLine());
//       System.out.println("----------------------------------------");

            // Get hold of the response entity
            HttpEntity entity = httpResponse.getEntity();

            // If the response does not enclose an entity, there is no need
            // to bother about connection release
            byte[] buffer = new byte[1024];
            if (entity != null) {
                InputStream inputStream = entity.getContent();
                try {
                    int bytesRead = 0;
                    BufferedInputStream bis = new BufferedInputStream(inputStream);
                    while ((bytesRead = bis.read(buffer)) != -1) {
                        String chunk = new String(buffer, 0, bytesRead);
                        System.out.println(chunk);
                    }
                } catch (IOException ioException) {
                    // In case of an IOException the connection will be released
                    // back to the connection manager automatically
                    ioException.printStackTrace();
                } catch (RuntimeException runtimeException) {
                    // In case of an unexpected exception you may want to abort
                    // the HTTP request in order to shut down the underlying
                    // connection immediately.
                    httpGetRequest.abort();
                    runtimeException.printStackTrace();
                } finally {
                    // Closing the input stream will trigger connection release
                    try {
                        inputStream.close();
                    } catch (Exception ignore) {
                    }
                }
            }
        } catch (ClientProtocolException e) {
            // thrown by httpClient.execute(httpGetRequest)
            e.printStackTrace();
        } catch (IOException e) {
            // thrown by entity.getContent();
            e.printStackTrace();
        } finally {
            // When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resources
            httpClient.getConnectionManager().shutdown();
        }
    }


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
