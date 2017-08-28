package com.vincent.imageloader.cache;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.vincent.imageloader.utils.CommonUtil;

/**
 * Created by Vincent on 2017/8/20.
 */

public class MemoryCache implements ImageCache {
    private LruCache<String, Bitmap> mLruCache;
    private int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
    public MemoryCache() {
        mLruCache = new LruCache<String, Bitmap>(maxMemory) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight()/1024;
            }
        };
    }

    @Override
    public Bitmap getCache(String url) {
        String key=CommonUtil.hashKeyFromUrl(url);
        return mLruCache.get(key);
    }

    @Override
    public void setCache(String url, Bitmap bitmap) {
        String key=CommonUtil.hashKeyFromUrl(url);
        mLruCache.put(key, bitmap);
    }

    @Override
    public boolean removeCache(String url) {
        String key = CommonUtil.hashKeyFromUrl(url);
        Bitmap bitmap = mLruCache.remove(key);
        return bitmap == null ? false : true;
    }
}
