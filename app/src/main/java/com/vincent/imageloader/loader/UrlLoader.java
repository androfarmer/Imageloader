package com.vincent.imageloader.loader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.vincent.imageloader.request.BitmapRequest;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by lixiang on 2017/8/29.
 */

public class UrlLoader extends AbsLoader {
    @Override
    protected Bitmap getBitmap(BitmapRequest bitmapRequest) {
        String url = bitmapRequest.requestUrl;
        HttpURLConnection urlConnection = null;
        InputStream is = null;
        Bitmap bitmap;
        try {
            URL neturl = new URL(url);
            urlConnection = (HttpURLConnection) neturl.openConnection();
            is = urlConnection.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
