package com.vincent.imageloader.cache;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * Created by Vincent on 2017/8/20.
 */

public class DoubleCache implements ImageCache {
    private MemoryCache mMemoryCache;
    private DiskCache mDiskCache;

    public DoubleCache(Context context) {
        mMemoryCache = new MemoryCache();
        mDiskCache = new DiskCache(context);
    }

    @Override
    public Bitmap getCache(String url) {
        Bitmap bitmap = mMemoryCache.getCache(url);
        if (bitmap == null) {
            bitmap = mDiskCache.getCache(url);
        }
        return bitmap;
    }

    @Override
    public void setCache(String url, Bitmap bitmap) {
        mMemoryCache.setCache(url, bitmap);
        mDiskCache.setCache(url, bitmap);
    }

    @Override
    public boolean removeCache(String url) {
      return mMemoryCache.removeCache(url)&&mDiskCache.removeCache(url);
    }
}
