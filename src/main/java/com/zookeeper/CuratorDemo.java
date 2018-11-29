package main.java.com.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhoujian on 2018/11/23.
 */
public class CuratorDemo {

    public static CuratorFramework client;
    public CuratorDemo() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
         client =
                CuratorFrameworkFactory.newClient(
                        "10.100.200.133:2181",
                        5000,
                        3000,
                        retryPolicy);
        client.start();

    }

    public static void listenterNode(CuratorFramework client) throws Exception {
        ExecutorService executorService= Executors.newCachedThreadPool();
        TreeCache treeCache=new TreeCache(client,"/gqhmt/gq-mdm/zk");
        treeCache.getListenable().addListener(new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, TreeCacheEvent treeCacheEvent) throws Exception {
                ChildData childData=treeCacheEvent.getData();
                if(childData!=null){
                    switch (treeCacheEvent.getType()){
                        case NODE_UPDATED:
                            System.out.println("数据节点修改："+new String(childData.getData()));
                    }
                }
            }
        });
        treeCache.start();

    }


    public static void main(String[] args) throws InterruptedException {
        CuratorDemo curatorDemo=new CuratorDemo();
        try {
            CuratorDemo.listenterNode(client);
        } catch (Exception e) {
            e.printStackTrace();
        }
        CountDownLatch countDownLatch=new CountDownLatch(1);
        countDownLatch.await();
    }







}










