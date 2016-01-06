package com.mie;

import java.util.concurrent.Semaphore;

/**
 * Created by Administrator on 2016/1/4.
 */
public class ChildSemaphore extends Semaphore{

    private int counts = 10;
    public ChildSemaphore(int permits, boolean fair) {
        super(permits, fair);
    }

    public ChildSemaphore(int permits) {
        super(permits);
    }
    public ChildSemaphore(int permits,int nCounts) {
        super(permits);
        this.counts = nCounts;
    }
    @Override
    public void acquire() throws InterruptedException {
        super.acquire();
    }

    @Override
    public void release() {
        counts--;
        System.out.println(counts);
        super.release();
    }

    public int getCounts() {
        return counts;
    }
}
