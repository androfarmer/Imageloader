package com.vincent.imageloader.cache;

import android.graphics.Bitmap;

/**
 * Created by lixiang on 2017/8/21.
 */

public class NoCache implements ImageCache {
    @Override
    public Bitmap getCache(String url) {
        return null;
    }

    @Override
    public void setCache(String url, Bitmap bitmap) {

    }

    @Override
    public boolean removeCache(String url) {
        return false;
    }
}
