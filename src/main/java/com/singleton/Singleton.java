package com.singleton;

/**
 * Created by zhoujian on 2017/8/22.
 */
public class Singleton {

    private static Singleton instance ;

    private volatile static Singleton volatileInstance ;

    public static Singleton getInstance(){
        synchronized (Singleton.class){
            if(instance==null){
                instance=new Singleton();
            }
        }
        return instance;
    }


    /**
     * 双重检测法
     * instance=new Singleton() 不是原子性操作
     * 1、给instance分配内存
     * 2、调用singleton 构造方法初始化数据
     * 3、将instance对象指向分配的内存空间
     * jvm中存在指令重排序优化，最终结果一样，但内部指令调用顺序未知，即2、3 的操作步骤顺序不能保证
     * 如果执行顺序为1-3-2 则，在3执行完毕后，2还未执行，
     * 此时instance！=null，但还没有初始化，其他线程使用时，直接返回没有初始化的实例，出错。
     * @param i
     * @return
     */
    public static Singleton getInstance(int i){
        if(instance==null){
            synchronized (Singleton.class){
                if(instance==null){
                    instance=new Singleton();
                }
            }
        }
        return instance;
    }


    /**
     * volatile 防止jvm指令重排序
     * @return
     */
    public static Singleton getVolatileInstance(){
        if(volatileInstance==null){
            synchronized (Singleton.class){
                if(volatileInstance==null){
                    volatileInstance=new Singleton();
                }
            }
        }

        return volatileInstance;
    }


    /**
     * 使用内部类，java规定 内部类只有在第一次使用时才加载
     */
    public static  class SingletonHandler{
        private static final Singleton INTANCE=new Singleton();
    }

    public static final Singleton getInstance(String string){
        return SingletonHandler.INTANCE;
    }


}
