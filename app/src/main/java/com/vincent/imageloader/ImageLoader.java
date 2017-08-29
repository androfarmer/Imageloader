package com.vincent.imageloader;

import android.widget.ImageView;

import com.vincent.imageloader.config.display.DisplayConfig;
import com.vincent.imageloader.config.init.ImageLoaderConfig;
import com.vincent.imageloader.core.RequestQueue;
import com.vincent.imageloader.request.BitmapRequest;

/**
 * Created by lixiang on 2017/8/21.
 */

public class ImageLoader {

    private RequestQueue mRequestQueue;

    public ImageLoaderConfig getImageLoaderConfing() {
        return mConfig;
    }

    //初始化配置类
    private ImageLoaderConfig mConfig;
    private static ImageLoader mInstance;

    public static ImageLoader getInstance() {
        if (mInstance == null) {
            synchronized (ImageLoader.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoader();
                }
            }
        }
        return mInstance;
    }

    private ImageLoader() {
    }

    public void init(ImageLoaderConfig config) {
        mConfig = config;
        mRequestQueue = new RequestQueue(mConfig.getThreadCount());
        mRequestQueue.start();
    }

    public void load(ImageView imageView, String url) {
        DisplayConfig displayConfig = mConfig.getDisplayConfig();
        BitmapRequest request = new BitmapRequest(imageView, url, displayConfig);
        mRequestQueue.addRequest(request);
    }
}

