package com.vincent.imageloader.core;

import android.util.Log;

import com.vincent.imageloader.loader.Loader;
import com.vincent.imageloader.loader.LoaderManager;
import com.vincent.imageloader.request.BitmapRequest;

import java.util.concurrent.BlockingQueue;

/**
 * Created by lixiang on 2017/8/29.
 */

public class RequestDispatcher extends Thread {
    private BlockingQueue<BitmapRequest> mQueue;

    public RequestDispatcher(BlockingQueue<BitmapRequest> queue) {
        this.mQueue = queue;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                BitmapRequest request = mQueue.take();
                if (request.isCancle) {
                    continue;
                }
                String url = request.requestUrl;
                Loader loader = LoaderManager.getInstance().getLoader(url);
                loader.loadImage(request);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
