package com.hongyuan.coach.custom_view.nine_gridimg;

import android.content.Context;
import android.widget.ImageView;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public abstract class NineGridImageViewAdapter<T> {
    protected abstract void onDisplayImage(Context context, ImageView imageView, T t);

    protected void onItemImageClick(Context context,List<ImageView> mImageViewList, ImageView imageView, int index, List<T> list) {
    }

    protected boolean onItemImageLongClick(Context context, ImageView imageView, int index, List<T> list) {
        return false;
    }

    protected ImageView generateImageView(Context context) {
        RoundedImageView imageView = new RoundedImageView(context);
        imageView.setCornerRadius(20);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        //GridImageView imageView = new GridImageView(context);
        //imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return imageView;
    }
}