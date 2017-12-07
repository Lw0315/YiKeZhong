package com.example.asus.yikezhong.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.asus.yikezhong.R;

/**
 * GlideLoader
 * Created by Yancy on 2015/12/6.
 */
public class GlideLoader implements com.yancy.imageselector.ImageLoader{


    @Override
    public void displayImage(Context context, String path, ImageView imageView) {
        RequestOptions op=new RequestOptions().placeholder(R.mipmap.ic_launcher);
        Glide.with(context)
                .load(path).apply(op)
               // .placeholder(com.yancy.imageselector.R.mipmap.imageselector_photo)
               // .centerCrop()
                .into(imageView);
    }
}