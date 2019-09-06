package com.hongyuan.coach.ui.adapter;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.coach.R;
import com.hongyuan.coach.ui.beans.HomeCouseListBean;
import com.hongyuan.coach.util.BaseUtil;
import com.makeramen.roundedimageview.RoundedImageView;

public class HomeCouseListAdapter extends BaseQuickAdapter<HomeCouseListBean.DataBean.ListBean, BaseViewHolder> {

    public HomeCouseListAdapter() {
        super(R.layout.item_home_course_list);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeCouseListBean.DataBean.ListBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.a_test2).error(R.mipmap.a_test2).centerCrop();
        Glide.with(mContext).load(item.getCp_img()).apply(options).into((RoundedImageView)helper.getView(R.id.bgImg));

        helper.setText(R.id.classTime,"可约时间："+item.getCp_duration())
                .setText(R.id.courseName,item.getCp_name())
                .setText(R.id.classInfo,item.getCp_info())
                .setText(R.id.coursePrice, BaseUtil.getNoZoon(item.getCp_price()));

        helper.addOnClickListener(R.id.courseBox);
    }
}
