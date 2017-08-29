package com.vincent.imageloader.loader;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.vincent.imageloader.request.BitmapRequest;

/**
 * Created by lixiang on 2017/8/29.
 */

public abstract class AbsLoader implements Loader {
    @Override
    public void loadImage(BitmapRequest bitmapRequest) {
        final Bitmap bitmap=getBitmap(bitmapRequest);
        final ImageView imageView=bitmapRequest.imageViewRef.get();
        imageView.post(new Runnable() {
            @Override
            public void run() {
                imageView.setImageBitmap(bitmap);
            }
        });
    }
    protected abstract Bitmap getBitmap(BitmapRequest bitmapRequest);
}
