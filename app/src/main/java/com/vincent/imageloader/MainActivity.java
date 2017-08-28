package com.vincent.imageloader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.vincent.imageloader.cache.DoubleCache;
import com.vincent.imageloader.config.init.ImageLoaderConfig;

public class MainActivity extends AppCompatActivity {
    private ImageView image,image1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageLoaderConfig config=new ImageLoaderConfig();
        config.setCache(new DoubleCache(this)).setProcessCount(5);
        ImageLoader.getInstance().init(config);


        final String url="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1503298067916&di=d719d3003f1dcdc1f6c81d363cf32aa8&imgtype=0&src=http%3A%2F%2Fpic62.nipic.com%2Ffile%2F20150319%2F12632424_132215178296_2.jpg";
        image= (ImageView) findViewById(R.id.image);
        ImageLoader.getInstance().load(image,url);
        image1= (ImageView) findViewById(R.id.image1);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageLoader.getInstance().load(image1,url);
            }
        });
    }
}
