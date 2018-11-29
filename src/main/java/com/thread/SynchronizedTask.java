package com.thread;

/**
 * Created by zhoujian on 2018/6/13.
 */
public class SynchronizedTask implements Runnable {

    private SynchronizedTest t;

    public SynchronizedTask(SynchronizedTest t) {
        this.t = t;
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
//        t.test1();
        t.test();
    }
}
