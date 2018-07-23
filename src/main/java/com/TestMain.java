package com;


import com.utils.JsonSerializeUtil;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhoujian on 2017/8/6.
 */
public class TestMain {
    private static Vector<Integer> vector=new Vector<>();

    private static AtomicInteger race=new AtomicInteger();

    private  static ScheduledExecutorService scheduledExecutorService= Executors.newScheduledThreadPool(10);

    public static void main(String[] args) throws FileNotFoundException, NoSuchFieldException, IllegalAccessException, ParseException {
        String activityTime=new SimpleDateFormat("yyyyMM").format(new SimpleDateFormat("yyyyMM").parse("2018-07-01 00:00:00"));
//        System.out.println(Biannary2Decimal(111) );
//        System.out.println(Decimal2Binary(7) );

        String key="5c40c2380223a:abdd9189acfa67eee1287c700228f190";
        System.out.println(new BASE64Encoder().encode(key.getBytes()));
        Map<String,Object> jsonMap=new HashMap<>();
        Map<String,Object> map=new HashMap<>();
        map.put("title","冠e通");
        map.put("content","你好~");
        map.put("vibrate",1);
        map.put("lights",0);


        List<String> token=new ArrayList<>();
        token.add("a6586d4f6d7dc64c4e4e8ccf3380141c65440b71");
        jsonMap.put("audience_type","token_list");
        jsonMap.put("platform","android");
        jsonMap.put("message_type","notify");
        jsonMap.put("push_id","0");
        jsonMap.put("environment","dev");
        jsonMap.put("message",map);
        jsonMap.put("token_list",token);



        System.out.println("json:"+ JsonSerializeUtil.objectToJson(jsonMap));

    }




    /**
     * 讲10 进制转化为二进制
     * @param de
     * @return
     */
    public static String Decimal2Binary(int de){
        String numstr = "";
        while (de>0){
            int res = de%2; //除2 取余数作为二进制数
            numstr = res + numstr;
            de = de/2;
        }
        return  numstr;
    }

    /**
     * 将二进制转换为10进制
     * @param bi
     * @return
     */
    public  static  Integer Biannary2Decimal(int bi){

        String binStr = bi+"";

        Integer sum = 0;
        int len = binStr.length();

        for (int i=1;i<=len;i++){
            //第i位 的数字为：
            int dt = Integer.parseInt(binStr.substring(i-1,i));
            sum+=(int)Math.pow(2,len-i)*dt;
        }
        return  sum;
    }

    public static boolean rangeInDefined(int current, int min, int max){
        return Math.max(min, current) == Math.min(current, max);
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

    public static Object getboolean(){
        return Boolean.FALSE;
    }


    public static  boolean change(String mobile){
        mobile="111";
        return true;
    }

    public  static  String test(){
        try {
            if(true){
                System.out.println("try ");
                return "try";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "catch";
        }finally {
            System.out.println("finally ");
        }
        return "success";
    }

    public static String updateVersion(String versionStr){
        if(versionStr.length()==3)
            versionStr=versionStr+"1";
        else {
            String lastIndex=versionStr.substring(versionStr.length()-1,versionStr.length());
            versionStr=versionStr.substring(0,versionStr.length()-1)+""+(Integer.parseInt(lastIndex)+1);
        }

        return versionStr;
    }

    public static String getMd5ByFile(File file) throws FileNotFoundException {
        String value ="" ;
        FileInputStream in = new FileInputStream(file);
        try {
            MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(byteBuffer);
            BigInteger bi = new BigInteger(1, md5.digest());
            value = bi.toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if( null!= in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }









}
