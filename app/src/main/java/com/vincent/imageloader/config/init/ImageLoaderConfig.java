package com.vincent.imageloader.config.init;

import com.vincent.imageloader.cache.ImageCache;
import com.vincent.imageloader.config.display.DisplayConfig;
import com.vincent.imageloader.policy.LoadPolicy;

/**
 * Created by lixiang on 2017/8/21.
 */

public class ImageLoaderConfig {
    //缓存
    private ImageCache mCache;
    //最大的线程数量,默认为当前cpu核心数+1
    private int mThreadCount = Runtime.getRuntime().availableProcessors() + 1;

    //配置placeholder相关的展示项
    private DisplayConfig displayConfig = new DisplayConfig();
    /**
     * 配置线程队列的加载顺序
     */
    private LoadPolicy mLoaderPolicy;

    public LoadPolicy getLoaderPolicy() {
        return mLoaderPolicy;
    }

    public DisplayConfig getDisplayConfig() {
        return displayConfig;
    }

    public void setLoaderPolicy(LoadPolicy mLoaderPolicy) {
        this.mLoaderPolicy = mLoaderPolicy;
    }

    public ImageCache getCache() {
        return mCache;
    }

    public ImageLoaderConfig setCache(ImageCache mCache) {
        this.mCache = mCache;
        return this;
    }

    public int getThreadCount() {
        return mThreadCount;
    }

    public ImageLoaderConfig setThreadCount(int mProcessCount) {
        this.mThreadCount = Math.max(1, mProcessCount);
        return this;
    }

    public ImageLoaderConfig setLoadErrorRes(int rid) {
        displayConfig.setLoadErrorRes(rid);
        return this;
    }

    public ImageLoaderConfig setLoadingRes(int rid) {
        displayConfig.setLoadingRes(rid);
        return this;
    }


}
