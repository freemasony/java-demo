package com.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by zhoujian on 2018/2/3.
 */
public class RedisMain {

    public static void main(String[] args) {

        Set<String> sentinels = new HashSet<String>();
        String hostAndPort1 = "10.100.200.75:26379";
        String hostAndPort2 = "10.100.200.76:26379";
        String hostAndPort3 = "10.100.200.77:26379";
        sentinels.add(hostAndPort1);
        sentinels.add(hostAndPort2);
        sentinels.add(hostAndPort2);

        String clusterName = "sentinelredisname";

        JedisSentinelPool redisSentinelJedisPool = new JedisSentinelPool(clusterName, sentinels);

        Jedis jedis = null;
        try {
            String key="gqmdm:userDeviceInfoAll:1:mdm";
            jedis = redisSentinelJedisPool.getResource();
//            System.out.println("***"+jedis.set(key,"1"));
//            System.out.println("***"+jedis.hgetAll(key));
            System.out.println("***"+jedis.hgetAll(key).size());
            System.out.println("***"+jedis.hget(key,"1438356"));
//            System.out.println("***"+jedis.hget(key,"1310922"));

//            Map<String,String> map=jedis.hgetAll(key);
//
//
//            String ids=bufferRead("c:\\1.txt");
//            Set<Integer> set= Sets.newTreeSet();
//            for(String str:ids.split(",")){
//                try {
//                    set.add(Integer.parseInt(str));
//                }catch (Exception e){
//                    continue;
//                }
//            }
//            set.add(1048297);
//
//            List<Integer> list= Lists.newArrayList(set);
//            List<Integer> list1=new ArrayList<>();
//            for(Integer id:list){
//                if(!map.containsKey(id.toString())){
//                    list1.add(id);
//                }
//            }
//            System.out.println("---:"+list1.size());



        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            redisSentinelJedisPool.returnBrokenResource(jedis);
            redisSentinelJedisPool.close();
            redisSentinelJedisPool.destroy();
        }
    }



    private static String bufferRead(String filename){
        try {
            BufferedReader buf=new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
            String str=null;
            while((str=buf.readLine())!=null) {
                System.out.println(str);
                break;
            }
            return str;
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    }
