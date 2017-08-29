package com.vincent.imageloader.policy;

import com.vincent.imageloader.request.BitmapRequest;

/**
 * Created by lixiang on 2017/8/29.
 */

public class FIFOPolicy implements LoadPolicy {
    @Override
    public int compare(BitmapRequest request1, BitmapRequest request2) {
        return request1.serialNum-request2.serialNum;
    }
}
