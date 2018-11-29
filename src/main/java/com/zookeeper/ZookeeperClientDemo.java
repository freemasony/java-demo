package com.zookeeper;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZookeeperClientDemo {

	private static final Logger logger = LoggerFactory.getLogger(ZookeeperClientDemo.class);

	// 设置会话超时时间
	private static final int SEESION_TIMEOUT = 60000;

	private static ZooKeeper zookeeper;

	private static Watcher watcher = new Watcher() {
		@Override
		public void process(WatchedEvent event) {
			// TODO Auto-generated method stub
			logger.info("process : " + event.getType()+ " ;WatchedEvent string : " + event.toString());
		}
	};

	private static String connectString = "10.100.200.133:2181";

	
	public static void init(){
		try {
			connect();
			logger.info("connect init successful...");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("ZooKeeper init false : {} "+e);
			e.printStackTrace();
		}
	}
	
	
	public static void destory(){
		try {
			close();
			logger.info("connect close successful...");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			logger.error("ZooKeeper destory false : {} "+e);
			e.printStackTrace();
		}
	}
	
	/**
	 * 连接Zookeeper
	 * 
	 * @throws IOException
	 */
	public static void connect() throws IOException {
		zookeeper = new ZooKeeper(connectString, SEESION_TIMEOUT, watcher);
	}

	/**
	 * 关闭Zookeeper
	 * @throws InterruptedException 
	 */
	public static void close() throws InterruptedException {
		  zookeeper.close(); 
	}

	
	
	/** 
     * 创建一个znode  
     *  1.CreateMode 取值   
     *  PERSISTENT：持久化，这个目录节点存储的数据不会丢失 
     *  PERSISTENT_SEQUENTIAL：顺序自动编号的目录节点，这种目录节点会根据当前已近存在的节点数自动加 1，然后返回给客户端已经成功创建的目录节点名； 
     *  EPHEMERAL：临时目录节点，一旦创建这个节点的客户端与服务器端口也就是 session过期超时，这种节点会被自动删除 
     *  EPHEMERAL_SEQUENTIAL：临时自动编号节点 
     * <br>------------------------------<br> 
     */ 
	public String create(String path, String data) {
		String result = null;

		try {
			result=zookeeper.create(path, data.getBytes(), Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		logger.debug("create result : {}"+result);
		return result;
	}

	
	/**
	 * 删除节点
	 * @param path
	 */
	public void delete(String path){
		try {
			if(exists(path)){
				zookeeper.delete(path, -1);
			} 
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.debug("delete successful");
	}
	
	
	/**
	 * 读取指定节点数据内容
	 * @param path
	 * @return
	 */
	public String readData(String path){
		byte bt1[]=null;
		byte bt2[]=null;
		String result1=null;
		String result2=null;
		try {
			bt1=zookeeper.getData(path, false, null);
			bt2=zookeeper.getData(path, watcher, null);
			result1=new String(bt1);
			result2=new String(bt2); 
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "result1 : {} "+result1+"  ;result2 : {} "+result2;
	}
	
	
	
	/**
	 * 修改指定节点数据内容
	 * @param path
	 * @param data
	 * @return
	 */
	public String writeData(String path ,String data){
		String result=null;
		try {
			 Stat stat = zookeeper.setData(path, data.getBytes(), -1); 
			 result=readData(path);
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
	 /** 
     *    判断节点是否存在 
     *    设置是否监控这个目录节点，这里的 watcher 是在创建 ZooKeeper实例时指定的 watcher 
     * <br>------------------------------<br> 
     */  
	public boolean exists(String path){
		boolean flag=false;
		Stat stat = null;  
		try {
			stat=zookeeper.exists(path, false);
			if(stat!=null){
				flag=true;
				logger.debug("exists result : {}", stat.getCzxid());
			}
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	
	/** 
     *    判断节点是否存在,  
     *    设置是否监控这个目录节点，这里的 watcher 是在创建 ZooKeeper实例时指定的 watcher 
     * <br>------------------------------<br> 
     */   
    public boolean testExistsWatch(String path) {  
    	boolean flag=false;
        Stat stat = null;  
         try {  
             stat = zookeeper.exists(path, true);  
             if(stat!=null){
            	 flag=true;
            	 logger.debug("exists result : {}", stat.getCzxid());
             }
        } catch (Exception e) {  
        	logger.error(e.getMessage());   
        	e.printStackTrace();
        }  
         return flag;
    }  
    
    
    
    /**
     * 获取指定节点下的子节点
     * @param path
     */
    public void getChild(String path){
    	try {
			List<String> list =zookeeper.getChildren(path, true);
			if(list!=null&&list.size()>0){
				 for (String node : list) {
					 logger.info("node {}", node);  
				 } 
			} 
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    public static void main(String[] args) throws IOException, InterruptedException {
		CountDownLatch countDownLatch=new CountDownLatch(1);
    	String path1="/gqhmt/gq-mdm";
    	ZookeeperClientDemo zookeeperClientdemo=new ZookeeperClientDemo();
    	zookeeperClientdemo.connect();
    	
    /*	String create1=zookeeperClientdemo.create(path1, "zk001data");
    	String create2=zookeeperClientdemo.create(path2, "zk002data");
    	System.out.println("create1 : "+create1);
    	System.out.println("create2 : "+create2);
    	*/
    	zookeeperClientdemo.exists(path1);

    	/*String data1=zookeeperClientdemo.readData(path1);
    	String data2=zookeeperClientdemo.readData(path2);
    	System.out.println("data1 : "+data1);
    	System.out.println("data2 : "+data2);
    	
    	zookeeperClientdemo.delete(path2);
    	
    	 data2=zookeeperClientdemo.readData(path2);
    	 
    	System.out.println("data2 : "+data2);*/

    	countDownLatch.await();

    	zookeeperClientdemo.close();
	}
	
}
