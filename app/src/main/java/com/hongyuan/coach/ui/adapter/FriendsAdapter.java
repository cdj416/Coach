package com.hongyuan.coach.ui.adapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.coach.R;
import com.hongyuan.coach.ui.beans.FriendsBeans;
import com.makeramen.roundedimageview.RoundedImageView;

public class FriendsAdapter extends BaseQuickAdapter<FriendsBeans.DataBean.ListBean, BaseViewHolder> {

    public FriendsAdapter(){
        super(R.layout.item_friends);
    }
    @Override
    protected void convert(BaseViewHolder helper, FriendsBeans.DataBean.ListBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.default_head_img).error(R.mipmap.default_head_img);
        Glide.with(mContext).load(item.getMi_head()).apply(options).into((RoundedImageView)helper.getView(R.id.headImg));
        helper.setText(R.id.name,item.getM_name()).setText(R.id.mark,"健身达人");
        if(item.getIs_xh() == 1){
            helper.setText(R.id.status,"互相关注");
        }else{
            helper.setText(R.id.status,"已关注");
        }

        helper.addOnClickListener(R.id.status);
    }
}
