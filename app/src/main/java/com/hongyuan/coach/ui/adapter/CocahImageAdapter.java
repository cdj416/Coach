package com.hongyuan.coach.ui.adapter;


import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.coach.R;
import com.hongyuan.coach.ui.beans.CoachImgBeans;
import com.makeramen.roundedimageview.RoundedImageView;

public class CocahImageAdapter extends BaseQuickAdapter<CoachImgBeans.DataBean.ListBean, BaseViewHolder> {

    public CocahImageAdapter() {
        super(R.layout.item_coach_image);
    }

    @Override
    protected void convert(BaseViewHolder helper, CoachImgBeans.DataBean.ListBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.zhengfangxing_default_img).error(R.mipmap.zhengfangxing_default_img);
        Glide.with(mContext).load(item.getImg_src()).apply(options).into((RoundedImageView)helper.getView(R.id.img));

        //是否为编辑状态
        if(item.isEdite()){
            helper.getView(R.id.markChick).setVisibility(View.VISIBLE);
        }else{
            helper.getView(R.id.markChick).setVisibility(View.GONE);
        }

        //是否为选中状态
        if(item.isSelect()){
            helper.setImageResource(R.id.markChick,R.mipmap.edit_status_select_mark);
        }else{
            helper.setImageResource(R.id.markChick,R.mipmap.edit_status_default_mark);
        }

        helper.addOnClickListener(R.id.img).addOnClickListener(R.id.markChick);
    }
}
