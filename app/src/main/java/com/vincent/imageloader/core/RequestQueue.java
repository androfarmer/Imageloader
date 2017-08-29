package com.vincent.imageloader.core;

import com.vincent.imageloader.ImageLoader;
import com.vincent.imageloader.request.BitmapRequest;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by lixiang on 2017/8/29.
 */

public class RequestQueue {
    /**
     * 线程安全的请求队列
     */
    private BlockingQueue<BitmapRequest> mQueue = new PriorityBlockingQueue<>();
    /**
     * 队列请求分派的线程数
     */
    private int mThreadCount = Runtime.getRuntime().availableProcessors()+1;

    /**
     * 请求的序列化生成器
     */
    private AtomicInteger mSerialNumGenerator = new AtomicInteger(0);

    /**
     * 线程分派数组
     */
    private RequestDispatcher[] mDispatcherArray;


    public RequestQueue(int threadCount)
    {
        this.mThreadCount=threadCount;
    }
    /**
     * 条件请求到请求队列
     *
     * @param bitmapRequest
     */
    public void addRequest(BitmapRequest bitmapRequest) {
        if (bitmapRequest == null) {
            return;
        }
        if (!mQueue.contains(bitmapRequest)) {
            bitmapRequest.serialNum=this.generateSerialNumber();
            mQueue.add(bitmapRequest);
        }
    }

    public void removeRequest(BitmapRequest bitmapRequest) {
        if (bitmapRequest == null) {
            return;
        }
        mQueue.remove(bitmapRequest);

    }

    private void startDispath() {

        mDispatcherArray = new RequestDispatcher[mThreadCount];
        for (int i = 0; i < mThreadCount; i++) {
            mDispatcherArray[i] = new RequestDispatcher(mQueue);
            mDispatcherArray[i].start();
        }
    }

    public void start() {
        stop();
        startDispath();
    }

    public void stop() {
        if (mDispatcherArray != null) {
            for (int i = 0; i < mThreadCount; i++) {
                mDispatcherArray[i].interrupt();
            }
        }
    }


    /**
     * 为每个请求生成一个系列号
     *
     * @return 序列号
     */
    private int generateSerialNumber() {
        return mSerialNumGenerator.incrementAndGet();
    }
}
