package com.vincent.imageloader.cache;

import android.graphics.Bitmap;

/**
 * Created by Vincent on 2017/8/20.
 */

public interface ImageCache {
    Bitmap getCache(String url);
    void setCache(String url, Bitmap bitmap);
    boolean removeCache(String url);
}
