package com.brucej.wanandroid_java.ui;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoaderInterface;

public class BannerGlideImageLoader implements ImageLoaderInterface {
    @Override
    public void displayImage(Context context, Object path, View imageView) {
        Glide.with(context)
                .load(path)
                .into((ImageView) imageView);
    }

    @Override
    public View createImageView(Context context) {
        return new ImageView(context);
    }
}
