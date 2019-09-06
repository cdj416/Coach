package com.hongyuan.coach.custom_view.comm_title;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.coach.R;

public class CommentTitleAdapter extends BaseQuickAdapter<CommentTitleBeans, BaseViewHolder> {

    public CommentTitleAdapter(){
        super(R.layout.item_comment_top);
    }
    @Override
    protected void convert(BaseViewHolder helper, CommentTitleBeans item) {
        helper.setText(R.id.itemName,item.getName()).setText(R.id.itemNum,String.valueOf(item.getNum()));
        helper.addOnClickListener(R.id.selectBox);

        if(item.isSelect()){
            helper.setTextColor(R.id.itemName,0xFFFFFFFF)
                    .setTextColor(R.id.itemNum,0xFFFFFFFF)
                    .setBackgroundRes(R.id.selectBox,R.drawable.shape_radius16_ef5b48);
        }else{
            helper.setTextColor(R.id.itemName,0xFF5F5F5F)
                    .setTextColor(R.id.itemNum,0xFFEF5B48)
                    .setBackgroundRes(R.id.selectBox,R.drawable.shape_radius16_ffffff_stroke_979797);
        }
    }
}
