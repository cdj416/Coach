package com.hongyuan.coach.ui.adapter;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.coach.R;
import com.hongyuan.coach.ui.beans.FeatureBean;
import com.hongyuan.coach.util.TimeUtil;
import com.hongyuan.coach.util.ViewChangeUtil;
import com.makeramen.roundedimageview.RoundedImageView;


public class V2FindContentAdapter extends BaseQuickAdapter<FeatureBean.DataBean.ListBean, BaseViewHolder> {

    public V2FindContentAdapter(){
        super(R.layout.item_find_content_v2);
    }

    @Override
    protected void convert(final BaseViewHolder helper, FeatureBean.DataBean.ListBean item) {

        RequestOptions options = new RequestOptions().placeholder(R.mipmap.a_test2).error(R.mipmap.a_test2);
        Glide.with(mContext).load(item.getMi_head()).apply(options).into((RoundedImageView)helper.getView(R.id.headImg));

        int imgNum = 0;
        String imgUrl = "";
        if(item.getCi() != null && item.getCi().size() > 0){
            imgNum = item.getCi().size();
            imgUrl = item.getCi().get(0).getFile_src();
        }
        Glide.with(mContext).load(imgUrl).apply(options).into((RoundedImageView)helper.getView(R.id.coverImg));

        helper.setText(R.id.styleImgNum,String.valueOf(imgNum)).setText(R.id.fName,item.getM_name())
                .setText(R.id.timeText, TimeUtil.friendly_time(item.getAdd_date()))
                .setText(R.id.attention, String.valueOf(item.getPraise_num()));

        if(item.getIs_praise() == 0){
            ViewChangeUtil.changeBottomDrawable(mContext,helper.getView(R.id.attention),R.mipmap.like_huise_img);
        }else{
            ViewChangeUtil.changeBottomDrawable(mContext,helper.getView(R.id.attention),R.mipmap.like_chengse_img);
        }

        helper.addOnClickListener(R.id.jumpDetails).addOnClickListener(R.id.attention);

    }

}
