package com.hongyuan.coach.ui.adapter;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.coach.R;
import com.hongyuan.coach.ui.beans.FitTypeListBeans;

public class CourseTypeAdapter extends BaseQuickAdapter<FitTypeListBeans.DataBean, BaseViewHolder> {

    public CourseTypeAdapter(){
        super(R.layout.item_comment_top);
    }

    @Override
    protected void convert(BaseViewHolder helper, FitTypeListBeans.DataBean item) {
        helper.getView(R.id.itemNum).setVisibility(View.GONE);
        helper.setText(R.id.itemName,item.getFt_name());
        helper.addOnClickListener(R.id.selectBox);

        if(item.isSelect()){
            helper.setTextColor(R.id.itemName,mContext.getResources().getColor(R.color.colorPrimary))
                    .setBackgroundRes(R.id.selectBox,R.drawable.shape_radius16_00000000_stroke_3da1e7);
        }else{
            helper.setTextColor(R.id.itemName,0xFF5F5F5F)
                    .setBackgroundRes(R.id.selectBox,R.drawable.shape_radius16_ffffff_stroke_979797);
        }
    }

}
