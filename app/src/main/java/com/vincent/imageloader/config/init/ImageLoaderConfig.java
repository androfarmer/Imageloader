package com.vincent.imageloader.config.init;

import com.vincent.imageloader.cache.ImageCache;
import com.vincent.imageloader.config.display.DisplayConfig;

/**
 * Created by lixiang on 2017/8/21.
 */

public class ImageLoaderConfig {
    //缓存
    private ImageCache mCache;
    //最大的线程数量,默认为当前cpu核心数+1
    private int mProcessCount = Runtime.getRuntime().availableProcessors() + 1;
    private DisplayConfig displayConfig;

    public ImageCache getCache() {
        return mCache;
    }

    public ImageLoaderConfig setCache(ImageCache mCache) {
        this.mCache = mCache;
        return this;
    }

    public int getProcessCount() {
        return mProcessCount;
    }

    public ImageLoaderConfig setProcessCount(int mProcessCount) {
        this.mProcessCount = Math.max(1, mProcessCount);
        return this;
    }
}
