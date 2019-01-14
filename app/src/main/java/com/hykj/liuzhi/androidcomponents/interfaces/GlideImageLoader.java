package com.hykj.liuzhi.androidcomponents.interfaces;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.youth.banner.loader.ImageLoader;

/**
 * @author: lujialei
 * @date: 2018/9/30
 * @describe:
 */


public class GlideImageLoader extends ImageLoader {

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        RequestOptions options = new RequestOptions();
        options.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        Glide.with(context)
                .applyDefaultRequestOptions(options)
                .load(path)
                .into(imageView);
    }
}
