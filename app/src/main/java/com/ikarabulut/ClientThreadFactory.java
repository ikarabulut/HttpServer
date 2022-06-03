package com.ikarabulut;

import java.util.concurrent.ThreadFactory;

public class ClientThreadFactory implements ThreadFactory {
    private int numOfThreads = 0;

    public Thread newThread(Runnable r) {
        numOfThreads++;
        return new Thread(r);
    }

    public int getNumOfThreads() {
        return numOfThreads;
    }
}
