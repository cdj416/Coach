package com.hongyuan.coach.ui.adapter;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.coach.R;
import com.hongyuan.coach.ui.beans.CouseListBean;
import com.makeramen.roundedimageview.RoundedImageView;

public class CoachHomePageCouseListAdapter extends BaseQuickAdapter<CouseListBean.DataBean.ListBean, BaseViewHolder> {

    public CoachHomePageCouseListAdapter() {
        super(R.layout.item_coach_homepage_course_list);
    }

    @Override
    protected void convert(BaseViewHolder helper, CouseListBean.DataBean.ListBean item) {

        RequestOptions options = new RequestOptions().placeholder(R.mipmap.a_test2).error(R.mipmap.a_test2).centerCrop();
        Glide.with(mContext).load(item.getCp_img()).apply(options).into((RoundedImageView)helper.getView(R.id.bgImg));

        helper.setText(R.id.classType,item.getCp_name()).setText(R.id.classBewrite,item.getCp_info())
                .setText(R.id.coachPrice,item.getCp_price());

        helper.addOnClickListener(R.id.courseBox);
    }
}
