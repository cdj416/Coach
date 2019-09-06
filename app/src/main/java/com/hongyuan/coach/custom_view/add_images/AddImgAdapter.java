package com.hongyuan.coach.custom_view.add_images;

import android.view.View;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.coach.R;
import com.hongyuan.coach.ui.beans.FileBean;
import com.makeramen.roundedimageview.RoundedImageView;

public class AddImgAdapter extends BaseQuickAdapter<FileBean, BaseViewHolder> {

    public AddImgAdapter(){
        super(R.layout.item_add_images);
    }

    @Override
    protected void convert(final BaseViewHolder helper, FileBean item) {
        if(item.getmFile() != null){
            helper.getView(R.id.tvBg).setVisibility(View.GONE);
            helper.getView(R.id.defaultImg).setVisibility(View.GONE);

            helper.getView(R.id.contentImg).setVisibility(View.VISIBLE);
            helper.setVisible(R.id.closeImg,true);
            Glide.with(mContext).load(item.getmFile()).into((RoundedImageView)helper.getView(R.id.contentImg));
        }else{
            helper.getView(R.id.tvBg).setVisibility(View.VISIBLE);
            helper.getView(R.id.defaultImg).setVisibility(View.VISIBLE);

            helper.getView(R.id.contentImg).setVisibility(View.GONE);
            helper.setVisible(R.id.closeImg,false);
        }

        helper.addOnClickListener(R.id.closeImg)
                .addOnClickListener(R.id.contentImg)
                .addOnClickListener(R.id.tvBg);
    }

}
