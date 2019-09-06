package com.hongyuan.coach.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.coach.R;
import com.hongyuan.coach.ui.beans.TimeBean;

public class TimeAdapter extends BaseQuickAdapter<TimeBean.DataBean, BaseViewHolder> {

    public TimeAdapter(){
        super(R.layout.item_edit_time_content);
    }
    @Override
    protected void convert(BaseViewHolder helper, TimeBean.DataBean item) {
        if(item.getIs_kong() == 1){
            helper.addOnClickListener(R.id.timeBox);
        }

        helper.setText(R.id.time,item.getNow_time().substring(0,5));
        if(item.isSelect()){
            helper.setBackgroundRes(R.id.timeBox,R.drawable.shape_time_optional_selected);
            helper.setTextColor(R.id.isOk,0xFFFFFFFF);
            helper.setTextColor(R.id.time,0xFFFFFFFF);
            if(item.getIs_kong() == 1){
                helper.setText(R.id.isOk,"没课");
            }else if(item.getIs_kong() == 0){
                helper.setText(R.id.isOk,"休息");
            }

        }else if(item.getIs_kong() == 1){
            helper.setBackgroundRes(R.id.timeBox,R.drawable.shape_time_optional_default);
            helper.setTextColor(R.id.isOk,0xFF6ABE24);
            helper.setTextColor(R.id.time,0xFF333333);
            helper.setText(R.id.isOk,"没课");
            helper.addOnClickListener(R.id.timeBox);
            helper.getView(R.id.timeBox).setClickable(true);
        }else if(item.getIs_kong() == 0){
            helper.setBackgroundRes(R.id.timeBox,R.drawable.shape_time_not_optional);
            helper.setTextColor(R.id.isOk,0xFF999999);
            helper.setTextColor(R.id.time,0xFF999999);
            helper.setText(R.id.isOk,"休息");
            helper.addOnClickListener(R.id.timeBox);
            helper.getView(R.id.timeBox).setClickable(true);
        }else if(item.getIs_kong() == 3){
            helper.setBackgroundRes(R.id.timeBox,R.drawable.shape_time_not_optional);
            helper.setTextColor(R.id.isOk,0xFF999999);
            helper.setTextColor(R.id.time,0xFF999999);
            helper.setText(R.id.isOk,"有课");
            helper.getView(R.id.timeBox).setClickable(false);
        }else{
            helper.setBackgroundRes(R.id.timeBox,R.drawable.shape_time_not_optional);
            helper.setTextColor(R.id.isOk,0xFF999999);
            helper.setTextColor(R.id.time,0xFF999999);
            helper.setText(R.id.isOk,"不可约");
            helper.getView(R.id.timeBox).setClickable(false);
        }
    }
}
