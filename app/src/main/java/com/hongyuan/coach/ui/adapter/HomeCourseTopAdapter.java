package com.hongyuan.coach.ui.adapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.coach.R;
import com.hongyuan.coach.custom_view.my_progress.CircleProgress;
import com.hongyuan.coach.ui.beans.HomeCourseTopBeans;
import com.hongyuan.coach.util.BaseUtil;
import com.hongyuan.coach.util.BigDecimalUtils;

public class HomeCourseTopAdapter extends BaseQuickAdapter<HomeCourseTopBeans.DataBean.ItemBean, BaseViewHolder> {

    public HomeCourseTopAdapter(){
        super(R.layout.item_home_course_type);
    }

    @Override
    protected void convert(final BaseViewHolder helper, HomeCourseTopBeans.DataBean.ItemBean item) {
        CircleProgress progress = helper.getView(R.id.myProgress);
        progress.setValue(getProVaule(item.getPer()));

        helper.setText(R.id.proportionNum, BaseUtil.getNoZoon(getProVaule(item.getPer())))
                .setText(R.id.courseName,item.getCp_name());
    }

    /*
    * 获取百分比值
    * */
    private float getProVaule(String value){
        return Float.valueOf(BigDecimalUtils.mul(value,"100",2));
    }

}
