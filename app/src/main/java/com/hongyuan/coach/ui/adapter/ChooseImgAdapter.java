package com.hongyuan.coach.ui.adapter;

import android.view.View;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.coach.R;
import com.hongyuan.coach.ui.beans.FileBean;
import com.makeramen.roundedimageview.RoundedImageView;

public class ChooseImgAdapter extends BaseQuickAdapter<FileBean, BaseViewHolder> {

    public ChooseImgAdapter(){
        super(R.layout.item_select_img);
    }

    @Override
    protected void convert(final BaseViewHolder helper, FileBean item) {
        if(item.getmFile() != null){
            helper.setVisible(R.id.closeImg,true);
            helper.getView(R.id.defaultImg).setVisibility(View.GONE);
            Glide.with(mContext).load(item.getmFile()).into((RoundedImageView)helper.getView(R.id.contentImg));
        }else{
            helper.getView(R.id.defaultImg).setVisibility(View.VISIBLE);
            helper.getView(R.id.contentImg).setVisibility(View.GONE);
            helper.setVisible(R.id.closeImg,false);
        }

        helper.addOnClickListener(R.id.closeImg).addOnClickListener(R.id.contentImg);
    }

}
