package com.hongyuan.coach.ui.adapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.coach.R;
import com.hongyuan.coach.ui.beans.FriendsBeans;
import com.makeramen.roundedimageview.RoundedImageView;

public class FindTopFriendsAdapter extends BaseQuickAdapter<FriendsBeans.DataBean.ListBean, BaseViewHolder> {

    public FindTopFriendsAdapter(){
        super(R.layout.item_feature_top_friends);
    }

    @Override
    protected void convert(BaseViewHolder helper, FriendsBeans.DataBean.ListBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.default_head_img).error(R.mipmap.default_head_img);
        Glide.with(mContext).load(item.getMi_head()).apply(options).into((RoundedImageView)helper.getView(R.id.headImg));

        //helper.addOnClickListener(R.id.jumpBox);
    }
}
