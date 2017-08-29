package com.vincent.imageloader.request;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.vincent.imageloader.ImageLoader;
import com.vincent.imageloader.config.display.DisplayConfig;
import com.vincent.imageloader.policy.LoadPolicy;

import java.lang.ref.WeakReference;

/**
 * Created by lixiang on 2017/8/29.
 */

public class BitmapRequest  implements Comparable<BitmapRequest>{
    public String requestUrl;
    public DisplayConfig displayConfig;
    public WeakReference<ImageView> imageViewRef;
    public LoadPolicy mloadPolicy= ImageLoader.getInstance().getImageLoaderConfing().getLoaderPolicy();
    public int serialNum;
    /**
     * 是否取消该请求
     */
    public boolean isCancle;
    public BitmapRequest(ImageView imageView,String requestUrl, DisplayConfig displayConfig) {
        this.imageViewRef =new WeakReference<>(imageView);
        this.requestUrl = requestUrl;
        this.displayConfig = displayConfig;
    }
    @Override
    public int compareTo(@NonNull BitmapRequest other) {
        return mloadPolicy.compare(this,other) ;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((requestUrl == null) ? 0 : requestUrl.hashCode());
        result = prime * result + ((imageViewRef == null) ? 0 : imageViewRef.get().hashCode());
        result = prime * result + serialNum;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BitmapRequest other = (BitmapRequest) obj;
        if (requestUrl == null) {
            if (other.requestUrl != null)
                return false;
        } else if (!requestUrl.equals(other.requestUrl))
            return false;
        if (imageViewRef == null) {
            if (other.imageViewRef != null)
                return false;
        } else if (!imageViewRef.get().equals(other.imageViewRef.get()))
            return false;
        if (serialNum != other.serialNum)
            return false;
        return true;
    }
}
