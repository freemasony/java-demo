package com.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhoujian on 2018/4/18.
 */
public class ThreadTask implements Runnable {

    private int count;

    private ScheduledExecutorService scheduledExecutorService= Executors.newSingleThreadScheduledExecutor();

    public ThreadTask(int count) {
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
//        scheduledExecutorService.schedule(new ScheduledTask(count), TimeUnit.SECONDS.toSeconds(1),TimeUnit.SECONDS);
        ThreadPoolUtil.schedule(new ScheduledTask(count), TimeUnit.SECONDS.toSeconds(1),TimeUnit.SECONDS);
        System.out.println("***正在执行第"+count+"线程结束"+ThreadPoolUtil.scheduledExecutorService);
    }
}
