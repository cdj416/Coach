package com.hongyuan.coach.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.coach.R;
import com.hongyuan.coach.custom_view.nine_gridimg.NineGridImageView;
import com.hongyuan.coach.custom_view.nine_gridimg.NineGridImageViewAdapter;
import com.hongyuan.coach.ui.beans.CommentBeans;
import com.hongyuan.coach.util.TimeUtil;
import com.makeramen.roundedimageview.RoundedImageView;
import com.previewlibrary.GPreviewBuilder;
import com.previewlibrary.enitity.UserViewInfo;

import java.util.ArrayList;
import java.util.List;

public class CommentAdapter extends BaseQuickAdapter<CommentBeans.DataBean.ListBean, BaseViewHolder> {

    public CommentAdapter(){
        super(R.layout.item_coach_homepage_pingjia);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommentBeans.DataBean.ListBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.default_head_img).error(R.mipmap.default_head_img).centerCrop();
        Glide.with(mContext).load(item.getMi_head()).apply(options).into((RoundedImageView)helper.getView(R.id.headImg));
        helper.setText(R.id.userName,item.getM_name())
                .setText(R.id.commentTime, TimeUtil.friendly_time(item.getCr_date()))
                .setText(R.id.cpName,item.getCp_name())
                .setText(R.id.barText,getBarString(Float.valueOf(item.getCoach_s())));
        RatingBar ratingBar = helper.getView(R.id.myRat);
        ratingBar.setRating(Float.valueOf(item.getCoach_s()));


        if(item.getCri() != null && item.getCri().size() > 0){
            helper.getView(R.id.nineGridImg).setVisibility(View.VISIBLE);
            NineGridImageView nineGridImageView = helper.getView(R.id.nineGridImg);
            nineGridImageView.setAdapter(mAdapter);
            nineGridImageView.setImagesData(item.getCri());
        }else{
            helper.getView(R.id.nineGridImg).setVisibility(View.GONE);
        }
    }

    /*
    * 根据星级返回相应的显示字段
    * */
    private String getBarString(float bar){

        if(bar <= 1){
            return "不满意";
        }
        if(bar > 1 && bar <=2){
            return "一般";
        }
        if(bar > 2 && bar <=3){
            return "满意";
        }
        if(bar > 3 && bar <=4){
            return "非常满意";
        }
        if(bar >= 5){
            return "超出预期";
        }
        return "超出预期";
    }


    /*
     * 九宫格适配器
     * */
    private NineGridImageViewAdapter<CommentBeans.DataBean.ListBean.CriBean> mAdapter = new NineGridImageViewAdapter<CommentBeans.DataBean.ListBean.CriBean>() {
        @Override
        protected void onDisplayImage(Context context, ImageView imageView, CommentBeans.DataBean.ListBean.CriBean s) {
            RequestOptions options = new RequestOptions().placeholder(R.mipmap.a_test2).error(R.mipmap.a_test2).centerCrop();
            Glide.with(mContext).load(s.getCri_src()).apply(options).into(imageView);
        }

        @Override
        protected ImageView generateImageView(Context context) {
            return super.generateImageView(context);
        }

        @Override
        protected void onItemImageClick(Context context, List<ImageView> mImageViewList, ImageView imageView, int index, List<CommentBeans.DataBean.ListBean.CriBean> list) {

            //点击查看大图功能
            GPreviewBuilder.from((Activity) context)
                    .setData(getInfoList(list,mImageViewList))
                    .setCurrentIndex(index)
                    .setType(GPreviewBuilder.IndicatorType.Dot)
                    .start();
        }
    };

    /*
     * 获取图片集和图片所处位置
     * */
    private List<UserViewInfo> getInfoList(List<CommentBeans.DataBean.ListBean.CriBean> list, List<ImageView> mImageViewList){
        List<UserViewInfo> imgList = new ArrayList<>();
        for(int i = 0 ; i < list.size() ; i++){
            imgList.add(new UserViewInfo(list.get(i).getCri_src()));
            Rect bounds = new Rect();
            mImageViewList.get(i).getGlobalVisibleRect(bounds);
            imgList.get(i).setBounds(bounds);
        }

        return imgList;
    }
}
