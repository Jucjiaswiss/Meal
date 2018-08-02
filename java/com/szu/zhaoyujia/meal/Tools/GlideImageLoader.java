package com.szu.zhaoyujia.meal.Tools;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoaderInterface;

/**
 * Created by zhaoyujia on 2017/9/20.
 */

public class GlideImageLoader implements ImageLoaderInterface{

    @Override
    public void displayImage(Context context, Object path, View imageView) {
        //Glide 加载图片简单用法
        Glide.with(context).load(path).into((ImageView) imageView);
    }

    @Override
    public View createImageView(Context context) {
        return null;
    }
}
