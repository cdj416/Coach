package com.hongyuan.coach.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hongyuan.coach.R;
import com.youth.banner.loader.ImageLoader;

/**
 *
 * Describe:提供Banner图片加载器
 */
public class UseGlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        //Glide 加载图片简单用法
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.test_no_data).error(R.mipmap.test_no_data).centerCrop();
        Glide.with(context).load(path).apply(options).into(imageView);
    }
}
