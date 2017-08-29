package com.vincent.imageloader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.vincent.imageloader.cache.DoubleCache;
import com.vincent.imageloader.config.init.ImageLoaderConfig;
import com.vincent.imageloader.policy.FIFOPolicy;

public class MainActivity extends AppCompatActivity {
    private ImageView image,image1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageLoaderConfig config=new ImageLoaderConfig();
        config.setCache(new DoubleCache(this)).setThreadCount(5).setLoaderPolicy(new FIFOPolicy());
        ImageLoader.getInstance().init(config);


        final String url="http://imgsrc.baidu.com/imgad/pic/item/267f9e2f07082838b5168c32b299a9014c08f1f9.jpg";
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
