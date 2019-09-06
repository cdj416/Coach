package com.hongyuan.coach.ui.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.coach.R;
import com.hongyuan.coach.ui.beans.TabTimeBean;

public class TabTimeAdapter extends BaseQuickAdapter<TabTimeBean.DataBean, BaseViewHolder> {

    public TabTimeAdapter(){
        super(R.layout.item_schedule_tab);
    }
    @Override
    protected void convert(BaseViewHolder helper, TabTimeBean.DataBean item) {
        TextView weekText = helper.getView(R.id.weekText);
        TextView dataText = helper.getView(R.id.dataText);
        helper.setText(R.id.weekText,item.getShowWeek());
        if(item.getIs_cur_date() == 1){
            dataText.setText("今天");
        }else{
            dataText.setText(item.getShowName());
        }

        if(item.isSelect()){
            weekText.setTextColor(mContext.getResources().getColor(R.color.color_FFFFFF));
            dataText.setTextColor(mContext.getResources().getColor(R.color.color_FFFFFF));
            helper.setVisible(R.id.selectBgImg,true);
        }else{
            weekText.setTextColor(0xFF999999);
            dataText.setTextColor(0xFF999999);
            helper.setVisible(R.id.selectBgImg,false);
        }

        helper.addOnClickListener(R.id.tabBox);

    }
}
