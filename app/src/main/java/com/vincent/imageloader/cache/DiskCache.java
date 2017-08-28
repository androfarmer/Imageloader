package com.vincent.imageloader.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import com.jakewharton.disklrucache.DiskLruCache;
import com.vincent.imageloader.utils.CommonUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Vincent on 2017/8/20.
 */

public class DiskCache implements ImageCache {
    private DiskLruCache mDisklrucache;
    private static final String CACHE_DIR_NAME = "bitmap";
    private static final int MAX_CACHE_SIZE = 1024 * 1024 * 50;

    public DiskCache(Context context) {
        File  cacheDir = CommonUtil.getDiskCacheDir(context, CACHE_DIR_NAME);
        if (!cacheDir.exists()) {
            cacheDir.mkdirs();
        }
        int versionCode = CommonUtil.getAppVersionCode(context);
        try {
            mDisklrucache = DiskLruCache.open(cacheDir, versionCode, 1, MAX_CACHE_SIZE);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Bitmap getCache(String url) {
        String key = CommonUtil.hashKeyFromUrl(url);
        try {
            DiskLruCache.Snapshot snapShot = mDisklrucache.get(key);
            if(snapShot!=null) {
                InputStream inputStream = snapShot.getInputStream(0);
                return BitmapFactory.decodeStream(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void setCache(String url, Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        DiskLruCache.Editor editor = null;
        OutputStream cacheOutPutStream=null;
        try {
            String key = CommonUtil.hashKeyFromUrl(url);
            editor = mDisklrucache.edit(key);
            cacheOutPutStream = editor.newOutputStream(0);
            byte[] data = new byte[1024];
            while (inputStream.read(data) != -1) {
                cacheOutPutStream.write(data);
            }
            editor.commit();
            mDisklrucache.flush();
        } catch (IOException e) {
            e.printStackTrace();
            if(inputStream!=null)
            {
                try {
                    inputStream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if(cacheOutPutStream!=null)
            {
                try {
                    cacheOutPutStream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (editor != null) {
                try {
                    editor.abort();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean removeCache(String url) {
        String key=CommonUtil.hashKeyFromUrl(url);
        try {
           return mDisklrucache.remove(key);
        } catch (IOException e) {
            e.printStackTrace();
            return true;
        }
    }
}
