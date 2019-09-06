package com.hongyuan.coach.ui.adapter;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.coach.R;
import com.hongyuan.coach.ui.beans.PostDetailsCommentsBean;

public class PostDetailsChildsAdapter extends BaseQuickAdapter<PostDetailsCommentsBean.DataBean.ListBean.ListSBean, BaseViewHolder> {

    public PostDetailsChildsAdapter(){
        super(R.layout.item_child_comment);
    }

    @Override
    protected void convert(final BaseViewHolder helper, PostDetailsCommentsBean.DataBean.ListBean.ListSBean item) {

        String showName;
        if(item.getCr_id_father() != item.getFirst_cr_id()){
            showName = item.getM_name()+"回复"+item.getTo_m_name()+":";
            SpannableString spannableString = new SpannableString(showName);
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#000000")), showName.indexOf("回复"),(showName.indexOf("回复")+2), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            helper.setText(R.id.titleName, spannableString);
        }else{
            showName = item.getM_name() + ":";
            helper.setText(R.id.titleName, showName);
        }

        helper.setText(R.id.content,item.getCr_content());
        helper.addOnClickListener(R.id.childBox);
    }

}
