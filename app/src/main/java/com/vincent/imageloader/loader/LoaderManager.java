package com.vincent.imageloader.loader;

import java.util.HashMap;

/**
 * Created by lixiang on 2017/8/29.
 */

public  class  LoaderManager {
    private static final String HTTPS = "https";
    private static final String HTTP = "http";
    private static final String FILE = "file";
    private  HashMap<String, Loader> mHashMapLoader = new HashMap<>();
    private NullLoader nullLoader = new NullLoader();
    /**
     * 单例模式
     */
    private static LoaderManager mInstance;

    private LoaderManager() {
        register(HTTP, new UrlLoader());
        register(HTTPS, new UrlLoader());
        register(FILE, new FileLoader());
    }

    public static LoaderManager getInstance() {
        if (mInstance == null) {
            synchronized (LoaderManager.class) {
                if (mInstance == null) {
                    mInstance = new LoaderManager();
                }
            }
        }
        return mInstance;
    }

    private synchronized void register(String schame, Loader loader) {
        mHashMapLoader.put(schame, loader);
    }

    public Loader getLoader(String url) {
        String key=getSchame(url);
        if(mHashMapLoader.containsKey(key))
            return mHashMapLoader.get(key);
        else return nullLoader;
    }

    private String getSchame(String url) {
        String schame;
        if (url.contains("://")) {
            schame = url.split("://")[0].toLowerCase();
        } else {
            schame = "";
        }
        return schame;
    }

}
