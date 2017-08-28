package com.vincent.imageloader;

import android.graphics.Bitmap;
import android.widget.ImageView;
import com.vincent.imageloader.cache.ImageCache;
import com.vincent.imageloader.config.init.ImageLoaderConfig;
import com.vincent.imageloader.http.MyHttp;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lixiang on 2017/8/21.
 */

public class ImageLoader {
    //图片缓存
    private ImageCache mCache;
    //线程池
    private ExecutorService mThreadPool;
    //初始化配置类
    private ImageLoaderConfig mImageLoaderConfing;
    private  static  ImageLoader mInstance;
    public static  ImageLoader getInstance()
    {
        if(mInstance==null)
        {
            synchronized (ImageLoader.class)
            {
                if(mInstance==null)
                {
                    mInstance=new ImageLoader();
                }
            }
        }
        return mInstance;
    }
    private ImageLoader()
    {

    }

    public  void  init(ImageLoaderConfig config) {
        mImageLoaderConfing=config;
        mCache=mImageLoaderConfing.getCache();
        int processCount=mImageLoaderConfing.getProcessCount();
        mThreadPool = Executors.newFixedThreadPool(processCount);
    }
    public void load(final ImageView imageView, final String url)
    {
        Bitmap bitmap=mCache.getCache(url);
        if(bitmap!=null)
        {
            imageView.setImageBitmap(bitmap);
            return;
        }
        mThreadPool.submit(new Runnable() {
            @Override
            public void run() {
                imageView.setTag(url);
                Bitmap bitmap=new MyHttp().getHttpBitmap(url);
                if(imageView.getTag().equals(url))
                {
                    imageView.setImageBitmap(bitmap);
                    mCache.setCache(url,bitmap);
                }
            }
        });
    }
}

