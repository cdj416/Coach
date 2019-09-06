package com.hongyuan.coach.ui.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.coach.R;
import com.hongyuan.coach.ui.beans.TabTimeBean;

public class EditTimeTabAdapter extends BaseQuickAdapter<TabTimeBean.DataBean, BaseViewHolder> {

    public EditTimeTabAdapter(){
        super(R.layout.item_edit_time_tab);
    }
    @Override
    protected void convert(BaseViewHolder helper, TabTimeBean.DataBean item) {
        TextView weekText = helper.getView(R.id.weekText);
        TextView dataText = helper.getView(R.id.dataText);
        helper.setText(R.id.weekText,item.getWeek());
        if(item.getIs_cur_date() == 1){
            dataText.setText("今日");
        }else{
            dataText.setText(item.getShowName());
        }

        if(item.isSelect()){
            weekText.setTextColor(mContext.getResources().getColor(R.color.color_3DA1E7));
            dataText.setTextColor(mContext.getResources().getColor(R.color.color_3DA1E7));
        }else{
            weekText.setTextColor(0xFF999999);
            dataText.setTextColor(0xFF999999);
        }

        helper.addOnClickListener(R.id.tabBox);

    }
}
