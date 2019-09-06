package com.hongyuan.coach.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.coach.R;
import com.hongyuan.coach.ui.beans.CourseDetailsBean;
import com.hongyuan.coach.util.BaseUtil;

public class CourseDetailsPriceAdapter extends BaseQuickAdapter<CourseDetailsBean.DataBean.PriceListBean, BaseViewHolder> {

    public CourseDetailsPriceAdapter(){
        super(R.layout.item_course_details_price_list);
    }

    @Override
    protected void convert(BaseViewHolder helper, CourseDetailsBean.DataBean.PriceListBean item) {
        helper.setText(R.id.courseNum,item.getMin_num()+"节课：¥")
                .setText(R.id.coursePrice, BaseUtil.getNoZoon(item.getPrice()));
    }
}
