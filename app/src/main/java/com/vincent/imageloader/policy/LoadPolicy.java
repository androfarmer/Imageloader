package com.vincent.imageloader.policy;

import com.vincent.imageloader.request.BitmapRequest;

import java.util.Comparator;

/**
 * Created by lixiang on 2017/8/29.
 */

public interface LoadPolicy {
    int compare(BitmapRequest request1,BitmapRequest request2);
}
