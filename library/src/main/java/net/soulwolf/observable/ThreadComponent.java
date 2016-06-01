package net.soulwolf.observable;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * author: EwenQin
 * since : 15/5/13 下午3:58.
 */
class ThreadComponent {

    static final int DEFAULT_THREAD_POOL_SIZE = 20;

    private static ThreadComponent component;

    public static ThreadComponent getComponent(){
        if(component == null){
            synchronized (ThreadComponent.class){
                if(component == null){
                    component = new ThreadComponent();
                }
            }
        }
        return component;
    }

    private final ExecutorService mThreadPool;
    private final Handler mMainHandler;

    private ThreadComponent(){
        this.mThreadPool = Executors.newFixedThreadPool(DEFAULT_THREAD_POOL_SIZE, new ThreadFactory());
        this.mMainHandler = new Handler(Looper.getMainLooper());
    }

    public Runnable execute(Runnable runnable){
        this.mThreadPool.execute(runnable);
        return runnable;
    }

    public boolean post(Runnable runnable){
        return this.mMainHandler.post(runnable);
    }

    public void removeCallbacks(Runnable callback){
        this.mMainHandler.removeCallbacks(callback);
    }

    public boolean postDelayed(Runnable r, long delayMillis) {
        return this.mMainHandler.postDelayed(r,delayMillis);
    }

    public void shutdown(){
        this.mThreadPool.shutdown();
    }

    public Handler getMainHandler(){
        return this.mMainHandler;
    }

}
