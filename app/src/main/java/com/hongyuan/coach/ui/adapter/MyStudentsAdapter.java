package com.hongyuan.coach.ui.adapter;

import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.coach.R;
import com.hongyuan.coach.ui.beans.MyStudentsBeans;
import com.hongyuan.coach.util.BaseUtil;
import com.hongyuan.coach.util.TimeUtil;
import com.makeramen.roundedimageview.RoundedImageView;

public class MyStudentsAdapter extends BaseQuickAdapter<MyStudentsBeans.DataBean.ListBean, BaseViewHolder> {

    public MyStudentsAdapter(){
        super(R.layout.item_my_students);
    }
    @Override
    protected void convert(BaseViewHolder helper, MyStudentsBeans.DataBean.ListBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.default_head_img).error(R.mipmap.default_head_img).centerCrop();
        Glide.with(mContext).load(item.getMi_head()).apply(options).into((RoundedImageView)helper.getView(R.id.headImg));

        helper.setText(R.id.studentName,item.getM_name())
                .setText(R.id.courseNum,String.valueOf(item.getHave_num()));
        if(BaseUtil.isValue(item.getLast_date()) && !"0".equals(item.getLast_date())){
            helper.setText(R.id.lastGet,"距离上次上课"+ TimeUtil.friendly_time(item.getLast_date()));
        }else{
            helper.setText(R.id.lastGet,"还未上第一节课");
        }
        if(item.getIs_in() == 0){
            helper.getView(R.id.atShopMark).setVisibility(View.GONE);
            helper.getView(R.id.departureMark).setVisibility(View.VISIBLE);
        }else{
            helper.getView(R.id.atShopMark).setVisibility(View.VISIBLE);
            helper.getView(R.id.departureMark).setVisibility(View.GONE);
        }
        helper.addOnClickListener(R.id.callTel);
    }
}
