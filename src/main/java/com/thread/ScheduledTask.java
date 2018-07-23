package com.thread;

/**
 * Created by zhoujian on 2018/4/19.
 */
public class ScheduledTask implements Runnable{

    private int count;

    public ScheduledTask(int count) {
        this.count = count;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
//        System.out.println("***ScheduledTask---start:"+count+",线程数:"+ThreadPoolUtil.executor);
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        System.out.println("***ScheduledTask---end:"+count+",线程数:"+ThreadPoolUtil.executor);
    }
}
