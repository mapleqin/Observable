package net.soulwolf.observable;

import android.util.Log;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * author: EwenQin
 * since : 15/5/13 下午4:06.
 */
class ThreadFactory implements java.util.concurrent.ThreadFactory{

    private final AtomicInteger mCount = new AtomicInteger(1);

    @Override
    public Thread newThread(Runnable r) {
        String threadName = "Observable #" + mCount.getAndIncrement();
        Log.i("Observable", "The 'ThreadFactory' new thread .[" + threadName + "]");
        return new Thread(r,threadName);
    }
}
