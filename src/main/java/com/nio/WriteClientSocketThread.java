package com.nio;

import java.nio.channels.SelectionKey;

/**
 * Created by Administrator on 2017/6/30.
 */
public class WriteClientSocketThread implements Runnable {

    private SelectionKey selectionKey;

    private Object requestMessage;

    public WriteClientSocketThread() {
    }

    public WriteClientSocketThread(SelectionKey selectionKey, Object requestMessage) {
        this.selectionKey = selectionKey;
        this.requestMessage = requestMessage;
    }

    @Override
    public void run() {

    }
}
